package de.axelrindle.chickendropfeathersmod;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.concurrent.ThreadLocalRandom;

public class EntityGoalDropFeather extends Goal {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private final int baseTicks = 12000;
    private final Chicken chicken;
    private int timeUntilNextFeather = getNewTime();

    public EntityGoalDropFeather(Chicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public boolean canUse() {
        return !chicken.isBaby() && !chicken.isChickenJockey();
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

    public int getTimeUntilNextFeather() {
        return timeUntilNextFeather;
    }

    public void setTimeUntilNextFeather(int timeUntilNextFeather) {
        this.timeUntilNextFeather = timeUntilNextFeather;
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
        chicken.spawnAtLocation(new ItemStack(Items.FEATHER, getFeatherAmount()));
    }
}
