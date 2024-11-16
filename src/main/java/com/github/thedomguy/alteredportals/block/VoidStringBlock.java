package com.github.thedomguy.alteredportals.block;

import com.github.thedomguy.alteredportals.sound.ModSounds;
import com.github.thedomguy.alteredportals.util.CustomPortalCooldownData;
import com.github.thedomguy.alteredportals.util.EntityDataSaver;
import com.github.thedomguy.alteredportals.util.ModProperties;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VoidStringBlock extends Block implements Portal{
    public VoidStringBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ModProperties.REVERSED, false));
    }

    private BlockPos targetPos;

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, state.getBlock(), 100);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(6,0,6,10,16,10);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ModProperties.REVERSED);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean startPortalFound = false;
        if (!world.isClient) {
            for (int i = 1; i <= 25; i++) {
                targetPos = pos.up(i);
                BlockState targetState = world.getBlockState(targetPos);

                if (targetState.isOf(ModBlocks.VOID_PASSAGE)) {
                    if (startPortalFound) {

                        world.playSound(null, pos, ModSounds.VOID_PASSAGE_PHASE, SoundCategory.BLOCKS);
                        world.playSound(null, targetPos, ModSounds.VOID_PASSAGE_PHASE, SoundCategory.BLOCKS);

                        CustomPortalCooldownData.setCustomPortalCooldown(((EntityDataSaver) player), 25);

                        if (player.canUsePortals(false)) {
                            player.tryUsePortal(this, pos);
                        }
                        break;
                    } else {
                        startPortalFound = true;
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        return new TeleportTarget(world, targetPos.toBottomCenterPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP);
    }
}
