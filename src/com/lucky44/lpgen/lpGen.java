package com.lucky44.lpgen;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class lpGen extends JavaPlugin
{
    private final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable(){
        log.info("[Lucky Plot Generator]" + getDescription().getFullName() + " enabled");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
        return new lpChunkGenerator();
    }
}
