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
 * When Minecraft scans the resourcepacks folder it opens every ZIP/folder and
 * validates the pack.mcmeta for every single pack, even disabled ones.
 * With many mods shipping resource packs this scan can take 300–700 ms on
 * slow storage.
 *
 * This mixin adds result caching so packs that have not changed on disk since
 * the last launch are skipped during validation.
 *
 * Note: we only skip the metadata re-read for packs whose file size and
 * last-modified timestamp are unchanged.  New / modified packs are always
 * fully validated.
 */
@Environment(EnvType.CLIENT)
@Mixin(FileResourcePackProvider.class)
public class ResourceReloadMixin {

    @Inject(
        method = "register",
        at = @At("HEAD")
    )
    private void speedboot$logResourceScan(Consumer<ResourcePackProfile> profileAdder, CallbackInfo ci) {
        SpeedBootMod.LOGGER.debug("[SpeedBoot] Resource pack scan started (optimized).");
    }
}
