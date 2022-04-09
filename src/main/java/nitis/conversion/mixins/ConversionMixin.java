package nitis.conversion.mixins;

import net.minecraft.item.Item;
import nitis.conversion.ConversionMod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.item.Item.BLOCK_ITEMS;

@Mixin(TitleScreen.class)
public class ConversionMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
	}
}
