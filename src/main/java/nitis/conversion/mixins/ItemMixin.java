package nitis.conversion.mixins;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.registry.RegistryEntry;
import nitis.Nullable;
import nitis.conversion.ConversionMod;
import nitis.conversion.tags.ConversionTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.text.html.HTML;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Shadow @Deprecated public abstract RegistryEntry.Reference<Item> getRegistryEntry();
    @Shadow @Final @org.jetbrains.annotations.Nullable private FoodComponent foodComponent;
    private static FoodComponent lightweightFoodComponent;
    private Nullable<Boolean> food = new Nullable<>();

    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    public void isFood(CallbackInfoReturnable<Boolean> cir) {
        initializeFood();
        cir.setReturnValue(food.getValue() || this.foodComponent != null);
    }
    @Inject(method = "getFoodComponent", at = @At("RETURN"), cancellable = true)
    public void getFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        initializeFood();
        boolean isLightweihtFood = food.getValue();

        if (isLightweihtFood) {
            cir.setReturnValue(lightweightFoodComponent);
        }
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo cir)
    {
        lightweightFoodComponent =
                new FoodComponent.Builder()
                        .hunger(1)
                        .alwaysEdible()
                        .build();
    }

    private void initializeFood() {
        if (food.isNull()) {

            ReferenceAccessor<Item> accessor = (ReferenceAccessor<Item>)this.getRegistryEntry();
            boolean f = false;
            for (var i : accessor.getTags()) {
                if (i == ConversionTags.EATABLE_ITEMS) {
                    f = true;
                    break;
                }
            }
            food.setValue(f);
        }
    }
}
