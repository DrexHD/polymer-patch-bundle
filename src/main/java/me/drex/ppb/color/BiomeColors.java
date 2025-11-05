package me.drex.ppb.color;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunk;

public class BiomeColors {
    public static final ColorResolver GRASS_COLOR_RESOLVER = Biome::getGrassColor;
    public static final ColorResolver FOLIAGE_COLOR_RESOLVER = (biome, d, e) -> biome.getFoliageColor();
    public static final ColorResolver DRY_FOLIAGE_COLOR_RESOLVER = (biome, d, e) -> biome.getDryFoliageColor();
    public static final ColorResolver WATER_COLOR_RESOLVER = (biome, d, e) -> biome.getWaterColor();

    private static int getAverageColor(LevelChunk chunk, BlockPos blockPos, ColorResolver colorResolver) {
        Holder<Biome> biome = chunk.getNoiseBiome(QuartPos.fromBlock(blockPos.getX()), QuartPos.fromBlock(blockPos.getY()), QuartPos.fromBlock(blockPos.getZ()));
        return colorResolver.getColor(biome.value(), blockPos.getX(), blockPos.getZ());
    }

    public static int getAverageGrassColor(LevelChunk chunk, BlockPos blockPos) {
        return BiomeColors.getAverageColor(chunk, blockPos, GRASS_COLOR_RESOLVER);
    }

    public static int getAverageFoliageColor(LevelChunk chunk, BlockPos blockPos) {
        return BiomeColors.getAverageColor(chunk, blockPos, FOLIAGE_COLOR_RESOLVER);
    }

    public static int getAverageDryFoliageColor(LevelChunk chunk, BlockPos blockPos) {
        return BiomeColors.getAverageColor(chunk, blockPos, DRY_FOLIAGE_COLOR_RESOLVER);
    }

    public static int getAverageWaterColor(LevelChunk chunk, BlockPos blockPos) {
        return BiomeColors.getAverageColor(chunk, blockPos, WATER_COLOR_RESOLVER);
    }
}
