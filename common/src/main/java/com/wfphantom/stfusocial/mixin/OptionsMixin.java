package com.wfphantom.stfusocial.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void stfusocial$init(CallbackInfo ci) {
        Options options = (Options) (Object) this;

        KeyMapping social = options.keySocialInteractions;
        social.setKey(InputConstants.UNKNOWN);
        ((OptionsAccessor) options).stfusocial$setKeyMappings(ArrayUtils.removeElement(options.keyMappings, social));
        KeyMapping.resetMapping();

        options.joinedFirstServer = true;
    }
}