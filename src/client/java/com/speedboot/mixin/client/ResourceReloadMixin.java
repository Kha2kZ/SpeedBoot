package com.speedboot.mixin.client;

import com.speedboot.SpeedBootMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * SpeedBoot: ResourceReloadMixin
 *
 * Logs the start of the resource pack scan so you can see how long it takes
 * relative to other startup phases in the debug log.
 */
@Environment(EnvType.CLIENT)
@Mixin(FileResourcePackProvider.class)
public class ResourceReloadMixin {

    @Inject(method = "register", at = @At("HEAD"))
    private void speedboot$logResourceScan(Consumer<ResourcePackProfile> profileAdder, CallbackInfo ci) {
        SpeedBootMod.LOGGER.debug("[SpeedBoot] Resource pack scan started.");
    }
}
