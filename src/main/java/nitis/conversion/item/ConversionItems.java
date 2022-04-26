package nitis.conversion.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import nitis.conversion.ConversionMod;

public class ConversionItems implements ModInitializer {
    public final static Item THING;
    public final static Item TIN_INGOT;
    public final static Item TIN_NUGGET;
    public final static Item ROCK;

    static {
        THING = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    }
    static {
        TIN_INGOT = new Item(new FabricItemSettings().group(ConversionItemGroup.TIN));
    }

    static {
        TIN_NUGGET = new Item(new FabricItemSettings().group(ConversionItemGroup.TIN));
    }

    static {
        ROCK = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    }


    @Override
    public void onInitialize() {
        registryItem("thing", THING); registryItem("tin_ingot", TIN_INGOT); registryItem("tin_nugget", TIN_NUGGET); registryItem("rock", ROCK);
    }
    private static void registryItem(String id, Item item) {
        Registry.register(Registry.ITEM, ConversionMod.idOf(id), item);
    }
    private static void registryItem(String id, Item.Settings itemSettings) {
        Registry.register(Registry.ITEM, ConversionMod.idOf(id), new Item(itemSettings));
    }

}
