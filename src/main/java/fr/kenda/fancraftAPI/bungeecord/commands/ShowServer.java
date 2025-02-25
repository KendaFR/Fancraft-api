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
            MessageUtils.sendCommandSenderMessage(commandSender, "&2===== &a[Servers] &2=====");
            FancraftApiBungee.getInstance().getProxy().getServersCopy().keySet().forEach(server -> MessageUtils.sendCommandSenderMessage(commandSender, server));
            MessageUtils.sendCommandSenderMessage(commandSender, "&2===== &a[Servers] &2=====");
        }
        //TODO make no valid
    }
}
