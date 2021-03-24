package com.rabbitcompany.spawnerlimiter.listeners;

import com.rabbitcompany.spawnerlimiter.SpawnerLimiter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class BlockExplodeListener implements Listener {

    private final SpawnerLimiter spawnerLimiter;

    public BlockExplodeListener(SpawnerLimiter plugin){
        spawnerLimiter = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e){
        for (Block block : e.blockList()) {
            if (block.getType() == Material.SPAWNER)
                spawnerLimiter.chunkCounter.decrementChunkSpawners(block.getChunk());
        }
    }
}
