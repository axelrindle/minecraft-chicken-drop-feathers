package de.axelrindle.chickendropfeathersmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(ChickenDropFeathersMod.MODID)
public class ChickenDropFeathersMod {

    public static final String MODID = "@MODID@";

    public ChickenDropFeathersMod() {
        MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());
    }
}
