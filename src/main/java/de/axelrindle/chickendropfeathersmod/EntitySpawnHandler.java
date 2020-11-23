package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySpawnHandler {

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityChicken) {
            EntityChicken chicken = ((EntityChicken) entity);
            chicken.tasks.addTask(
                    chicken.tasks.taskEntries.size(),
                    new EntityAIDropFeather(chicken)
            );
        }
    }
}
