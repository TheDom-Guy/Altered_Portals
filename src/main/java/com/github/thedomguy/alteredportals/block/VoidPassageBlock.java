package com.github.thedomguy.alteredportals.block;

import com.github.thedomguy.alteredportals.sound.ModSounds;
import com.github.thedomguy.alteredportals.util.CustomPortalCooldownData;
import com.github.thedomguy.alteredportals.util.EntityDataSaver;
import com.github.thedomguy.alteredportals.util.ModProperties;
import com.github.thedomguy.alteredportals.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VoidPassageBlock extends Block implements Portal{
    public VoidPassageBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.FACING, Direction.NORTH).with(ModProperties.PORTAL_DISTANCE, 0).with(ModProperties.STRING_CONNECTED, false));
    }

    private BlockPos targetPos;

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, state.getBlock(), 100);
        world.playSound(null, pos, ModSounds.VOID_PASSAGE_OPEN, SoundCategory.BLOCKS);

        if (state.get(Properties.FACING) == Direction.DOWN) {
            boolean reverseString = false;

            for (int i = 1; i <= 10; i++) {
                BlockPos targetPos = pos.add(Direction.DOWN.getVector().multiply(i));
                BlockState targetState = world.getBlockState(targetPos);

                if (targetState.isIn(ModTags.Blocks.VOID_PASSAGE_REPLACEABLE)) {
                    world.setBlockState(targetPos, ModBlocks.VOID_STRING.getDefaultState().with(ModProperties.REVERSED, reverseString));
                    world.setBlockState(pos, state.with(ModProperties.STRING_CONNECTED, true));
                    if (reverseString) {
                        reverseString = false;
                    } else {
                        reverseString = true;
                    }
                } else {
                    break;
                }
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.playSound(null, pos, ModSounds.VOID_PASSAGE_CLOSE, SoundCategory.BLOCKS);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0,0,0,16,16,16);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING).add(ModProperties.PORTAL_DISTANCE).add(ModProperties.STRING_CONNECTED);
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient) {
            if (!CustomPortalCooldownData.hasCustomPortalCooldown(((EntityDataSaver) entity))) {
                Direction dir = state.get(Properties.FACING).getOpposite();
                targetPos = pos.add(dir.getVector().multiply(state.get(ModProperties.PORTAL_DISTANCE)));

                world.playSound(null, pos, ModSounds.VOID_PASSAGE_PHASE, SoundCategory.BLOCKS);
                world.playSound(null, targetPos, ModSounds.VOID_PASSAGE_PHASE, SoundCategory.BLOCKS);

                CustomPortalCooldownData.setCustomPortalCooldown(((EntityDataSaver) entity), 25);

                if (entity.canUsePortals(false)) {
                    entity.tryUsePortal(this, pos);
                }
            }
        }
    }

    @Nullable
    @Override
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        if (world.getBlockState(targetPos.down(1)).isIn(ModTags.Blocks.VOID_PASSAGE_EXITABLE)) {
            return new TeleportTarget(world, targetPos.down(1).toBottomCenterPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP);
        } else {
            return new TeleportTarget(world, targetPos.toBottomCenterPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP);
        }
    }
}
