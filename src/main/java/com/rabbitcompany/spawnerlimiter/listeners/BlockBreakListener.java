package com.rabbitcompany.spawnerlimiter.listeners;

import com.rabbitcompany.spawnerlimiter.SpawnerLimiter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final SpawnerLimiter spawnerLimiter;

    public BlockBreakListener(SpawnerLimiter plugin){
        spawnerLimiter = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.SPAWNER)
            spawnerLimiter.chunkCounter.decrementChunkSpawners(e.getBlock().getChunk());
    }

}
