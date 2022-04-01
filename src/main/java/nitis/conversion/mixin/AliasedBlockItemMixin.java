package nitis.conversion.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AliasedBlockItem.class)
public class AliasedBlockItemMixin {
    @Inject(at = @At("HEAD"), method = "<init>")
    private static void init(Block block, Item.Settings settings, CallbackInfo ci){
        FoodComponent.Builder foodBuilder = new FoodComponent.Builder();
        foodBuilder.alwaysEdible();
        foodBuilder.hunger(1);
        settings.food(foodBuilder.build());
    }
}
