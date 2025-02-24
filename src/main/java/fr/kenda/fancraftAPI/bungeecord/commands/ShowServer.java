package fr.kenda.fancraftAPI.bungeecord.commands;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.utils.MessageUtils;
import fr.kenda.fancraftAPI.bungeecord.utils.Role;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ShowServer extends Command {

    public ShowServer(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission(Role.ADMIN.getPermission())) {
            FancraftApiBungee.getInstance().getProxy().getServers().forEach((s, serverInfo) ->
            {
                MessageUtils.sendPlayerMessage(commandSender, s);
            });
        }
    }
}
