package nitis.conversion.tags;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import nitis.conversion.ConversionMod;

public abstract class ConversionTags {
    public final static TagKey<Item> EATABLE_ITEMS;
    private ConversionTags() {}

    static {
        EATABLE_ITEMS = register("always_eatable");
    }

    private static TagKey<Item> register(String id) {
        return TagKey.of(Registry.ITEM_KEY, ConversionMod.idOf(id));
    }
}
