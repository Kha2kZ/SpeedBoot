package com.speedboot.mixin.client;

import com.speedboot.SpeedBootMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * SpeedBoot: SplashOverlayMixin
 *
 * Minimizes the loading splash screen time.
 * Normally Minecraft waits at least ~1000ms on the splash even after resources are loaded.
 * This mixin removes that artificial delay so the game continues as soon as it's ready.
 */
@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashOverlayMixin {

    @Shadow
    private long reloadCompleteTime;

    /**
     * When the resource reload finishes, immediately set the completion time to
     * a value that forces the overlay to close on the very next frame instead
     * of waiting 1 000 ms.
     */
    @Inject(
        method = "render",
        at = @At("HEAD")
    )
    private void speedboot$minimizeSplashWait(CallbackInfo ci) {
        if (this.reloadCompleteTime > 0) {
            this.reloadCompleteTime = Math.min(this.reloadCompleteTime,
                    System.currentTimeMillis() - 1001L);
        }
    }
}
