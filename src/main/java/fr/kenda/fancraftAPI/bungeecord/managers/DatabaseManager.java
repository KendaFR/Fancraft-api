package fr.kenda.fancraftAPI.bungeecord.managers;

import fr.kenda.fancraftAPI.bungeecord.FancraftApiBungee;
import fr.kenda.fancraftAPI.bungeecord.config.ConfigFile;
import fr.kenda.fancraftAPI.bungeecord.database.Database;
import fr.kenda.fancraftAPI.bungeecord.database.DatabaseRedis;
import fr.kenda.fancraftAPI.bungeecord.utils.FileDatabase;
import net.md_5.bungee.config.Configuration;
import redis.clients.jedis.Jedis;

import java.sql.Connection;

public class DatabaseManager implements IManager {

    private Database database;
    private DatabaseRedis databaseRedis;

    @Override
    public void register() {

        final FancraftApiBungee instance = FancraftApiBungee.getInstance();
        final ConfigFile config = instance.getConfig();
        final Configuration configDB = config.getConfig(FileDatabase.DATABASE_FILE);

        final String host = configDB.getString("database.mysql.host");
        final int port = configDB.getInt("database.mysql.port");
        final String user = configDB.getString("database.mysql.user");
        final String password = configDB.getString("database.mysql.password");
        final String databaseName = configDB.getString("database.mysql.database");

        final String hostRedis = configDB.getString("database.redis.host");
        final int portRedis = configDB.getInt("database.redis.port");
        final String passwordRedis = configDB.getString("database.redis.password");

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

