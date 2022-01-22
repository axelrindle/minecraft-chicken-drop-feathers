package de.axelrindle.chickendropfeathersmod.mixin;

import de.axelrindle.chickendropfeathersmod.EntityGoalDropFeather;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public class EntityChickenMixin {

    private static final String TAG_KEY = "FeatherDropTime";

    private EntityGoalDropFeather goal;

    @Inject(at = @At("TAIL"), method = "registerGoals")
    private void registerGoals(CallbackInfo ci) {
        Chicken chicken = (Chicken) (Object) this;
        goal = new EntityGoalDropFeather(chicken);
        chicken.goalSelector.addGoal(8, goal);
    }

    @Inject(at = @At("TAIL"), method = "addAdditionalSaveData")
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putInt(TAG_KEY, goal.getTimeUntilNextFeather());
    }

    @Inject(at = @At("TAIL"), method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains(TAG_KEY)) {
            goal.setTimeUntilNextFeather(tag.getInt(TAG_KEY));
        }
    }
}
