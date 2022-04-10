package nitis.conversion.block;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import nitis.conversion.ConversionMod;
import org.jetbrains.annotations.Nullable;

public class ModBlocks implements ModInitializer {
    public final static Block CHECKMATE, TIN_BLOCK, MAPLE_LOG;
    public final static BlockItem CHECKMATE_ITEM, TIN_BLOCK_ITEM, MAPLE_LOG_ITEM;

    @Override
    public void onInitialize() {
        registry(
                RegistryType.Both,
                "checkmate",
                CHECKMATE,
                CHECKMATE_ITEM
        );
        registry(
                RegistryType.Both,
                "tin_block",
                TIN_BLOCK,
                TIN_BLOCK_ITEM
        );
        registry(
                RegistryType.Both,
                "maple_log",
                MAPLE_LOG,
                MAPLE_LOG_ITEM
        );
    }
    private static void registry(RegistryType type, String id, AbstractBlock.Settings blockSettings, Item item) {
        Block block = new Block(blockSettings);
        if (type.regBlock()) registryBlock(id, block);
        if (type.regItem()) registryBlockItem(id, item);
    }
    private static void registry(RegistryType type, String id, Block block, Item item) {
        if (type.regBlock()) registryBlock(id, block);
        if (type.regItem()) registryBlockItem(id, item);
    }
    private static void registry(RegistryType type, String id, Block block, Settings itemSettings) {
        BlockItem item = new BlockItem(block, itemSettings);
        if (type.regBlock()) registryBlock(id, block);
        if (type.regItem()) registryBlockItem(id, item);
    }
    private static void registry(RegistryType type, String id, @Nullable AbstractBlock.Settings blockSettings, @Nullable Settings itemSettings) {
        Block block = new Block(blockSettings);
        BlockItem item = new BlockItem(block, itemSettings);
        if (type.regBlock()) registryBlock(id, block);
        if (type.regItem()) registryBlockItem(id, item);
    }
    private static void registryBlock(String id, Block block) {
        Registry.register(Registry.BLOCK, ConversionMod.idOf(id), block);
    }
    private static void registryBlockItem(String id, Item item) {
        Registry.register(Registry.ITEM, ConversionMod.idOf(id), item);
    }
    private static PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }
    static {
        CHECKMATE = new Block(FabricBlockSettings.of(Material.STONE).strength(3f).requiresTool());
        CHECKMATE_ITEM = new BlockItem(CHECKMATE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        TIN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool());
        TIN_BLOCK_ITEM = new BlockItem(TIN_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        MAPLE_LOG = createLogBlock(MapColor.ORANGE, MapColor.TERRACOTTA_ORANGE);
        MAPLE_LOG_ITEM = new BlockItem(MAPLE_LOG, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    }
    private enum RegistryType {
        Both(true, true), // Add Block and BlockItem
        OnlyBlock(true, false), // Add only Block
        OnlyItem(false, true), // Add only BlockItem
        None(false, false); //Disable
        private boolean block, item;

        RegistryType(Boolean block, Boolean item) {
            this.block = block;
            this.item = item;
        }

        public boolean regBlock() {
            return block;
        }
        public boolean regItem() {
            return item;
        }
    }
}
