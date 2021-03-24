package com.rabbitcompany.spawnerlimiter.listeners;

import com.rabbitcompany.spawnerlimiter.SpawnerLimiter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    private final SpawnerLimiter spawnerLimiter;

    public EntityExplodeListener(SpawnerLimiter plugin){
        spawnerLimiter = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        for (Block block : e.blockList()) {
            if (block.getType() == Material.SPAWNER)
                spawnerLimiter.chunkCounter.decrementChunkSpawners(block.getChunk());
        }
    }
}
