package com.rabbitcompany.spawnerlimiter;

import com.rabbitcompany.spawnerlimiter.listeners.BlockBreakListener;
import com.rabbitcompany.spawnerlimiter.listeners.BlockExplodeListener;
import com.rabbitcompany.spawnerlimiter.listeners.BlockPlaceListener;
import com.rabbitcompany.spawnerlimiter.listeners.EntityExplodeListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SpawnerLimiter extends JavaPlugin {

    private static SpawnerLimiter instance;

    private File c = null;
    private final YamlConfiguration conf = new YamlConfiguration();

    private File d = null;
    private final YamlConfiguration data = new YamlConfiguration();

    public ChunkCounter chunkCounter;
    public int spawners_limit;
    public String message;

    @Override
    public void onEnable() {
        instance = this;

        this.c = new File(getDataFolder(), "config.yml");
        this.d = new File(getDataFolder(), "data.yml");

        mkdirAndLoad(c, conf);
        mkdirAndLoad(d, data);

        spawners_limit = getConf().getInt("spawners_limit", 5);
        message = ChatColor.translateAlternateColorCodes('&', ""+getConf().getString("too_many_spawners", "&cThis chunk has exceeded your maximum limit of {limit} spawners."));
        chunkCounter = new ChunkCounter();

        new BlockBreakListener(this);
        new BlockExplodeListener(this);
        new BlockPlaceListener(this);
        new EntityExplodeListener(this);
    }

    @Override
    public void onDisable() {}

    private void mkdirAndLoad(File file, YamlConfiguration conf) {
        if (!file.exists()) {
            saveResource(file.getName(), false);
        }

        try {
            conf.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConf() {
        return this.conf;
    }
    public YamlConfiguration getData() {
        return this.data;
    }

    public void saveData() {
        try {
            this.data.save(this.d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SpawnerLimiter getInstance() { return instance; }
}
