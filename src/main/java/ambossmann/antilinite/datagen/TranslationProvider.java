package ambossmann.antilinite.datagen;

import ambossmann.antilinite.Antilinite;
import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.materials.ModMaterial;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class TranslationProvider extends LanguageProvider {

	public TranslationProvider(DataGenerator gen, String locale) {
		super(gen, Antilinite.MOD_ID, locale);
	}

	protected void add(ModMaterial material, MaterialParts part, String name) {
		IForgeRegistryEntry<?> key = material.getPart(part).get();
		if (key instanceof Block) {
			add((Block) key, name);
		} else if (key instanceof Item) {
			add((Item) key, name);
		} else {
			throw new UnsupportedOperationException("%s %s is instance of unsupported class %s"
					.formatted(material.getName(), part.toString(), key.getClass().getSimpleName()));
		}
	}

}
