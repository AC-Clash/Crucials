package me.acclashcorporation.crucials;

import me.acclashcorporation.crucials.commands.*;
import me.acclashcorporation.crucials.events.BanInventoryListener;
import me.acclashcorporation.crucials.files.Points;
import org.bukkit.plugin.java.JavaPlugin;

public final class Crucials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Points.setup(getDataFolder());
        Points.get().options().copyDefaults();
        Points.save();
        getCommand("bangui").setExecutor(new BanGUI());
        getCommand("tpplayer").setExecutor(new Teleport());
        getCommand("tpall").setExecutor(new TeleportAll());
        getCommand("setwarp").setExecutor(new SetTeleportWarp(this));
        getCommand("warp").setExecutor(new TeleportWarp(this));
        getServer().getPluginManager().registerEvents(new BanInventoryListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}


