package com.krox.client;

import com.krox.client.gui.ClickGuiScreen;
import com.krox.client.hud.KroxHud;
import com.krox.client.module.ModuleManager;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

public class KroxClient implements ClientModInitializer {

    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    public static KeyBinding OPEN_GUI;

    @Override
    public void onInitializeClient() {

        MODULE_MANAGER.registerDefaults();

        OPEN_GUI = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.kroxclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.kroxclient"
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (OPEN_GUI.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }

            MODULE_MANAGER.onTick(client);
        });

        KroxHud.initialize();

        System.out.println("[Krox Client] Initialized successfully!");
    }
}
