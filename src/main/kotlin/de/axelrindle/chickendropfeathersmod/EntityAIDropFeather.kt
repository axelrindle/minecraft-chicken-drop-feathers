package de.axelrindle.chickendropfeathersmod

import net.minecraft.entity.ai.EntityAIBase
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.init.Items
import java.util.*

/**
 * A custom [EntityAIBase] instance responsible for dropping a feather from
 * time to time.
 */
class EntityAIDropFeather(

        /**
         * The [EntityChicken] this AI belongs to.
         */
        private val chicken: EntityChicken

) : EntityAIBase() {

    private val rand: Random = Random()

    /**
     * The base amount of ticks used for time calculation.
     *
     * 20 ticks equal 1 second. 12000 ticks equal 600 seconds. 600 Seconds equal 10 minutes.
     */
    private val baseTicks: Int = 1000
    private var timeUntilNextFeather: Int = getNewTime()


    override fun shouldExecute(): Boolean {
        return true
    }

    override fun resetTask() {
        timeUntilNextFeather = getNewTime()
    }

    override fun updateTask() {
        timeUntilNextFeather--

        if (timeUntilNextFeather == 0) {
            dropFeather()
            resetTask()
        }
    }

    /**
     * Returns a new tick delay until the next feather will be dropped.
     * Currently this lies between 10 and 20 minutes.
     *
     * @return An integer between 12000 and 23999
     * @see baseTicks
     */
    private fun getNewTime(): Int {
        return this.rand.nextInt(baseTicks) + baseTicks
    }

    /**
     * Returns the amount of feathers that should be dropped.
     *
     * @return An integer between 1 and 2.
     */
    private fun getFeatherAmount(): Int {
        return this.rand.nextInt(2) + 1
    }

    private fun dropFeather() {
        chicken.dropItem(Items.FEATHER, getFeatherAmount())
    }
}