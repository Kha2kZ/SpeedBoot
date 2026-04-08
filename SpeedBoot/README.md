# SpeedBoot — Fabric 1.20.1

Mod tối ưu thời gian khởi động Minecraft, đặc biệt phù hợp cho thiết bị RAM thấp như TV Box.

## Những gì SpeedBoot tối ưu

| Tối ưu | Thời gian tiết kiệm (ước tính) |
|--------|-------------------------------|
| Bỏ qua thời gian chờ Splash Screen | ~1 giây |
| Bỏ qua khởi tạo Narrator khi tắt | ~200–400ms |
| Rút ngắn hiệu ứng fade-in Title Screen | ~500ms |
| Log timing DataPack để debug | (chuẩn đoán) |

## Yêu cầu để build

- **Java 17** trở lên (JDK, không phải JRE)
- Kết nối Internet lần đầu (để tải Gradle 8.6 + Minecraft jar)
- **RAM tối thiểu 4GB** (chỉ cần cho lần build đầu tiên)

## Cách build

### Cách 1: GitHub Actions (khuyên dùng — không cần máy mạnh)

1. Tạo repo mới trên [github.com](https://github.com)
2. Upload toàn bộ thư mục `SpeedBoot/` lên repo đó
3. Vào tab **Actions** → chọn workflow **Build SpeedBoot Mod** → nhấn **Run workflow**
4. Chờ 5–10 phút, sau đó tải file `.jar` từ mục **Artifacts**

Mỗi lần push code lên `main` sẽ tự động build lại.

---

### Cách 2: Build local (cần máy ≥4GB RAM)

```bash
# Linux / macOS
./gradlew build

# Windows
gradlew.bat build
```

Lần đầu tiên sẽ mất 5–15 phút (tải Gradle, decompile Minecraft).
Lần sau chỉ mất 10–30 giây.

File `.jar` output nằm tại:
```
build/libs/speedboot-1.0.0.jar
```

## Cài vào game

1. Copy file `build/libs/speedboot-1.0.0.jar` vào thư mục `mods/` của Minecraft.
2. Đảm bảo đã cài **Fabric Loader 0.15+** và **Fabric API 0.92+** cho Minecraft 1.20.1.
3. Khởi động game bình thường.

> **Lưu ý cho PojavLauncher:** Thư mục `mods/` thường ở  
> `/storage/emulated/0/games/PojavLauncher/.minecraft/mods/`

## Cấu trúc project

```
SpeedBoot/
├── build.gradle                    # Cấu hình build Fabric Loom
├── gradle.properties               # Phiên bản Minecraft, mod, loader
├── settings.gradle
├── gradlew / gradlew.bat           # Gradle wrapper (chạy trực tiếp)
├── gradle/wrapper/
│   ├── gradle-wrapper.jar
│   └── gradle-wrapper.properties   # Trỏ đến Gradle 8.6
└── src/main/java/com/speedboot/
    ├── SpeedBootMod.java            # Entry point chính
    ├── SpeedBootClientMod.java      # Entry point client
    └── mixin/
        ├── DataPackContentsMixin.java         # Log timing DataPack
        ├── DedicatedServerWatchdogMixin.java  # (server)
        └── client/
            ├── SplashOverlayMixin.java        # Bỏ delay splash
            ├── NarratorMixin.java             # Skip narrator init
            ├── ResourceReloadMixin.java       # Log resource scan
            └── TitleScreenMixin.java          # Skip fade-in
```

## Tùy chỉnh

Mở `gradle.properties` để thay đổi phiên bản:

```properties
minecraft_version=1.20.1
loader_version=0.15.11
fabric_version=0.92.2+1.20.1
mod_version=1.0.0
```

## License

MIT
