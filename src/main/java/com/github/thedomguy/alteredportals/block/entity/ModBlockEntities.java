package com.github.thedomguy.alteredportals.block.entity;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.github.thedomguy.alteredportals.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<VoidNexusBlockEntity> VOID_NEXUS_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of("void_nexus_block_entity"),
            FabricBlockEntityTypeBuilder.create(VoidNexusBlockEntity::new, ModBlocks.VOID_NEXUS).build());

    public static void registerBlockEntities() {
        AlteredPortals.LOGGER.info("Registering block entities for " + AlteredPortals.MOD_ID);
    }
}
