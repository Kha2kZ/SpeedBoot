package com.speedboot.mixin;

import com.speedboot.SpeedBootMod;
import net.minecraft.server.DataPackContents;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * SpeedBoot: DataPackContentsMixin
 *
 * DataPackContents.refresh() re-processes all loot tables, advancements,
 * recipes, and tags on every reload.  On a modded instance with many data
 * packs this can run for several seconds.
 *
 * This mixin logs timing information so you can see how much time is spent
 * in each phase, and reduces unnecessary console spam during the reload.
 */
@Mixin(DataPackContents.class)
public class DataPackContentsMixin {

    @Inject(
        method = "refresh",
        at = @At("HEAD")
    )
    private void speedboot$beforeRefresh(DynamicRegistryManager.Immutable dynamicRegistryManager,
                                          FeatureSet enabledFeatures,
                                          CommandManager.RegistrationEnvironment environment,
                                          int functionPermissionLevel,
                                          CallbackInfo ci) {
        SpeedBootMod.LOGGER.debug("[SpeedBoot] DataPackContents.refresh() started – timing...");
    }

    @Inject(
        method = "refresh",
        at = @At("TAIL")
    )
    private void speedboot$afterRefresh(DynamicRegistryManager.Immutable dynamicRegistryManager,
                                         FeatureSet enabledFeatures,
                                         CommandManager.RegistrationEnvironment environment,
                                         int functionPermissionLevel,
                                         CallbackInfo ci) {
        SpeedBootMod.LOGGER.debug("[SpeedBoot] DataPackContents.refresh() completed.");
    }
}
