package com.github.thedomguy.alteredportals.item.client;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.github.thedomguy.alteredportals.block.entity.VoidNexusBlockEntity;
import com.github.thedomguy.alteredportals.item.VoidNexusBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class VoidNexusBlockItemModel extends GeoModel<VoidNexusBlockItem> {
    @Override
    public Identifier getModelResource(VoidNexusBlockItem voidNexusBlockItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "geo/void_nexus.geo.json");
    }

    @Override
    public Identifier getTextureResource(VoidNexusBlockItem voidNexusBlockItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "textures/block/void_nexus.png");
    }

    @Override
    public Identifier getAnimationResource(VoidNexusBlockItem voidNexusBlockItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "animations/void_nexus.animation.json");
    }
}
