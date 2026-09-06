package com.krox.client.skin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Krox skin manager — selective port from optix cosmetics/SkinManager.
 * Adapted to Krox naming, Yarn mappings verified; texture cache handled via KroxTextureService.
 * No blind copy: package com.krox.client, id kroxclient, config krox/.
 */
public final class KroxSkinManager {
    private String activeSkinId;
    private Path importedDir;
    private Path downloadedDir;
    private final Map<String, SkinEntry> cache = new LinkedHashMap<>();

    public Optional<String> getActiveSkinId() { return Optional.ofNullable(activeSkinId); }
    public void setActiveSkinId(String id) { this.activeSkinId = id; }

    public void init(Path gameDir) {
        importedDir = gameDir.resolve("skins");
        downloadedDir = gameDir.resolve("krox/skins");
        try { Files.createDirectories(importedDir); Files.createDirectories(downloadedDir); } catch (Exception ignored) {}
        refresh();
    }

    public void refresh() {
        cache.clear();
        scan(importedDir, SkinSource.IMPORTED);
        scan(downloadedDir, SkinSource.DOWNLOADED);
    }

    private void scan(Path dir, SkinSource src) {
        try {
            if (!Files.exists(dir)) return;
            try (var stream = Files.newDirectoryStream(dir, "*.png")) {
                for (Path p : stream) {
                    String name = p.getFileName().toString();
                    cache.putIfAbsent(name, new SkinEntry(name, p, src));
                }
            }
        } catch (Exception ignored) {}
    }

    public List<SkinEntry> getSkins() { return Collections.unmodifiableList(cache.values().stream().collect(Collectors.toList())); }
    public Optional<SkinEntry> findByName(String name) { return Optional.ofNullable(cache.get(name)); }

    public enum SkinSource { IMPORTED, DOWNLOADED }

    public static final class SkinEntry {
        public final String name; public final Path path; public final SkinSource source;
        public SkinEntry(String n, Path p, SkinSource s) { name=n; path=p; source=s; }
    }
}
