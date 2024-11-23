package com.github.thedomguy.alteredportals.block.client;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.github.thedomguy.alteredportals.block.entity.VoidNexusBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class VoidNexusModel extends GeoModel<VoidNexusBlockEntity> {
    @Override
    public Identifier getModelResource(VoidNexusBlockEntity voidNexusBlockEntity) {
        return Identifier.of(AlteredPortals.MOD_ID, "geo/void_nexus.geo.json");
    }

    @Override
    public Identifier getTextureResource(VoidNexusBlockEntity voidNexusBlockEntity) {
        return Identifier.of(AlteredPortals.MOD_ID, "textures/block/void_nexus.png");
    }

    @Override
    public Identifier getAnimationResource(VoidNexusBlockEntity voidNexusBlockEntity) {
        return Identifier.of(AlteredPortals.MOD_ID, "animations/void_nexus.animation.json");
    }
}
