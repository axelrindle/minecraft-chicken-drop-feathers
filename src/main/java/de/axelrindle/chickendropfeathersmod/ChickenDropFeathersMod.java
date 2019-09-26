package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("chickendropfeathersmod")
public class ChickenDropFeathersMod {

    public ChickenDropFeathersMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChickenSpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ChickenEntity) {
            ChickenEntity chicken = (ChickenEntity) entity;
            chicken.goalSelector.addGoal(8, new GoalDropFeather(chicken));
        }
    }
}
