package me.drex.ppb.color.mod;

import com.brand.blockus.registry.content.BlockusBlocks;
import me.drex.ppb.color.BiomeColors;
import me.drex.ppb.color.ColorProviderRegistry;

public class BlockusColorProvider {
    public static void register() {
        ColorProviderRegistry.register(
            (blockState, levelChunk, pos) ->
                BiomeColors.getAverageFoliageColor(levelChunk, pos),
            BlockusBlocks.OAK_HEDGE,
            BlockusBlocks.JUNGLE_HEDGE,
            BlockusBlocks.ACACIA_HEDGE,
            BlockusBlocks.DARK_OAK_HEDGE,
            BlockusBlocks.MANGROVE_HEDGE,

            BlockusBlocks.POTTED_OAK.block(),
            BlockusBlocks.POTTED_JUNGLE.block(),
            BlockusBlocks.POTTED_ACACIA.block(),
            BlockusBlocks.POTTED_DARK_OAK.block(),
            BlockusBlocks.POTTED_MANGROVE.block(),

            BlockusBlocks.RAINBOW_PETALS
        );
        ColorProviderRegistry.register(
            (blockState, levelChunk, pos) -> -10380959,
            BlockusBlocks.SPRUCE_HEDGE,
            BlockusBlocks.POTTED_SPRUCE.block()
        );
        ColorProviderRegistry.register(
            (blockState, levelChunk, pos) -> -10380959,
            BlockusBlocks.BIRCH_HEDGE,
            BlockusBlocks.POTTED_BIRCH.block()
        );
        ColorProviderRegistry.register(
            (blockState, levelChunk, pos) ->
                BiomeColors.getAverageGrassColor(levelChunk, pos),
            BlockusBlocks.POTTED_LARGE_FERN.block()
        );
    }
}
