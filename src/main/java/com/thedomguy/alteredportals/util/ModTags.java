package com.thedomguy.alteredportals.util;

import com.thedomguy.alteredportals.AlteredPortals;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> VOID_PASSAGE_UNUSABLE = createTag("void_passage_unusable");
        public static final TagKey<Block> VOID_PASSAGE_REPLACEABLE = createTag("void_passage_replaceable");
        public static final TagKey<Block> VOID_PASSAGE_BLOCKING = createTag("void_passage_blocking");
        public static final TagKey<Block> VOID_PASSAGE_EXITABLE = createTag("void_passage_exitable");

        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AlteredPortals.MOD_ID, name));
        }
    }
}
