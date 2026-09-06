package com.krox.client.cape;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Selective port from optix cape handling — Krox adaptation.
 */
public final class KroxCapeManager {
    private String activeCapeId;
    private Path capeDir;
    private final Map<String, CapeEntry> cache = new LinkedHashMap<>();

    public Optional<String> getActiveCapeId() { return Optional.ofNullable(activeCapeId); }
    public void setActiveCapeId(String id) { this.activeCapeId = id; }

    public void init(Path gameDir) {
        capeDir = gameDir.resolve("krox/capes");
        try { Files.createDirectories(capeDir); } catch (Exception ignored) {}
        refresh();
    }

    public void refresh() {
        cache.clear();
        try {
            if (!Files.exists(capeDir)) return;
            try (var s = Files.newDirectoryStream(capeDir, "*.png")) {
                for (Path p : s) cache.putIfAbsent(p.getFileName().toString(), new CapeEntry(p.getFileName().toString(), p));
            }
        } catch (Exception ignored) {}
    }

    public List<CapeEntry> getCapes() { return Collections.unmodifiableList(cache.values().stream().collect(Collectors.toList())); }

    public static final class CapeEntry {
        public final String name; public final Path path;
        public CapeEntry(String n, Path p) { name=n; path=p; }
    }
}
