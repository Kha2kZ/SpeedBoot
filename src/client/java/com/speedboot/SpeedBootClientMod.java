package com.speedboot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpeedBootClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SpeedBootMod.LOGGER.info("[SpeedBoot] Client-side optimizations active.");
        SpeedBootMod.LOGGER.info("[SpeedBoot] - Splash screen delay: minimized");
        SpeedBootMod.LOGGER.info("[SpeedBoot] - Narrator init: skipped when disabled");
        SpeedBootMod.LOGGER.info("[SpeedBoot] - Title screen fade: disabled");
    }
}
