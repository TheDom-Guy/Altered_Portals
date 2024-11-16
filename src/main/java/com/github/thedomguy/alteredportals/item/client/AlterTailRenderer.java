package com.github.thedomguy.alteredportals.item.client;

import com.github.thedomguy.alteredportals.item.AlterTailItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AlterTailRenderer extends GeoItemRenderer<AlterTailItem> {
    public AlterTailRenderer() {
        super(new AlterTailModel());
    }
}
