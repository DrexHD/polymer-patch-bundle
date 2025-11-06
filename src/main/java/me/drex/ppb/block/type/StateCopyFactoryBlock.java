package me.drex.ppb.block.type;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.block.model.SignModel;
import eu.pb4.factorytools.api.block.model.generic.BSMMParticleBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import me.drex.ppb.block.BlockStateModel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.function.BiFunction;

public record StateCopyFactoryBlock(Block clientBlock,
                                    BiFunction<BlockState, BlockPos, BlockModel> modelFunction) implements FactoryBlock, PolymerTexturedBlock, BSMMParticleBlock {
    public static final StateCopyFactoryBlock SIGN = new StateCopyFactoryBlock(Blocks.BIRCH_SIGN, SignModel::new);
    public static final StateCopyFactoryBlock WALL_SIGN = new StateCopyFactoryBlock(Blocks.BIRCH_WALL_SIGN, SignModel::new);
    public static final StateCopyFactoryBlock HANGING_SIGN = new StateCopyFactoryBlock(Blocks.BIRCH_HANGING_SIGN, SignModel::new);
    public static final StateCopyFactoryBlock HANGING_WALL_SIGN = new StateCopyFactoryBlock(Blocks.BIRCH_WALL_HANGING_SIGN, SignModel::new);

    public static final StateCopyFactoryBlock WALL = new StateCopyFactoryBlock(Blocks.SANDSTONE_WALL, (state, blockPos) -> BlockStateModel.midRange().shifty());
    public static final StateCopyFactoryBlock STAIR = new StateCopyFactoryBlock(Blocks.SANDSTONE_STAIRS, (state, blockPos) -> BlockStateModel.midRange().shifty());
    public static final StateCopyFactoryBlock FENCE = new StateCopyFactoryBlock(Blocks.BIRCH_FENCE, (state, blockPos) -> BlockStateModel.midRange().shifty());
    public static final StateCopyFactoryBlock FENCE_GATE = new StateCopyFactoryBlock(Blocks.BIRCH_FENCE_GATE, (state, blockPos) -> BlockStateModel.midRange().shifty());
    public static final StateCopyFactoryBlock BUTTON = new StateCopyFactoryBlock(Blocks.STONE_BUTTON, (state, blockPos) -> BlockStateModel.shortRange().shifty());
    public static final StateCopyFactoryBlock PRESSURE_PLATE = new StateCopyFactoryBlock(Blocks.STONE_PRESSURE_PLATE, (state, blockPos) -> BlockStateModel.shortRange().shifty());
    public static final StateCopyFactoryBlock CARPET = new StateCopyFactoryBlock(Blocks.WHITE_CARPET, (state, blockPos) -> BlockStateModel.shortRange().shifty());
    public static final StateCopyFactoryBlock SHELF = new StateCopyFactoryBlock(Blocks.BIRCH_SHELF, (state, blockPos) -> BlockStateModel.shortRange().shifty());
    public static final StateCopyFactoryBlock TRAPDOOR = new StateCopyFactoryBlock(Blocks.IRON_TRAPDOOR, (state, blockPos) -> BlockStateModel.midRange().shifty());

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return clientBlock.withPropertiesOf(state);
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        return this.modelFunction.apply(initialBlockState, pos);
    }

    @Override
    public boolean isIgnoringBlockInteractionPlaySoundExceptedEntity(BlockState state, ServerPlayer player, InteractionHand hand, ItemStack stack, ServerLevel world, BlockHitResult blockHitResult) {
        return true;
    }
}
