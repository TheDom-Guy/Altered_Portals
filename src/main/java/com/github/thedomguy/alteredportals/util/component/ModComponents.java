package com.github.thedomguy.alteredportals.util.component;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Integer> BANNER_CARD_TYPE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(AlteredPortals.MOD_ID, "banner_card_type"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<VoidNexusLocationComponent> NEXUS_LOCATION = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(AlteredPortals.MOD_ID, "nexus_location"),
            ComponentType.<VoidNexusLocationComponent>builder().codec(VoidNexusLocationComponent.CODEC).build()
    );

    public static void registerModComponents() {
        AlteredPortals.LOGGER.info("Registering item components for " + AlteredPortals.MOD_ID);
    }
}
