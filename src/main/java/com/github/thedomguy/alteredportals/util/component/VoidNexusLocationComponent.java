package com.github.thedomguy.alteredportals.util.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;

public record VoidNexusLocationComponent(BlockPos nexusLocation) {
    public static final Codec<VoidNexusLocationComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                BlockPos.CODEC.fieldOf("nexus_location").forGetter(VoidNexusLocationComponent::nexusLocation)
        ).apply(builder, VoidNexusLocationComponent::new);
    });
}
