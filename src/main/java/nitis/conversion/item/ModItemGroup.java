package nitis.conversion.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import nitis.conversion.ConversionMod;

public class ModItemGroup {
    public static final ItemGroup TIN = FabricItemGroupBuilder.build(new Identifier(ConversionMod.MODID, "tin"),
            () -> new ItemStack(ModItems.TIN_INGOT));
}
