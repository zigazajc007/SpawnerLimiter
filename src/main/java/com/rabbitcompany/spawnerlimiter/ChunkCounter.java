package com.rabbitcompany.spawnerlimiter;

import org.bukkit.Chunk;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChunkCounter {

    public int getChunkSpawners(Chunk chunk) {
        return SpawnerLimiter.getInstance().getData().getInt(getHash(chunk), 0);
    }

    public void setChunkSpawners(Chunk chunk, int amount){
        if(amount <= 0){
            SpawnerLimiter.getInstance().getData().set(getHash(chunk), null);
            SpawnerLimiter.getInstance().saveData();
            return;
        }
        SpawnerLimiter.getInstance().getData().set(getHash(chunk), amount);
        SpawnerLimiter.getInstance().saveData();
    }

    public void incrementChunkSpawners(Chunk chunk) {
        setChunkSpawners(chunk, getChunkSpawners(chunk) + 1);
    }

    public void decrementChunkSpawners(Chunk chunk) {
        setChunkSpawners(chunk, getChunkSpawners(chunk) - 1);
    }

    public static String getHash(Chunk chunk) {
        try {
            return bytesToHex(MessageDigest.getInstance("MD5").digest((chunk.getWorld().getName() + chunk.getX() + "" + chunk.getZ()).getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
