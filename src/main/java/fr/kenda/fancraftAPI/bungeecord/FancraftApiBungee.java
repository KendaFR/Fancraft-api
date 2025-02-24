package fr.kenda.fancraftAPI.bungeecord;


import fr.kenda.fancraftAPI.bungeecord.config.ConfigFile;
import fr.kenda.fancraftAPI.bungeecord.events.EventManager;
import fr.kenda.fancraftAPI.bungeecord.managers.CommandManager;
import fr.kenda.fancraftAPI.bungeecord.managers.DatabaseManager;
import fr.kenda.fancraftAPI.bungeecord.managers.DockerManager;
import net.md_5.bungee.api.plugin.Plugin;

public class FancraftApiBungee extends Plugin {

    private static FancraftApiBungee instance;
    private DatabaseManager database;
    private DockerManager dockerManager;
    private ConfigFile config;

    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigFile();

        database = new DatabaseManager();
        database.register();

        dockerManager = new DockerManager();
        dockerManager.register();

        new EventManager().register();
        new CommandManager().register();
    }

    @Override
    public void onDisable() {
        database.close();
        dockerManager.closeAllDocker();
    }

    public static FancraftApiBungee getInstance() {
        return instance;
    }

    public DatabaseManager getDatabase() {
        return database;
    }

    public DockerManager getDockerManager() {
        return dockerManager;
    }

    public ConfigFile getConfig() {
        return config;
    }
}
