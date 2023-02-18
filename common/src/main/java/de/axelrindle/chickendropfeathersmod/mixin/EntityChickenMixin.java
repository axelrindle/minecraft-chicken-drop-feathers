package de.axelrindle.chickendropfeathersmod.mixin;

import de.axelrindle.chickendropfeathersmod.goal.EntityGoalDropFeather;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public class EntityChickenMixin extends Animal {

	private static final String TAG_KEY = "FeatherDropTime";
	private EntityGoalDropFeather goal;

	protected EntityChickenMixin(EntityType<? extends Animal> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(at = @At("TAIL"), method = "registerGoals")
	private void initGoals(CallbackInfo info) {
		Chicken chicken = (Chicken) (Object) this;
		goal = new EntityGoalDropFeather(chicken);
		this.goalSelector.addGoal(8, goal);
	}

	@Inject(at = @At("TAIL"), method = "addAdditionalSaveData")
	private void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
		tag.putInt(TAG_KEY, goal.getTimeUntilNextFeather());
	}

	@Inject(at = @At("TAIL"), method = "readAdditionalSaveData")
	private void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
		if (tag.contains(TAG_KEY)) {
			goal.setTimeUntilNextFeather(tag.getInt(TAG_KEY));
		}
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
		return EntityType.CHICKEN.create(serverLevel);
	}
}