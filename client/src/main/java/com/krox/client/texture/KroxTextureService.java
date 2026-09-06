package com.krox.client.texture;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Texture registration cache — ported from optix texture handling,
 * adapted to Krox id "kroxclient" and Yarn NativeImage mapping (class_1011 -> NativeImage).
 * Actual GL upload deferred to client thread via KroxRenderBridge.
 */
public final class KroxTextureService {
    private final Map<String, Path> pending = new ConcurrentHashMap<>();
    private final Map<String, String> textureIds = new ConcurrentHashMap<>();

    public String register(String key, Path png) {
        if (!Files.exists(png)) return null;
        String id = "kroxclient:texture/" + key.replaceAll("[^a-z0-9/_-]", "_").toLowerCase();
        pending.put(key, png);
        textureIds.put(key, id);
        return id;
    }

    public String getTextureId(String key) { return textureIds.get(key); }
    public boolean hasTexture(String key) { return textureIds.containsKey(key); }
    public void evict(String key) { pending.remove(key); textureIds.remove(key); }
    public void clear() { pending.clear(); textureIds.clear(); }
}
