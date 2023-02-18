![banner](banner.svg)

[![CI](https://github.com/axelrindle/minecraft-chicken-drop-feathers/actions/workflows/ci.yml/badge.svg)](https://github.com/axelrindle/minecraft-chicken-drop-feathers/actions/workflows/ci.yml)
[![CurseForge](https://img.shields.io/badge/CurseForge-Download-f16436?logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/chicken-drop-feathers)
[![Modrinth](https://img.shields.io/badge/Modrinth-Download-1bd96a?logo=modrinth)](https://modrinth.com/mod/chicken-drop-feathers)

# minecraft-chicken-drop-feathers

> Adult chicken will drop 0 - 2 feathers from time to time.

Usually I play Minecraft without mods, but one thing that I was always frustrated
about is the fact that I have to kill Chicken in order to get some feathers.
This is the reason I created this mod.

If you want to request this mod to be available for another Forge/Minecraft version,
please open an issue on GitHub and specify the required version.

You can query the remaining amount of ticks until the next feather drop using the
following command:

```
/data get entity @e[type=minecraft:chicken,distance=..5,limit=1] FeatherDropTime
```

## Required Dependencies

- [Architectury Api](https://modrinth.com/mod/architectury-api)

## FAQs

### May I use this in my modpack?

**Yes.** Just link back to this page.

## Special thanks

Thanks to [d4rkm0nkey](https://github.com/d4rkm0nkey) for helping with the [FabricMC](https://fabricmc.net/) port.

## License

[GNU LGPLv3](LICENSE)
