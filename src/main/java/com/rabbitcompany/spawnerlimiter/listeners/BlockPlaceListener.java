package com.rabbitcompany.spawnerlimiter.listeners;

import com.rabbitcompany.spawnerlimiter.SpawnerLimiter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final SpawnerLimiter spawnerLimiter;

    public BlockPlaceListener(SpawnerLimiter plugin){
        spawnerLimiter = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlockPlaced().getType() != Material.SPAWNER) return;

        if(spawnerLimiter.chunkCounter.getChunkSpawners(e.getBlockPlaced().getChunk()) >= spawnerLimiter.spawners_limit){
            e.setCancelled(true);
            e.getPlayer().sendMessage(spawnerLimiter.message);
        }else{
            spawnerLimiter.chunkCounter.incrementChunkSpawners(e.getBlockPlaced().getChunk());
        }
    }
}
