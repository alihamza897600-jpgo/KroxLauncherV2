package com.krox.client;

import com.krox.client.cape.KroxCapeManager;
import com.krox.client.gui.KroxUiManager;
import com.krox.client.skin.KroxSkinManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class KroxClient implements ClientModInitializer {
    public static final String MOD_ID = "kroxclient";
    private static final KroxSkinManager SKINS = new KroxSkinManager();
    private static final KroxCapeManager CAPES = new KroxCapeManager();
    private static final KroxUiManager UI = new KroxUiManager();

    @Override
    public void onInitializeClient() {
        try {
            var gameDir = FabricLoader.getInstance().getGameDir();
            SKINS.init(gameDir);
            CAPES.init(gameDir);
        } catch (Exception ignored) {}
        UI.initialize();
    }

    public static KroxSkinManager skins() { return SKINS; }
    public static KroxCapeManager capes() { return CAPES; }
}
