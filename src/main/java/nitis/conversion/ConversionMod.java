package nitis.conversion;

import net.fabricmc.api.ModInitializer;
import nitis.conversion.block.ModBlocks;
import net.minecraft.util.Identifier;
import nitis.conversion.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionMod implements ModInitializer {
	public final static String MODID = "conversion";
	public final static Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier ifOf(String id) {
		return new Identifier(MODID, id);
    }

    @Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}
}
