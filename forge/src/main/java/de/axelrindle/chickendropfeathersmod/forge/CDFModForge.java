package de.axelrindle.chickendropfeathersmod.forge;

import dev.architectury.platform.forge.EventBuses;
import de.axelrindle.chickendropfeathersmod.ChickenDropFeathersMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ChickenDropFeathersMod.MOD_ID)
public class CDFModForge {

    public CDFModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ChickenDropFeathersMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ChickenDropFeathersMod.init();
    }
}
