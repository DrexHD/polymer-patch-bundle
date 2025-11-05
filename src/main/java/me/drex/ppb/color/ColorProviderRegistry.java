package me.drex.ppb.color;

import me.drex.ppb.color.mod.BlockusColorProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.IdMapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

public class ColorProviderRegistry {
    private static final IdMapper<BlockColor> blockColors = new IdMapper<>(32);

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("blockus")) {
            BlockusColorProvider.register();
        }
    }

    public static void register(BlockColor blockColor, Block... blocks) {
        for (Block block : blocks) {
            blockColors.addMapping(blockColor, BuiltInRegistries.BLOCK.getId(block));
        }
    }

    public static int getColor(BlockState blockState, LevelChunk level, BlockPos pos) {
        BlockColor blockColor = blockColors.byId(BuiltInRegistries.BLOCK.getId(blockState.getBlock()));
        if (blockColor != null) {
            return blockColor.getColor(blockState, level, pos);
        }
        return -1;
    }

    public static boolean hasColor(ResourceLocation id) {
        try {
            Block value = BuiltInRegistries.BLOCK.getValue(id);
            return blockColors.byId(BuiltInRegistries.BLOCK.getId(value)) != null;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public interface BlockColor {
        int getColor(BlockState blockState, LevelChunk levelChunk, BlockPos pos);
    }
}
