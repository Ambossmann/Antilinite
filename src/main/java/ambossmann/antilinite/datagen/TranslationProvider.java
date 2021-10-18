package ambossmann.antilinite.datagen;

import ambossmann.antilinite.Antilinite;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class TranslationProvider extends LanguageProvider {

	public TranslationProvider(DataGenerator gen, String locale) {
		super(gen, Antilinite.MOD_ID, locale);
	}

}
