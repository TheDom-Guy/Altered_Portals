package com.github.thedomguy.alteredportals.block.entity;

import com.github.thedomguy.alteredportals.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.RenderUtil;

public class VoidNexusBlockEntity extends BlockEntity implements GeoBlockEntity {
    public VoidNexusBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VOID_NEXUS_BLOCK_ENTITY, pos, state);
    }

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, state -> {
            VoidNexusBlockEntity entity = state.getAnimatable();
            World world = entity.getWorld();
            if (world.getBlockState(entity.getPos().up(1)).isIn(ModTags.Blocks.VOID_PASSAGE_EXITABLE)) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("Activate").thenLoop("Active Idle"));
            }
            return state.setAndContinue(RawAnimation.begin().thenPlay("Deactivate").thenLoop("inactive Idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }
}
