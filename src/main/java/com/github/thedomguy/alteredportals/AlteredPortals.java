package com.github.thedomguy.alteredportals;

import com.github.thedomguy.alteredportals.block.ModBlocks;
import com.github.thedomguy.alteredportals.item.ModItems;
import com.github.thedomguy.alteredportals.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlteredPortals implements ModInitializer {
	public static final String MOD_ID = "alteredportals";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Altered Portals");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
	}
}