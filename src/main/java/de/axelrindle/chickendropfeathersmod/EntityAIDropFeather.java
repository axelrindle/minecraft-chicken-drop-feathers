package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A custom {@link EntityAIBase} instance responsible for dropping a feather from time to time.
 */
public class EntityAIDropFeather extends EntityAIBase {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * The base amount of ticks used for time calculation.
     *
     * 20 ticks equal 1 second. 12000 ticks equal 600 seconds. 600 Seconds equal 10 minutes.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final int baseTicks = 12000;

    private int timeUntilNextFeather = getNewTime();
    private EntityChicken chickenEntity;

    EntityAIDropFeather(EntityChicken chickenEntity) {
        this.chickenEntity = chickenEntity;
    }

    @Override
    public boolean shouldExecute() {
        return !chickenEntity.isChild() && !chickenEntity.isChickenJockey();
    }

    @Override
    public void resetTask() {
        timeUntilNextFeather = getNewTime();
    }

    @Override
    public void tick() {
        timeUntilNextFeather--;

        if (timeUntilNextFeather == 0) {
            dropFeather();
            resetTask();
        }
    }

    private int getNewTime() {
        return random.nextInt(baseTicks) + baseTicks;
    }

    private int getFeatherAmount() {
        return random.nextInt(2) + 1;
    }

    private void dropFeather() {
        chickenEntity.entityDropItem(new ItemStack(Items.FEATHER, getFeatherAmount()));
    }
}
