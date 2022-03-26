package nitis.conversion.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ConversionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Изменения рендера
    }
}
