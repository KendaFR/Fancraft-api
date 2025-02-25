package fr.kenda.fancraftAPI.bungeecord.managers;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.utils.Definitions;
import fr.kenda.fancraftAPI.bungeecord.utils.MessageUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DockerManager implements IManager {

    @Override
    public void register() {
        int port = getRandomPort();

        //launch a lobby by default
        launchLobby(port);
    }

    /**
     * Launch a lobby from a port
     * Create a new process to launch a docker of lobby:latest with port
     *
     * @param port Integer
     */
    public void launchLobby(int port) {
        final String lobbyName = "dyn_lobby_" + port;
        final String cmd = "docker run --rm -d --network host -e PORT=" + port + " --name " + lobbyName + " lobby:latest";
        MessageUtils.sendLogConsole(Definitions.PREFIX + "&7Creating lobby " + lobbyName);

        new Thread(() -> {
            try {
                ProcessBuilder builder = new ProcessBuilder("sh", "-c", cmd);
                builder.redirectErrorStream(true);
                Process process = builder.start();

                int exitCode = process.waitFor();
                process.destroy();
                MessageUtils.sendLogConsole(Definitions.PREFIX + "&7process end with code: " + exitCode);

            } catch (Exception e) {
                MessageUtils.sendLogErrorConsole(Definitions.PREFIX + "Error during launch lobby : " + e.getMessage());
            }
        }).start();

        final ProxyServer proxy = FancraftApiBungee.getInstance().getProxy();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", port);
        ServerInfo serverInfo = proxy.constructServerInfo(lobbyName, socketAddress, "Dynamic Server", false);

        proxy.getServersCopy().put(lobbyName, serverInfo);
    }

    /**
     * Close all docker of proxy
     * Get all docker with contains string "dyn" and close all.
     */
    public void closeAllDocker() {
        final Map<String, ServerInfo> servers = FancraftApiBungee.getInstance().getProxy().getServersCopy();
        ArrayList<String> serverDocker = new ArrayList<>();

        servers.keySet().forEach(s ->
        {
            if (s.equalsIgnoreCase("dyn_")) {
                serverDocker.add(s);
            }
        });

        serverDocker.forEach(server -> new Thread(() -> {
            try {
                MessageUtils.sendLogConsole("&7Trying to close ");
                ProcessBuilder builder = new ProcessBuilder("sh", "-c", "docker stop " + server);
                builder.redirectErrorStream(true);
                Process process = builder.start();

                process.waitFor();
                process.destroy();

            } catch (Exception e) {
                MessageUtils.sendLogErrorConsole("Error during close of docker.. Error: " + e.getMessage());
            }
        }).start());
    }

    /**
     * Check if port is already assigned on server
     *
     * @param port Integer
     * @return Boolean
     */
    private boolean isExistPort(int port) {
        boolean exist = false;
        for (ServerInfo si : FancraftApiBungee.getInstance().getProxy().getServersCopy().values()) {
            try {
                int _port = Integer.parseInt(si.getSocketAddress().toString().split(":")[1]);
                if (_port == port) {
                    exist = true;
                    break;
                }
            } catch (NumberFormatException e) {
                MessageUtils.sendLogErrorConsole("Error during retrieve port. Error: " + e.getMessage());
            }
        }
        return exist;
    }

    private int getRandomPort() {
        final int min = 20000;
        final int max = 26000;
        int port = random.nextInt(max - min) + min;

        while (isExistPort(port)) {
            port = getRandomPort();
        }
        return port;
    }
    final Random random = new Random();
}
