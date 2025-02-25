package fr.kenda.fancraftAPI.bungeecord.utils;

public enum Definitions {

    PREFIX("&f[Fan&cCraft&f] ");


    private final String string;

    public String toString() {
        return string;
    }

    Definitions(String string) {
        this.string = string;
    }
}