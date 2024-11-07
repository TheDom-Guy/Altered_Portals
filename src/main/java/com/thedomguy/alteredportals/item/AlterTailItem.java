package com.thedomguy.alteredportals.item;

import com.thedomguy.alteredportals.AlteredPortals;
import com.thedomguy.alteredportals.block.ModBlocks;
import com.thedomguy.alteredportals.item.client.AlterTailRenderer;
import com.thedomguy.alteredportals.sound.ModSounds;
import com.thedomguy.alteredportals.util.ModProperties;
import com.thedomguy.alteredportals.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.function.Consumer;

public class AlterTailItem extends Item implements GeoItem {
    public AlterTailItem(Settings settings) {
        super(settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }



    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {

            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            Direction portalDir = context.getSide();

            if (!context.getWorld().getBlockState(positionClicked).isIn(ModTags.Blocks.VOID_PASSAGE_UNUSABLE) && context.getWorld().getBlockState(positionClicked.add(portalDir.getVector().multiply(1))).isIn(ModTags.Blocks.VOID_PASSAGE_REPLACEABLE)) {
                boolean foundPos = false;

                for(int i = 0; i <= 10; i++) {
                    BlockState state = context.getWorld().getBlockState(positionClicked.add(portalDir.getVector().multiply(-i)));
                    if (state.isIn(ModTags.Blocks.VOID_PASSAGE_BLOCKING)) {
                        player.sendMessage(Text.translatable("item.alteredportals.alter_tail.blockage_error"), true);
                        context.getWorld().playSound(null, context.getPlayer().getBlockPos(), ModSounds.ALTER_TAIL_FAIL, SoundCategory.PLAYERS);
                        foundPos = true;
                        break;
                    }

                    if(state.isIn(ModTags.Blocks.VOID_PASSAGE_REPLACEABLE)) {
                        BlockPos startPos = positionClicked.add(portalDir.getVector().multiply(1));
                        BlockPos endPos = positionClicked.add(portalDir.getVector().multiply(-i));

                        if (context.getWorld() instanceof ServerWorld serverWorld) {
                            triggerAnim(player, GeoItem.getOrAssignId(player.getStackInHand(context.getHand()), serverWorld), "alter_tail_controller", "fire_portal");
                            AlteredPortals.LOGGER.info("Anim Triggered");
                        }

                        player.getItemCooldownManager().set(this, 20);

                        context.getWorld().playSound(null, context.getPlayer().getBlockPos(), ModSounds.ALTER_TAIL_USE, SoundCategory.PLAYERS);

                        context.getStack().damage(1, player, context.getPlayer().getPreferredEquipmentSlot(context.getStack()));

                        AlteredPortals.LOGGER.info("Block Placing Start");
                        context.getWorld().setBlockState(startPos, ModBlocks.VOID_PASSAGE.getDefaultState().with(Properties.FACING, portalDir).with(ModProperties.PORTAL_DISTANCE, i + 1));
                        context.getWorld().setBlockState(endPos, ModBlocks.VOID_PASSAGE.getDefaultState().with(Properties.FACING, portalDir.getOpposite()).with(ModProperties.PORTAL_DISTANCE, i + 1));
                        AlteredPortals.LOGGER.info("Block Placing Done");

                        foundPos = true;
                        break;
                    }
                }

                if(!foundPos) {
                    player.sendMessage(Text.translatable("item.alteredportals.alter_tail.distance_error"), true);
                    context.getWorld().playSound(null, context.getPlayer().getBlockPos(), ModSounds.ALTER_TAIL_FAIL, SoundCategory.PLAYERS);
                }
            } else {
                context.getPlayer().sendMessage(Text.translatable("item.alteredportals.alter_tail.start_error"), true);
                context.getWorld().playSound(null, context.getPlayer().getBlockPos(), ModSounds.ALTER_TAIL_FAIL, SoundCategory.PLAYERS);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "alter_tail_controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("fire_portal", RawAnimation.begin().thenPlay("Fire Portal")));
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
            private final AlterTailRenderer renderer = new AlterTailRenderer();

            @Override
            public @Nullable BuiltinModelItemRenderer getGeoItemRenderer() {
                return this.renderer;
            }
        });
    }
}
