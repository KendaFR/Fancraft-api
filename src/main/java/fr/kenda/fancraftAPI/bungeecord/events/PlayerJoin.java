package fr.kenda.fancraftAPI.bungeecord.events;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.utils.MessageUtils;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        final FancraftApiBungee instance = FancraftApiBungee.getInstance();
        instance.getProxy().getScheduler().schedule(instance, () -> {

            Optional<ServerInfo> optimalLobby = FancraftApiBungee.getInstance().getProxy().getServers()
                    .entrySet().stream()
                    .filter(entry -> entry.getKey().contains("lobby_"))
                    .min(Comparator.comparingInt(entry -> entry.getValue().getPlayers().size())) // Trouver le serveur avec le moins de joueurs
                    .map(Map.Entry::getValue);

            if (optimalLobby.isPresent()) {
                event.getPlayer().connect(optimalLobby.get());
            } else {
                MessageUtils.sendPlayerMessage(event.getPlayer(), "&cAucun lobby trouvé.");
            }
        }, 250, TimeUnit.MILLISECONDS);
    }
}