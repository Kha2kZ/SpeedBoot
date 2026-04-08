# SpeedBoot — Fabric 1.20.5

A lightweight Fabric mod that reduces Minecraft startup time by skipping unnecessary initialization processes. Designed for low-RAM devices such as TV Boxes and budget Android phones running PojavLauncher.

## What SpeedBoot optimizes

| Optimization | Estimated time saved |
|---|---|
| Removes artificial splash screen delay | ~1 second |
| Skips narrator init when disabled | ~200–400 ms |
| Skips title screen fade-in animation | ~500 ms |
| Logs DataPack reload timing for debugging | (diagnostic) |

## Requirements

- Minecraft **1.20.5**
- Fabric Loader **≥ 0.15.0**
- Fabric API **0.97.8+1.20.5**
- Java **21**

## How to build

### Option 1: GitHub Actions (recommended — no powerful machine needed)

1. Fork or clone this repository to your GitHub account
2. Go to the **Actions** tab → select **Build SpeedBoot Mod** → click **Run workflow**
3. Wait 5–10 minutes, then download the `.jar` from the **Artifacts** section

The mod is also built automatically on every push to `main`.

---

### Option 2: Build locally (requires ≥ 4 GB RAM and JDK 21)

```bash
# Linux / macOS
./gradlew build

# Windows
gradlew.bat build
```

The first run takes 5–15 minutes (downloading Gradle, decompiling Minecraft).  
Subsequent builds take 10–30 seconds thanks to caching.

Output file:
```
build/libs/speedboot-1.0.0.jar
```

## Installation

1. Copy `speedboot-1.0.0.jar` into your `mods/` folder.
2. Make sure **Fabric Loader** and **Fabric API** are already installed.
3. Launch the game normally.

> **PojavLauncher users:** the `mods/` folder is usually at  
> `/storage/emulated/0/games/PojavLauncher/.minecraft/mods/`

## Project structure

```
SpeedBoot/
├── build.gradle                    # Fabric Loom build config
├── gradle.properties               # Minecraft, mod, and loader versions
├── settings.gradle
├── gradlew / gradlew.bat           # Gradle wrapper scripts
├── gradle/wrapper/
│   ├── gradle-wrapper.jar
│   └── gradle-wrapper.properties   # Points to Gradle 8.6
└── src/main/java/com/speedboot/
    ├── SpeedBootMod.java            # Common entry point
    ├── SpeedBootClientMod.java      # Client-side entry point
    └── mixin/
        ├── DataPackContentsMixin.java         # DataPack reload timing
        ├── DedicatedServerWatchdogMixin.java  # Server watchdog
        └── client/
            ├── SplashOverlayMixin.java        # Removes splash delay
            ├── NarratorMixin.java             # Skips narrator init
            ├── ResourceReloadMixin.java       # Resource pack scan log
            └── TitleScreenMixin.java          # Skips fade-in
```

## Configuration

Edit `gradle.properties` to change versions:

```properties
minecraft_version=1.20.5
loader_version=0.15.11
fabric_version=0.97.8+1.20.5
mod_version=1.0.0
```

## License

MIT
