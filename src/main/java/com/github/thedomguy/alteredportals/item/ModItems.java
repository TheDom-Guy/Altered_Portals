package com.github.thedomguy.alteredportals.item;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.github.thedomguy.alteredportals.block.ModBlocks;
import com.github.thedomguy.alteredportals.util.component.ModComponents;
import com.github.thedomguy.alteredportals.util.component.VoidNexusLocationComponent;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModItems {
    public static final Item ALTER_TAIL = registerItem("alter_tail", new AlterTailItem(new Item.Settings().maxDamage(100)));
    public static final Item BANNER_CARD = registerItem("banner_card", new BannerCardItem(new Item.Settings().maxCount(1).component(ModComponents.BANNER_CARD_TYPE, 0).component(ModComponents.NEXUS_LOCATION, new VoidNexusLocationComponent(new BlockPos(0,0,0)))));
    public static final Item VOID_NEXUS_BLOCK_ITEM = registerItem("void_nexus", new VoidNexusBlockItem(ModBlocks.VOID_NEXUS, new Item.Settings()));

    //Adds items to the Tools & Utilities creative tab
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ALTER_TAIL);
        entries.add(BANNER_CARD);
        entries.add(VOID_NEXUS_BLOCK_ITEM);
    }

    //Item Registration
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AlteredPortals.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AlteredPortals.LOGGER.info("Registering items for " + AlteredPortals.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }

}
