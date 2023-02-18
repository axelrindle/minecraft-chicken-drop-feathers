package de.axelrindle.chickendropfeathersmod.fabric;

import de.axelrindle.chickendropfeathersmod.fabriclike.CDFModFabricLike;
import net.fabricmc.api.ModInitializer;

public class CDFModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CDFModFabricLike.init();
    }
}
