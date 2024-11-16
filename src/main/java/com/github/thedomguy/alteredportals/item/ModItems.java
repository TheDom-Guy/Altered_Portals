package com.github.thedomguy.alteredportals.item;

import com.github.thedomguy.alteredportals.AlteredPortals;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item ALTER_TAIL = registerItem("alter_tail", new AlterTailItem(new Item.Settings().maxDamage(100)));

    //Adds items to the Tools & Utilities creative tab
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ALTER_TAIL);
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
