package com.speedboot.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * SpeedBoot: SplashOverlayMixin
 *
 * Minecraft normally waits at least 1 000 ms on the splash screen even after
 * resources have finished loading. This mixin rewinds the completion timestamp
 * so the overlay closes on the very next rendered frame.
 */
@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {

    @Shadow
    private long reloadCompleteTime;

    @Inject(method = "render", at = @At("HEAD"))
    private void speedboot$minimizeSplashWait(CallbackInfo ci) {
        if (this.reloadCompleteTime > 0) {
            this.reloadCompleteTime = Math.min(this.reloadCompleteTime,
                    System.currentTimeMillis() - 1001L);
        }
    }
}
