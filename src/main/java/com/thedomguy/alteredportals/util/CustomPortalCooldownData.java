package com.thedomguy.alteredportals.util;

import net.minecraft.nbt.NbtCompound;

public class CustomPortalCooldownData {
    public static int setCustomPortalCooldown(EntityDataSaver entity, int ticks) {
        NbtCompound nbt = entity.GetPersistentData();

        nbt.putInt("cooldown", ticks);

        return ticks;
    }

    public static int decrementCustomPortalCooldown(EntityDataSaver entity) {
        NbtCompound nbt = entity.GetPersistentData();
        int remainingTicks = nbt.getInt("cooldown");

        if (remainingTicks > 0) {
            remainingTicks--;
        }

        nbt.putInt("cooldown", remainingTicks);

        return remainingTicks;
    }

    public static boolean hasCustomPortalCooldown(EntityDataSaver entity) {
        NbtCompound nbt = entity.GetPersistentData();
        int ticks = nbt.getInt("cooldown");

        return ticks > 0;
    }
}
