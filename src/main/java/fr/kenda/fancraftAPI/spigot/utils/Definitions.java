package fr.kenda.fancraftAPI.spigot.utils;

public enum Definitions {

    PREFIX("&f[Fun&cKraft&f] ");


    private final String string;

    public String toString() {
        return string;
    }

    Definitions(String string) {
        this.string = string;
    }
}
