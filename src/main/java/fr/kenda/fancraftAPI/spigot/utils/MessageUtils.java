package fr.kenda.fancraftAPI.spigot.utils;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

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
}
