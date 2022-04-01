package nitis.conversion.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nitis.conversion.ConversionMod;

public class ModItems {
    public static final Item THING = registerItem( "thing",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ConversionMod.MODID, name), item);
    }

    public static void registerModItems() {
        ConversionMod.LOGGER.info("Registering Mod Items for " + ConversionMod.MODID);
    }
}
