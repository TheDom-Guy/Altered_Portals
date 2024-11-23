package com.github.thedomguy.alteredportals.item.client;

import com.github.thedomguy.alteredportals.item.VoidNexusBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class VoidNexusBlockItemRenderer extends GeoItemRenderer<VoidNexusBlockItem> {
    public VoidNexusBlockItemRenderer() {
        super(new VoidNexusBlockItemModel());
    }
}
