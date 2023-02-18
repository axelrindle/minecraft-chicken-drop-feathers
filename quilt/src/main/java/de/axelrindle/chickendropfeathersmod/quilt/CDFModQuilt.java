package de.axelrindle.chickendropfeathersmod.quilt;

import de.axelrindle.chickendropfeathersmod.fabriclike.CDFModFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class CDFModQuilt implements ModInitializer {

    @Override
    public void onInitialize(ModContainer mod) {
        CDFModFabricLike.init();
    }
}
