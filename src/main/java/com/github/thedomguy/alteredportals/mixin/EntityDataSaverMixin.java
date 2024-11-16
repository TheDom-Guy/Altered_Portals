package com.github.thedomguy.alteredportals.mixin;

import com.github.thedomguy.alteredportals.util.CustomPortalCooldownData;
import com.github.thedomguy.alteredportals.util.EntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements EntityDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound GetPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }

        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if (persistentData != null) {
            nbt.put("alteredportals.thedomguy.data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("alteredportals.thedomguy.data", 10)) {
            persistentData = nbt.getCompound("alteredportals.thedomguy.data");
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    protected void injectTickMethod(CallbackInfo info) {
        Entity entity = (Entity)(Object)this;
        CustomPortalCooldownData.decrementCustomPortalCooldown(((EntityDataSaver) entity));
    }
}
