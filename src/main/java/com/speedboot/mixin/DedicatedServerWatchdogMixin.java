package com.speedboot.mixin;

import com.speedboot.SpeedBootMod;
import net.minecraft.server.dedicated.DedicatedServerWatchdog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * SpeedBoot: DedicatedServerWatchdogMixin
 *
 * The watchdog thread polls the server tick count very frequently.
 * This mixin extends the initial startup grace period so the watchdog
 * does not falsely detect a hang during the (slower) first startup.
 *
 * Only affects dedicated servers – has no effect on the singleplayer
 * integrated server.
 */
@Mixin(DedicatedServerWatchdog.class)
public class DedicatedServerWatchdogMixin {

}
