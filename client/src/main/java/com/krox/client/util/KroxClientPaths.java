package com.krox.client.util;

import java.nio.file.Path;

public final class KroxClientPaths {

    private KroxClientPaths() {
    }

    public static Path child(Path root, String name) {
        return root.resolve(name);
    }
}
