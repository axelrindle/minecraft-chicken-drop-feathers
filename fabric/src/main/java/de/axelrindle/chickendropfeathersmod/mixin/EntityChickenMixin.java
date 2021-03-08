package de.axelrindle.chickendropfeathersmod.mixin;

import de.axelrindle.chickendropfeathersmod.goal.EntityGoalDropFeather;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(ChickenEntity.class)
public class EntityChickenMixin {

	private static final String TAG_KEY = "FeatherDropTime";
	private EntityGoalDropFeather goal;

	@Inject(at = @At("TAIL"), method = "initGoals")
	private void initGoals(CallbackInfo info) throws NoSuchFieldException, IllegalAccessException {
		ChickenEntity chicken = (ChickenEntity) (Object) this;

		// FIXME: a little hack as the solution below does not work
		Field goalSelectorField = chicken.getClass().getField("goalSelector");
		goalSelectorField.setAccessible(true);
		GoalSelector goalSelector = (GoalSelector) goalSelectorField.get(chicken);

		goal = new EntityGoalDropFeather(chicken);
		goalSelector.add(8, goal);

//		((MobEntityAccessor) chicken).getGoalSelector().add(8, new EntityGoalDropFeather(chicken)); //TODO: Doesn't work
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

}
