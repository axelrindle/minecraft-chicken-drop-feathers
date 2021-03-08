package de.axelrindle.chickendropfeathersmod.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Items;

import java.util.concurrent.ThreadLocalRandom;

public class EntityGoalDropFeather extends Goal {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private final int baseTicks = 12000;
    private final ChickenEntity chicken;
    private int timeUntilNextFeather = getNewTime();

    public EntityGoalDropFeather(ChickenEntity chicken) {
        this.chicken = chicken;
    }

    @Override
    public boolean canStart() {
        return shouldContinue();
    }

    @Override
    public boolean shouldContinue() {
        return !chicken.isBaby() && !chicken.hasJockey();
    }

    @Override
    public void stop() {
        timeUntilNextFeather = getNewTime();
    }

    @Override
    public void tick() {
        timeUntilNextFeather--;
        if (timeUntilNextFeather <= 0) {
            dropFeather();
            stop();
        }
    }

    /**
     * Returns a new tick delay until the next feather will be dropped.
     *
     * @return An integer between 12000 and 24000
     * @see #baseTicks
     */
    private int getNewTime() {
        return random.nextInt(baseTicks, baseTicks * 2 + 1);
    }

    /**
     * Returns the amount of feathers that should be dropped.
     *
     * @return An integer between 1 and 2.
     */
    private int getFeatherAmount() {
        return random.nextInt(3);
    }

    private void dropFeather() {
        chicken.dropItem(Items.FEATHER, getFeatherAmount());
    }
}
