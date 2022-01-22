package de.axelrindle.chickendropfeathersmod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySpawnHandler {

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Chicken chicken) {
            chicken.goalSelector.addGoal(
                    10,
                    new EntityGoalDropFeather(chicken)
            );
        }
    }
}
