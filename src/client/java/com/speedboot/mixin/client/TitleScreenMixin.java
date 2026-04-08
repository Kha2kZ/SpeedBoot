package com.speedboot.mixin.client;

import com.speedboot.SpeedBootMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * SpeedBoot: TitleScreenMixin
 *
 * Forces the title screen background to be fully visible immediately,
 * skipping the ~500 ms fade-in animation.
 */
@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Shadow
    private float backgroundFadeOpacity;

    @Inject(method = "init", at = @At("TAIL"))
    private void speedboot$skipFadeIn(CallbackInfo ci) {
        this.backgroundFadeOpacity = 1.0f;
        SpeedBootMod.LOGGER.debug("[SpeedBoot] Title screen fade-in skipped.");
    }
}
