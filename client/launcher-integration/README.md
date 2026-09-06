# Krox Client Launcher Integration

The final Krox Client JAR should be built automatically.

Rules:

1. Build the client with Gradle.
2. Use the final remapped/distributable Fabric JAR.
3. Package the artifact into the launcher.
4. Prepare it during launcher initialization.
5. Keep it inactive until a supported Minecraft + Fabric installation exists.
6. For Minecraft 1.21.11 + Fabric, deploy the Krox Client JAR into the correct instance mods directory.
7. Preserve unrelated user mods.
8. Replace only the previous Krox Client artifact during updates.
