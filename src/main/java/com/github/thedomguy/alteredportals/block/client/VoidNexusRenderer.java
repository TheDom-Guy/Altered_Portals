package com.github.thedomguy.alteredportals.block.client;

import com.github.thedomguy.alteredportals.block.entity.VoidNexusBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class VoidNexusRenderer extends GeoBlockRenderer<VoidNexusBlockEntity> {
    public VoidNexusRenderer(BlockEntityRendererFactory.Context context) {
        super(new VoidNexusModel());
    }
}
