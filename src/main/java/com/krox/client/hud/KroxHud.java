package com.krox.client.hud;

import com.krox.client.KroxClient;
import com.krox.client.module.Module;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class KroxHud {

    public static void initialize() {

        HudRenderCallback.EVENT.register(
            (drawContext, tickDelta) -> {

                MinecraftClient client =
                    MinecraftClient.getInstance();

                if (client.player == null) {
                    return;
                }

                int y = 10;

                drawContext.drawTextWithShadow(
                    client.textRenderer,
                    "KROX CLIENT",
                    10,
                    y,
                    0xFFAAAAFF
                );

                y += 14;

                for (Module module :
                        KroxClient.MODULE_MANAGER.getModules()) {

                    if (module.isEnabled()) {

                        drawContext.drawTextWithShadow(
                            client.textRenderer,
                            module.getName(),
                            10,
                            y,
                            0xFFFFFFFF
                        );

                        y += 11;
                    }
                }
            }
        );
    }
}
