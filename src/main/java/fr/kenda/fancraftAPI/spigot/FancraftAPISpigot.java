package fr.kenda.fancraftAPI.spigot;

import fr.kenda.fancraftAPI.spigot.managers.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FancraftAPISpigot extends JavaPlugin {

    private static FancraftAPISpigot api;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        api = this;
        saveDefaultConfig();

        databaseManager = new DatabaseManager();
        databaseManager.register();
    }

    @Override
    public void onDisable() {
        databaseManager.close();
    }

    public static FancraftAPISpigot getApi() {
        return api;
    }
}