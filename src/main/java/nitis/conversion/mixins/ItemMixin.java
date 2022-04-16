package nitis.conversion.mixins;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    private static FoodComponent lightweightFoodComponent;

    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    public void isFood(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
    @Inject(method = "getFoodComponent", at = @At("RETURN"), cancellable = true)
    public void getFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        cir.setReturnValue(lightweightFoodComponent);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void init(CallbackInfo cir)
    {
        lightweightFoodComponent =
                new FoodComponent.Builder()
                        .hunger(1)
                        .alwaysEdible()
                        .build();
    }
}
