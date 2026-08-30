package com.krox.client.mixin;

import com.krox.client.gui.KroxTitleScreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {

    private static boolean krox$replaced = false;

    @Inject(
        method = "init",
        at = @At("TAIL")
    )
    private void krox$replaceTitleScreen(CallbackInfo ci) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (!krox$replaced && client != null) {

            krox$replaced = true;

            client.execute(() -> {
                if (client.currentScreen instanceof TitleScreen) {
                    client.setScreen(new KroxTitleScreen());
                }
            });
        }
    }
}
