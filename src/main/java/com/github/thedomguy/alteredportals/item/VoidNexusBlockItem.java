package com.github.thedomguy.alteredportals.item;

import com.github.thedomguy.alteredportals.item.client.AlterTailRenderer;
import com.github.thedomguy.alteredportals.item.client.VoidNexusBlockItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.BlockItem;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.RenderUtil;

import java.util.function.Consumer;

public class VoidNexusBlockItem extends BlockItem implements GeoItem {
    public VoidNexusBlockItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "void_nexus_controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("activate", RawAnimation.begin().thenPlay("Activate"))
                .triggerableAnim("deactivate", RawAnimation.begin().thenPlay("Deactivate"))
                .triggerableAnim("active_idle", RawAnimation.begin().thenPlay("Active Idle"))
                .triggerableAnim("inactive_idle", RawAnimation.begin().thenPlay("Inactive Idle"))
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentTick();
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final VoidNexusBlockItemRenderer renderer = new VoidNexusBlockItemRenderer();

            @Override
            public @Nullable BuiltinModelItemRenderer getGeoItemRenderer() {
                return this.renderer;
            }
        });
    }
}
