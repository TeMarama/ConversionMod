package nitis.conversion.mixins;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import nitis.Nullable;
import nitis.conversion.ConversionMod;
import nitis.conversion.tags.ConversionTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    private static FoodComponent lightweightFoodComponent;
    private Nullable<Boolean> isFood = new Nullable<>();

    @Shadow @Deprecated public abstract RegistryEntry.Reference<Item> getRegistryEntry();

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        lightweightFoodComponent = new FoodComponent.Builder()
                .hunger(1)
                .alwaysEdible()
                .saturationModifier(0.035f)
                .build();
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (stack.isFood()) {
            tooltip.add(new TranslatableText("tooltip.food").formatted(Formatting.ITALIC, Formatting.GRAY));
        }
    }

    @Inject(method = "getFoodComponent", at = @At("RETURN"), cancellable = true)
    public void getFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        initializeFood();
        if (isFood.getValue()) {
            cir.setReturnValue(lightweightFoodComponent);

        }
    }
    @Inject(method = "isFood", at = @At("RETURN"), cancellable = true)
    public void isFood(CallbackInfoReturnable<Boolean> cir) {
        if (isFood.getValue()) {
            cir.setReturnValue(true);
        }
    }
    private void initializeFood() {
        if (isFood.isNull()) {
            RegistryEntry.Reference<Item> r = this.getRegistryEntry();

            boolean f = r.isIn(ConversionTags.EATABLE_ITEMS);
            if (f) {
                ConversionMod.log("its food");
            } else {
                ConversionMod.log(ConversionTags.EATABLE_ITEMS);
            }
            isFood.setValue(f);
        }
    }
}
