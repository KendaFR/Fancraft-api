package fr.kenda.fancraftAPI.bungeecord.managers;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.utils.Definitions;
import fr.kenda.fancraftAPI.bungeecord.utils.MessageUtils;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DockerManager implements IManager {

    @Override
    public void register() {
        int port = getRandomPort();
        while (isExistPort(port)) {
            port = getRandomPort();
        }
        //launch a lobby
        launchLobby(port);
    }

    public void launchLobby(int port) {
        final String lobbyName = "lobby_" + port;
        final String cmd = "docker run --rm -d --network host -e PORT=" + port + " --name " + lobbyName + " lobby:latest";
        MessageUtils.sendLogConsole(Definitions.PREFIX + "&7Création " + lobbyName);

        new Thread(() -> {
            try {
                ProcessBuilder builder = new ProcessBuilder("sh", "-c", cmd);
                builder.redirectErrorStream(true);
                Process process = builder.start();

                int exitCode = process.waitFor();
                process.destroy();
                MessageUtils.sendLogConsole(Definitions.PREFIX + "&7Processus terminé avec le code : " + exitCode);

            } catch (Exception e) {
                e.printStackTrace();
                MessageUtils.sendLogConsole(Definitions.PREFIX + "&cErreur lors du lancement du lobby : " + e.getMessage());
            }
        }).start();

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", port);
        ServerInfo serverInfo = FancraftApiBungee.getInstance().getProxy().constructServerInfo(lobbyName, socketAddress, "Dynamic Server", false);

        FancraftApiBungee.getInstance().getProxy().getServers().put(lobbyName, serverInfo);
    }

    public void closeAllDocker() {
        final Map<String, ServerInfo> servers = FancraftApiBungee.getInstance().getProxy().getServers();
        ArrayList<String> serverDocker = new ArrayList<>();

        servers.keySet().forEach(s ->
        {
            if (!s.equalsIgnoreCase("lobby")) {
                serverDocker.add(s);
            }
        });

        serverDocker.forEach(server -> new Thread(() -> {
            try {
                ProcessBuilder builder = new ProcessBuilder("sh", "-c", "docker stop " + server);
                builder.redirectErrorStream(true);
                Process process = builder.start();

                process.waitFor();
                process.destroy();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
    }

    private boolean isExistPort(int port) {
        boolean exist = false;
        for (ServerInfo si : FancraftApiBungee.getInstance().getProxy().getServers().values()) {
            try {
                int _port = Integer.parseInt(si.getSocketAddress().toString().split(":")[1]);
                if (_port == port) {
                    exist = true;
                    break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    private int getRandomPort() {
        final Random random = new Random();
        final int min = 20000;
        final int max = 26000;
        return random.nextInt(max - min) + min;
    }
}
