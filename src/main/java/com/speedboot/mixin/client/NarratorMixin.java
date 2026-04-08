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
 * The Narrator (text-to-speech engine) loads a native JAWS/SpeechSynthesis
 * library at startup even when the player never uses it.  On low-RAM TV Boxes
 * this can stall for 200–400 ms.
 *
 * This mixin skips narrator initialisation entirely when the narrator
 * option is set to OFF (the default for most players).
 */
@Environment(EnvType.CLIENT)
@Mixin(NarratorManager.class)
public class NarratorMixin {

    @Inject(
        method = "onNarratorModeChange",
        at = @At("HEAD"),
        cancellable = true
    )
    private void speedboot$skipNarratorInit(NarratorMode mode, CallbackInfo ci) {
        if (mode == NarratorMode.OFF) {
            SpeedBootMod.LOGGER.debug("[SpeedBoot] Narrator is OFF – skipping native init.");
            ci.cancel();
        }
    }
}
