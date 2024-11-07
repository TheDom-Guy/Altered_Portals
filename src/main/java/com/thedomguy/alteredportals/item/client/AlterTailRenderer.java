package com.thedomguy.alteredportals.item.client;

import com.thedomguy.alteredportals.item.AlterTailItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AlterTailRenderer extends GeoItemRenderer<AlterTailItem> {
    public AlterTailRenderer() {
        super(new AlterTailModel());
    }
}
