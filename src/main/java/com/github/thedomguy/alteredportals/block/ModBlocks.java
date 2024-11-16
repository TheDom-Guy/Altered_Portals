package com.github.thedomguy.alteredportals.block;

import com.github.thedomguy.alteredportals.AlteredPortals;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block VOID_PASSAGE = registerBlock("void_passage", new VoidPassageBlock(AbstractBlock.Settings.create().noCollision().nonOpaque().strength(-1.0F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.BLOCK)));
    public static final Block VOID_STRING = registerBlock("void_string", new VoidStringBlock(AbstractBlock.Settings.create().noCollision().nonOpaque().strength(-1.0F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.BLOCK)));

    //Block Registration
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AlteredPortals.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(AlteredPortals.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        AlteredPortals.LOGGER.info("Registering blocks for " + AlteredPortals.MOD_ID);
    }
}
