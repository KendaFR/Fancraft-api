package fr.kenda.fancraftAPI.bungeecord.utils;

public enum Role {

    ADMIN("admin", 999),
    PLAYER("player", 0);


    private final String permission;
    private final int power;

    public String getPermission() {
        return permission;
    }

    public int getPower() {
        return power;
    }

    Role(String permission, int power) {
        this.permission = permission;
        this.power = power;
    }
}
