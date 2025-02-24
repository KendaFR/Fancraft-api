package fr.kenda.fancraftAPI.bungeecord.config;


import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.utils.FileDatabase;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private final FancraftApiBungee instance;

    public ConfigFile() {
        instance = FancraftApiBungee.getInstance();
        createConfig(FileDatabase.DATABASE_FILE, this::loadDefaultDatabase);
    }

    public Configuration getConfig(String fileName) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveConfig(Configuration config, String fileName) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(instance.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createConfig(String fileName, Runnable loadDefaults) {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }

        File file = new File(instance.getDataFolder(), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                loadDefaults.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDefaultDatabase() {
        Configuration config = getConfig(FileDatabase.DATABASE_FILE);
        if (config != null) {
            config.set("database.mysql.host", "127.0.0.1");
            config.set("database.mysql.port", 3306);
            config.set("database.mysql.user", "fancraft");
            config.set("database.mysql.password", "root");
            config.set("database.mysql.database", "fancraft");

            config.set("database.redis.host", "127.0.0.1");
            config.set("database.redis.port", 6379);
            config.set("database.redis.password", "root");
        }
        saveConfig(config, FileDatabase.DATABASE_FILE);
    }
}
