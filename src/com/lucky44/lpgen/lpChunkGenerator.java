package com.lucky44.lpgen;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class lpChunkGenerator extends ChunkGenerator
{
    //TODO: Kreuzungsslabs

    Logger log = Logger.getLogger("Minecraft");

    BlockData bedrock = Material.BEDROCK.createBlockData();
    BlockData stone = Material.STONE.createBlockData();
    BlockData way = Material.STONE_BRICKS.createBlockData();
    BlockData dirt = Material.DIRT.createBlockData();
    BlockData grass = Material.GRASS_BLOCK.createBlockData();
    BlockData slab = Material.SMOOTH_QUARTZ_SLAB.createBlockData();

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        try{
            if(chunkX % 2 == 0 && chunkZ % 2 == 0){
                chunk = generateShit(0,0,17,16,chunk);
            }
            else{
                chunk.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK.createBlockData());
                chunk.setRegion(0, 1, 0, 16, 16, 16, way);

                if(chunkX % 2 != 0 && chunkZ % 2 == 0){

                    //Linke seite
                    chunk = generateShit(0,0,4,16,chunk);
                    chunk.setRegion(4,16,0,5,17,16,slab);

                    //Rechte seite
                    chunk = generateShit(12,0,16,16,chunk);
                    chunk.setRegion(11,16,0,12,17,16,slab);
                }
                if(chunkZ % 2 != 0 && chunkX % 2 == 0){

                    //Obere Seite
                    chunk = generateShit(0,12,16,16,chunk);
                    chunk.setRegion(0,16,11,16,17,12,slab);

                    //Untere Seite
                    chunk = generateShit(0,0,16,4,chunk);
                    chunk.setRegion(0,16,4,16,17,5,slab);
                }
                if(chunkZ % 2 != 0 && chunkX % 2 != 0){
                    chunk = generateShit(0,0,16,16,chunk);

                    //Oben - Unten
                    chunk.setRegion(4, 0, 0, 12, 1, 16, Material.BEDROCK.createBlockData());
                    chunk.setRegion(4, 1, 0, 12, 16, 16, way);

                    //Links - Rechts
                    chunk.setRegion(0, 0, 4, 16, 1, 12, Material.BEDROCK.createBlockData());
                    chunk.setRegion(0, 1, 4, 16, 16, 12, way);

                    //1.Corner
                    chunk.setRegion(11,16,11,12,17,16,slab);
                    chunk.setRegion(12,16,11,16,17,12,slab);

                    //2.Corner
                    chunk.setRegion(11,16,4,16,17,5,slab);
                    chunk.setRegion(11,16,0,12,17,4,slab);

                    //3.Corner
                    chunk.setRegion(4,16,11,5,17,16,slab);
                    chunk.setRegion(0,16,11,4,17,12,slab);

                    //4.Corner
                    chunk.setRegion(4,16,0,5,17,4,slab);
                    chunk.setRegion(0,16,4,5,17,5,slab);
                }
            }
        }
        catch(Exception ex){
            chunk.setRegion(0,0,0,16,16,16,Material.RED_CONCRETE);
            log.info("[Lucky Plot Generator] ERROR: " + ex.getMessage() + " CAUSE: " + ex.getCause() + "STACKTRACE: " + Arrays.toString(ex.getStackTrace()));
        }

        return chunk;
    }

    ChunkData generateShit(int x1, int z1, int x2, int z2, ChunkData data){
        data.setRegion(x1, 0, z1, x2, 1, z2, bedrock);
        data.setRegion(x1, 1, z1, x2, 6, z2, stone);
        data.setRegion(x1, 6, z1, x2, 15, z2, dirt);
        data.setRegion(x1, 15, z1, x2, 16, z2, grass);

        return  data;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        if (!world.isChunkLoaded(0, 0)) {
            world.loadChunk(0, 0);
        }

        int highestBlock = world.getHighestBlockYAt(0, 0);

        return new Location(world, 0, highestBlock, 0);
    }
}
