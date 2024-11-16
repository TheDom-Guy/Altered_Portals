package com.github.thedomguy.alteredportals.util;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public class ModProperties {
    public static final BooleanProperty REVERSED = BooleanProperty.of("reversed");
    public static final IntProperty PORTAL_DISTANCE = IntProperty.of("portal_distance", 0, 15);
    public static final BooleanProperty STRING_CONNECTED = BooleanProperty.of("string_connected");
}
