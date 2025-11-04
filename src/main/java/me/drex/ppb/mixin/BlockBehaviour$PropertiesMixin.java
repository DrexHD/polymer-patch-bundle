package me.drex.ppb.mixin;

import me.drex.ppb.PolymerPatchBundleMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.Properties.class)
public abstract class BlockBehaviour$PropertiesMixin {
    @Shadow
    public abstract BlockBehaviour.Properties noOcclusion();

    @Inject(
        method = "setId",
        at = @At("RETURN")
    )
    public void disableOcclusion(ResourceKey<Block> resourceKey, CallbackInfoReturnable<BlockBehaviour.Properties> cir) {
        ResourceLocation id = resourceKey.location();
        if (!PolymerPatchBundleMod.MOD_NAMESPACES.contains(id.getNamespace())) {
            return;
        }
        this.noOcclusion();
    }
}
