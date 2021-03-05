package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySpawnHandler {

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ChickenEntity) {
            ChickenEntity chicken = (ChickenEntity) entity;
            chicken.goalSelector.addGoal(
                    10,
                    new EntityGoalDropFeather(chicken)
            );
        }
    }
}
