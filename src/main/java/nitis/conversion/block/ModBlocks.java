package nitis.conversion.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nitis.conversion.ConversionMod;

public class ModBlocks {

    public static final Block CHECKMATE = registerBlock( "checkmate",
            new Block(FabricBlockSettings.of(Material.STONE).strength(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, ConversionMod.ifOf(name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
            return Registry.register(Registry.ITEM, ConversionMod.ifOf(name),
                    new BlockItem(block, new FabricItemSettings().group(group)));
    }
    public static void registerModBlocks() {
            ConversionMod.LOGGER.info("Registering ModBlocks for" + ConversionMod.MODID);
    }

    public static final Block TIN_BLOCK = registerBlock( "tin_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);

}
