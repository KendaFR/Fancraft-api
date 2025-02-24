package fr.kenda.fancraftAPI.bungeecord.managers;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.commands.ShowServer;
import net.md_5.bungee.api.plugin.PluginManager;

public class CommandManager implements IManager {
    @Override
    public void register() {
        final FancraftApiBungee instance = FancraftApiBungee.getInstance();
        final PluginManager pm = instance.getProxy().getPluginManager();
        pm.registerCommand(instance, new ShowServer("showserver"));
    }
}
