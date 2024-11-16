package com.github.thedomguy.alteredportals.item.client;

import com.github.thedomguy.alteredportals.AlteredPortals;
import com.github.thedomguy.alteredportals.item.AlterTailItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AlterTailModel extends GeoModel<AlterTailItem> {
    @Override
    public Identifier getModelResource(AlterTailItem alterTailItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "geo/alter_tail.geo.json");
    }

    @Override
    public Identifier getTextureResource(AlterTailItem alterTailItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "textures/item/alter_tail.png");
    }

    @Override
    public Identifier getAnimationResource(AlterTailItem alterTailItem) {
        return Identifier.of(AlteredPortals.MOD_ID, "animations/alter_tail.animation.json");
    }
}
