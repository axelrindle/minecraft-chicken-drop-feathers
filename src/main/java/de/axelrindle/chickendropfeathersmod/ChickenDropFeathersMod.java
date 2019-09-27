package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("chickendropfeathersmod")
public class ChickenDropFeathersMod {

    public ChickenDropFeathersMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChickenSpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityChicken) {
            EntityChicken chicken = (EntityChicken) entity;
            chicken.tasks.addTask(8, new EntityAIDropFeather(chicken));
        }
    }
}
