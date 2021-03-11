package de.axelrindle.chickendropfeathersmod.mixin;

import de.axelrindle.chickendropfeathersmod.goal.EntityGoalDropFeather;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenEntity.class)
public class EntityChickenMixin extends AnimalEntity {

	protected EntityChickenMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	private static final String TAG_KEY = "FeatherDropTime";
	private EntityGoalDropFeather goal;

	@Inject(at = @At("TAIL"), method = "initGoals")
	private void initGoals(CallbackInfo info) {
		ChickenEntity chicken = (ChickenEntity) (Object) this;
		goal = new EntityGoalDropFeather(chicken);
		this.goalSelector.add(8, goal);
	}

	@Inject(at = @At("TAIL"), method = "writeCustomDataToTag")
	private void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
		tag.putInt(TAG_KEY, goal.getTimeUntilNextFeather());
	}

	@Inject(at = @At("TAIL"), method = "readCustomDataFromTag")
	private void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
		if (tag.contains(TAG_KEY)) {
			goal.setTimeUntilNextFeather(tag.getInt(TAG_KEY));
		}
	}

	@Override
	public PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
		return EntityType.CHICKEN.create(serverWorld);
	}
}