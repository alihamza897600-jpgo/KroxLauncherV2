package com.krox.client.module;

import net.minecraft.client.MinecraftClient;

public class FullbrightModule extends Module {

    private double previousGamma;

    public FullbrightModule() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    public void onEnable() {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null) {
            previousGamma = client.options.getGamma().getValue();
            client.options.getGamma().setValue(16.0);
        }
    }

    @Override
    public void onDisable() {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null) {
            client.options.getGamma().setValue(previousGamma);
        }
    }
}
