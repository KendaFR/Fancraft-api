package fr.kenda.fancraftAPI.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullBuilder {

    private final Player owner;
    private final OfflinePlayer offlinePlayer;
    private String name;
    private List<String> lores = new ArrayList<>();

    public SkullBuilder(Player owner) {
        this.owner = owner;
        this.offlinePlayer = null;
        this.name = "§e" + owner.getName();
    }

    @SuppressWarnings("deprecation")
    public SkullBuilder(String owner) {
        this.owner = null;
        this.offlinePlayer = Bukkit.getOfflinePlayer(owner);
        this.name = "§e" + owner;
    }

    public SkullBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SkullBuilder setLore(String line) {
        lores.add(line);
        return this;
    }

    public SkullBuilder setLores(List<String> lines) {
        lores = lines;
        return this;
    }

    public SkullBuilder addLine(String line) {
        lores.add(line);
        return this;
    }

    public ItemStack toItemStack() {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        assert skullMeta != null;
        skullMeta.setOwner(owner == null ? offlinePlayer.getName() : owner.getName());
        if (name != null) skullMeta.setDisplayName(name);
        if (!lores.isEmpty()) skullMeta.setLore(lores);
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
