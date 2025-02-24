package fr.kenda.fancraftAPI.bungeecord.events;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.managers.IManager;
import net.md_5.bungee.api.plugin.PluginManager;

public class EventManager implements IManager {

    @Override
    public void register() {

        final FancraftApiBungee instance = FancraftApiBungee.getInstance();
        PluginManager pm = instance.getProxy().getPluginManager();
        pm.registerListener(instance, new PlayerJoin());
    }


}
