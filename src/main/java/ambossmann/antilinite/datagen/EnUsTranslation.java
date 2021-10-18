package ambossmann.antilinite.datagen;

import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;

public class EnUsTranslation extends TranslationProvider {

	public EnUsTranslation(DataGenerator gen) {
		super(gen, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.antilinitetab", "Antilinite");
		
		add(Registration.ANTILINITE_BLOCK.get(), "Antilinite Block");
		add(Registration.ANTILINITE_ORE.get(), "Antilinite Ore");
		add(Registration.RAW_ANTILINITE_BLOCK.get(), "Raw Antilinite Block");
		
		add(Registration.ANTILINITE_INGOT.get(), "Antilinite Ingot");
		add(Registration.RAW_ANTILINITE.get(), "Raw Antilinite");
		add(Registration.GOLDEN_ROD.get(), "Golden Rod");
		add(Registration.ANTILINITE_NUGGET.get(), "Antilinite Nugget");
		
		add(Registration.ANTILINITE_HELMET.get(), "Antilinite Helmet");
		add(Registration.ANTILINITE_CHESTPLATE.get(), "Antilinite Chestplate");
		add(Registration.ANTILINITE_LEGGINGS.get(), "Antilinite Leggings");
		add(Registration.ANTILINITE_BOOTS.get(), "Antilinite Boots");
		
		add(Registration.ANTILINITE_SWORD.get(), "Antilinite Sword");
		add(Registration.ANTILINITE_SHOVEL.get(), "Antilinite Shovel");
		add(Registration.ANTILINITE_PICKAXE.get(), "Antilinite Pickaxe");
		add(Registration.ANTILINITE_AXE.get(), "Antilinite Axe");
		add(Registration.ANTILINITE_HOE.get(), "Antilinite Hoe");
		
	}

}
