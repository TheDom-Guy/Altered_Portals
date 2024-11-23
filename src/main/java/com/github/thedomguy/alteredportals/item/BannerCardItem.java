package com.github.thedomguy.alteredportals.item;

import com.github.thedomguy.alteredportals.block.ModBlocks;
import com.github.thedomguy.alteredportals.util.ModTags;
import com.github.thedomguy.alteredportals.util.component.ModComponents;
import com.github.thedomguy.alteredportals.util.component.VoidNexusLocationComponent;
import net.minecraft.block.BlockState;
import net.minecraft.block.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BannerCardItem extends Item implements Portal {
    public BannerCardItem(Settings settings) {
        super(settings);
    }

    private BlockPos nexusLocation;

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (!context.getWorld().isClient()) {
            BlockState usedBlock = context.getWorld().getBlockState(context.getBlockPos());

            if (context.getStack().get(ModComponents.BANNER_CARD_TYPE) == 0 && usedBlock.isOf(ModBlocks.VOID_NEXUS)) {
                context.getStack().set(ModComponents.BANNER_CARD_TYPE, 1);
                context.getStack().set(ModComponents.NEXUS_LOCATION, new VoidNexusLocationComponent(context.getBlockPos()));
                player.sendMessage(Text.translatable("item.alteredportals.banner_card.nexus_set"), true);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (itemStack.get(ModComponents.BANNER_CARD_TYPE) == 1 && itemStack.contains(ModComponents.NEXUS_LOCATION)) {
                nexusLocation = itemStack.get(ModComponents.NEXUS_LOCATION).nexusLocation();
                if (world.getBlockState(nexusLocation.up(1)).isIn(ModTags.Blocks.VOID_PASSAGE_EXITABLE)) {
                    user.tryUsePortal(this, user.getBlockPos());
                    itemStack.set(ModComponents.BANNER_CARD_TYPE, 0);
                } else {
                    user.sendMessage(Text.translatable("item.alteredportals.banner_card.nexus_blocked"), true);
                }
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModComponents.BANNER_CARD_TYPE) == 1) {
            if (world.getBlockState(stack.get(ModComponents.NEXUS_LOCATION).nexusLocation()) != ModBlocks.VOID_NEXUS.getDefaultState()) {
                stack.set(ModComponents.BANNER_CARD_TYPE, 0);
                if (entity instanceof PlayerEntity player) {
                    player.sendMessage(Text.translatable("item.alteredportals.banner_card.nexus_destroyed"), true);
                }
            }
        }
    }

    @Nullable
    @Override
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        return new TeleportTarget(world, nexusLocation.toBottomCenterPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        switch (stack.get(ModComponents.BANNER_CARD_TYPE)) {
            case 0:
                tooltip.add(Text.translatable("item.alteredportals.banner_card.tooltip.empty_card").formatted(Formatting.GRAY));
                break;
            case 1:
                BlockPos nexusLocRef = stack.get(ModComponents.NEXUS_LOCATION).nexusLocation();
                tooltip.add(Text.translatable("item.alteredportals.banner_card.tooltip.nexus_card", nexusLocRef.getX(), nexusLocRef.getY(), nexusLocRef.getZ()).formatted(Formatting.LIGHT_PURPLE));
                break;
        }

    }
}
