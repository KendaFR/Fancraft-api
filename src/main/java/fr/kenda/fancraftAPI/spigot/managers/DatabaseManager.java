package fr.kenda.fancraftAPI.spigot.managers;

import fr.kenda.fancraftAPI.spigot.FancraftAPISpigot;
import fr.kenda.fancraftAPI.spigot.database.Database;
import fr.kenda.fancraftAPI.spigot.database.DatabaseRedis;
import org.bukkit.configuration.file.FileConfiguration;
import redis.clients.jedis.Jedis;

import java.sql.Connection;

public class DatabaseManager implements IManager {

    private Database database;
    private DatabaseRedis databaseRedis;

    @Override
    public void register() {

        final FancraftAPISpigot instance = FancraftAPISpigot.getApi();
        final FileConfiguration config = instance.getConfig();


        final String host = config.getString("database.mysql.host");
        final int port = config.getInt("database.mysql.port");
        final String user = config.getString("database.mysql.user");
        final String password = config.getString("database.mysql.password");
        final String databaseName = config.getString("database.mysql.database");

        final String hostRedis = config.getString("database.redis.host");
        final int portRedis = config.getInt("database.redis.port");
        final String passwordRedis = config.getString("database.redis.password");

        database = new Database(host, user, password, port, databaseName);
        databaseRedis = new DatabaseRedis(hostRedis, portRedis, passwordRedis);
    }

    public Connection getDatabase() {
        return database.getConnection();
    }

    public Jedis getDatabaseRedis() {
        return databaseRedis.getConnection();
    }

    public void close() {
        database.close();
        databaseRedis.close();
    }
}

