package com.github.thedomguy.alteredportals;

import com.github.thedomguy.alteredportals.block.ModBlocks;
import com.github.thedomguy.alteredportals.block.client.VoidNexusRenderer;
import com.github.thedomguy.alteredportals.block.entity.ModBlockEntities;
import com.github.thedomguy.alteredportals.item.ModItems;
import com.github.thedomguy.alteredportals.util.component.ModComponents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class AlteredPortalsClient implements ClientModInitializer {
    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ModItems.BANNER_CARD, Identifier.ofVanilla("type"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (itemStack.contains(ModComponents.BANNER_CARD_TYPE)) {
                return (float) itemStack.get(ModComponents.BANNER_CARD_TYPE) / 10;
            }

            return 0.0F;
        });
    }

    @Override
    public void onInitializeClient() {
        registerModelPredicateProviders();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_PASSAGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VOID_STRING, RenderLayer.getCutout());
        BlockEntityRendererRegistry.register(ModBlockEntities.VOID_NEXUS_BLOCK_ENTITY, VoidNexusRenderer::new);
    }
}
