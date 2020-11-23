package de.axelrindle.chickendropfeathersmod;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class EntityAIDropFeather extends EntityAIBase {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private final int baseTicks = 12000;
    private final EntityChicken chicken;
    private int timeUntilNextFeather = getNewTime();

    public EntityAIDropFeather(EntityChicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public boolean shouldExecute() {
        return !chicken.isChild() && !chicken.isChickenJockey();
    }

    @Override
    public void resetTask() {
        timeUntilNextFeather = getNewTime();
    }

    @Override
    public void tick() {
        timeUntilNextFeather--;
        System.out.println("Feathers in " + timeUntilNextFeather + " ticks...");
        if (timeUntilNextFeather <= 0) {
            dropFeather();
            resetTask();
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
        chicken.entityDropItem(new ItemStack(Items.FEATHER, getFeatherAmount()));
    }
}
