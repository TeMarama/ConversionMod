package nitis.conversion;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import nitis.conversion.block.ConversionBlocks;
import net.minecraft.util.Identifier;
import nitis.conversion.item.ConversionItems;
import nitis.conversion.world.biome.ConversionBiomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionMod implements ModInitializer, ClientModInitializer {
	private final static ModInitializer[] SERVER_CONTENT;
	private final static ClientModInitializer[] CLIENT_CONTENT;
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
	@Override
	public void onInitializeClient() {
		for (ClientModInitializer contentList : CLIENT_CONTENT) {
			contentList.onInitializeClient();
		}
	}
	public static void log(Object obj) {
		log(obj.toString());
	}
	public static void log(String text) {
		LOGGER.info(text);
	}
	static {
		SERVER_CONTENT = new ModInitializer[] {
				new ConversionItems(),
				new ConversionBlocks(),
				new ConversionBiomes(),
		};
		CLIENT_CONTENT = new ClientModInitializer[0];

		MODID = "conversion";
		LOGGER = LoggerFactory.getLogger("Conversion");
	}
}
