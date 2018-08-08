package de.axelrindle.chickendropfeathersmod

import net.minecraft.entity.passive.EntityChicken
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class EntitySpawnHandler {

    @SubscribeEvent
    fun onEntitySpawn(event: EntityJoinWorldEvent) {
        val entity = event.entity
        if (entity is EntityChicken)
            entity.tasks.addTask(
                    entity.tasks.taskEntries.size,
                    EntityAIDropFeather(entity)
            )
    }
}