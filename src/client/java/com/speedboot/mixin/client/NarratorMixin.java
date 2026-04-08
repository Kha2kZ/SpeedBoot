package com.speedboot.mixin.client;

import com.speedboot.SpeedBootMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.option.NarratorMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * SpeedBoot: NarratorMixin
 *
 * Skips narrator (text-to-speech) native library initialisation when the
 * narrator option is OFF (the default for most players). On TV Boxes this
 * call can stall for 200–400 ms.
 */
@Environment(EnvType.CLIENT)
@Mixin(NarratorManager.class)
public class NarratorMixin {

    @Inject(method = "onNarratorModeChange", at = @At("HEAD"), cancellable = true)
    private void speedboot$skipNarratorInit(NarratorMode mode, CallbackInfo ci) {
        if (mode == NarratorMode.OFF) {
            SpeedBootMod.LOGGER.debug("[SpeedBoot] Narrator is OFF – skipping native init.");
            ci.cancel();
        }
    }
}
