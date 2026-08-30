package com.krox.client.module;

import net.minecraft.client.MinecraftClient;

public class SprintModule extends Module {

    public SprintModule() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick(MinecraftClient client) {

        if (client.player != null
                && client.player.input != null
                && client.player.input.movementForward > 0) {

            client.player.setSprinting(true);
        }
    }
}
