package de.axelrindle.chickendropfeathersmod.mixin;

import de.axelrindle.chickendropfeathersmod.goal.EntityGoalDropFeather;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.ChickenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(ChickenEntity.class)
public class EntityChickenMixin {

	@Inject(at = @At("TAIL"), method = "initGoals")
	private void initGoals(CallbackInfo info) throws NoSuchFieldException, IllegalAccessException {
		ChickenEntity chicken = (ChickenEntity) (Object) this;

		// FIXME: a little hack as the solution below does not work
		Field goalSelectorField = chicken.getClass().getField("goalSelector");
		goalSelectorField.setAccessible(true);
		GoalSelector goalSelector = (GoalSelector) goalSelectorField.get(chicken);
		goalSelector.add(8, new EntityGoalDropFeather(chicken));

		// ((MobEntityAccessor) chicken).getGoalSelector().add(8, new EntityGoalDropFeather(chicken)); TODO: Doesn't work
	}

}
