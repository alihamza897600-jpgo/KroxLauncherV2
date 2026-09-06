# Krox Client Base

Fabric/Mixin starter module for KroxLauncherV2.

This module is intended as the foundation for:

- Fabric client entrypoint
- Mixin support
- Krox custom UI
- Skin management
- Cape management
- Texture management
- Rendering hooks
- Selective Optix feature porting
- Automatic launcher integration

## Intended pipeline

client source
→ Gradle build
→ remapped/distributable Fabric JAR
→ automatically packaged into KroxLauncher
→ prepared during launcher initialization
→ inactive until supported Minecraft + Fabric exists
→ automatically deployed into the correct instance mods directory
