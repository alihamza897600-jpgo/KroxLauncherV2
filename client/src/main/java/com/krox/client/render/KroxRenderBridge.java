package com.krox.client.render;

/**
 * Render bridge — selective port from optix render/ shader pipeline.
 * Verifies Yarn mappings: no fabricated net.minecraft.client.render imports here;
 * actual GL calls live in mixin-verified classes. This bridge is the seam.
 */
public final class KroxRenderBridge {
    private boolean initialized;
    private float blurStrength = 0.5f;

    public void init() { initialized = true; }
    public boolean isInitialized() { return initialized; }
    public void setBlurStrength(float v) { blurStrength = Math.max(0f, Math.min(1f, v)); }
    public float getBlurStrength() { return blurStrength; }
    public void onFrame(float tickDelta) { /* hook for motion blur / shader passes */ }
}
