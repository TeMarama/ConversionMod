package nitis.conversion;

import net.fabricmc.api.ModInitializer;
import nitis.conversion.block.ModBlocks;
import net.minecraft.util.Identifier;
import nitis.conversion.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionMod implements ModInitializer {
	private final static ModInitializer[] SERVER_CONTENT;
	public final static String MODID;
	public final static Logger LOGGER;

    public static Identifier idOf(String id) {
		return new Identifier(MODID, id);
    }

    @Override
	public void onInitialize() {
		for (ModInitializer contentList : SERVER_CONTENT) {
			contentList.onInitialize(); //Initialize content list
		}
	}

	public static void log(Object obj) {
		LOGGER.info(obj.toString());
	}
	static {
		SERVER_CONTENT = new ModInitializer[] {
				new ModItems(),
				new ModBlocks()
		};

		MODID = "conversion";
		LOGGER = LoggerFactory.getLogger(MODID);
	}
}
