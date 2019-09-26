package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A custom {@link Goal} instance responsible for dropping a feather from time to time.
 */
public class GoalDropFeather extends Goal {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * The base amount of ticks used for time calculation.
     *
     * 20 ticks equal 1 second. 12000 ticks equal 600 seconds. 600 Seconds equal 10 minutes.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final int baseTicks = 12000;

    private int timeUntilNextFeather = getNewTime();
    private ChickenEntity chickenEntity;

    GoalDropFeather(ChickenEntity chickenEntity) {
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
