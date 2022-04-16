package nitis.conversion.mixins;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import nitis.conversion.ConversionMod;
import nitis.conversion.tags.ConversionTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    private FoodComponent outFoodComponent;
    @Shadow public abstract Item asItem();

    @Shadow @Deprecated public abstract RegistryEntry.Reference<Item> getRegistryEntry();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(Item.Settings settings, CallbackInfo ci) {
        // Obsolete part of code, maybe in 1.19+ doesn't support
        RegistryEntry.Reference<Item> r = this.getRegistryEntry();
        if (r.isIn(ConversionTags.EATABLE_ITEMS)) {
            ConversionMod.LOGGER.info("Founded always eatable");
            outFoodComponent = new FoodComponent.Builder()
                    .hunger(1)
                    .alwaysEdible()
                    .saturationModifier(0.035f)
                    .build();
        }
    }

    @Inject(method = "getFoodComponent", at = @At("RETURN"), cancellable = true)
    public void getFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        if (outFoodComponent != null) {
            RegistryEntry.Reference<Item> r = this.getRegistryEntry();
            if (r.isIn(ConversionTags.EATABLE_ITEMS)) {
                ConversionMod.LOGGER.info("Founded always eatable");
                outFoodComponent = new FoodComponent.Builder()
                        .hunger(1)
                        .alwaysEdible()
                        .saturationModifier(0.035f)
                        .build();
            }
            cir.setReturnValue(outFoodComponent);
        }
    }
    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    public void isFood(CallbackInfoReturnable<Boolean> cir) {
        RegistryEntry.Reference<Item> r = this.getRegistryEntry();
        if (r.isIn(ConversionTags.EATABLE_ITEMS)) {
            ConversionMod.LOGGER.info("Founded always eatable");
            outFoodComponent = new FoodComponent.Builder()
                    .hunger(1)
                    .alwaysEdible()
                    .saturationModifier(0.035f)
                    .build();
        }
        cir.setReturnValue(cir.getReturnValue() || outFoodComponent != null);
    }
}
