package fr.kenda.fancraftAPI.spigot.database;

import redis.clients.jedis.Jedis;

public class DatabaseRedis {
    private final Jedis jedis;

    public DatabaseRedis(String host, int port, String password) {
        jedis = new Jedis(host, port);
        if (password != null && !password.isEmpty()) {
            jedis.auth(password);
        }
    }

    public Jedis getConnection() {
        return jedis;
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
