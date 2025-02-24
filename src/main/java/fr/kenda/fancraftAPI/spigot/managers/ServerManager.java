package fr.kenda.fancraftAPI.spigot.managers;

import fr.kenda.fancraftAPI.bungeecord.managers.IManager;
import fr.kenda.fancraftAPI.spigot.service.IServerManager;

public class ServerManager implements IManager, IServerManager {

    @Override
    public void register() {
        registerServer();
    }

    @Override
    public void registerServer() {

    }
}
