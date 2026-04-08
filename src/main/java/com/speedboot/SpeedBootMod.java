package com.speedboot;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeedBootMod implements ModInitializer {

    public static final String MOD_ID = "speedboot";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        long start = System.currentTimeMillis();
        LOGGER.info("[SpeedBoot] Mod initialized in {}ms", System.currentTimeMillis() - start);
    }
}
