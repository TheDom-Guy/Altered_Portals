package com.github.thedomguy.alteredportals.sound;

import com.github.thedomguy.alteredportals.AlteredPortals;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ALTER_TAIL_USE = registerSoundEvent("alter_tail_use");
    public static final SoundEvent ALTER_TAIL_FAIL = registerSoundEvent("alter_tail_fail");
    public static final SoundEvent VOID_PASSAGE_OPEN = registerSoundEvent("void_passage_open");
    public static final SoundEvent VOID_PASSAGE_CLOSE = registerSoundEvent("void_passage_close");
    public static final SoundEvent VOID_PASSAGE_PHASE = registerSoundEvent("void_passage_phase");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(AlteredPortals.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        AlteredPortals.LOGGER.info("Registering sounds for " + AlteredPortals.MOD_ID);
    }
}
