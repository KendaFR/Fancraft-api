package fr.kenda.fancraftAPI.bungeecord.utils;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageUtils {

    public static void sendLogConsole(String msg) {
        FancraftApiBungee.getInstance().getProxy().getConsole().sendMessage(transformColor(msg));
    }

    public static void sendLogErrorConsole(String msg) {
        FancraftApiBungee.getInstance().getProxy().getConsole().sendMessage(transformColor(msg));
    }

    public static TextComponent transformColor(String msg) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void sendPlayerMessage(ProxiedPlayer player, String msg) {
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', msg)));
    }

    public static void sendPlayerMessage(CommandSender sender, String msg) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', msg)));
    }
}
