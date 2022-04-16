package nitis.conversion.mixins;

import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(RegistryEntry.Reference.class)
public interface ReferenceAccessor<T> {
    @Accessor("tags")
    public Set<TagKey<T>> getTags();
}
