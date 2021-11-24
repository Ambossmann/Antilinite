package ambossmann.antilinite.datagen;

import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;

public class EnUsTranslation extends TranslationProvider {

	public EnUsTranslation(DataGenerator gen) {
		super(gen, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.antilinitetab", "Antilinite");
		
		add(Registration.ANTILINITE, MaterialParts.BLOCK, "Antilinite Block");
		add(Registration.ANTILINITE, MaterialParts.ORE, "Antilinite Ore");
		add(Registration.ANTILINITE, MaterialParts.RAW_MATERIAL_BLOCK, "Raw Antilinite Block");
		
		add(Registration.ANTILINITE, MaterialParts.MATERIAL, "Antilinite Ingot");
		add(Registration.ANTILINITE, MaterialParts.RAW_MATERIAL, "Raw Antilinite");
		add(Registration.ANTILINITE, MaterialParts.NUGGET, "Antilinite Nugget");
		
		add(Registration.ANTILINITE, MaterialParts.HELMET, "Antilinite Helmet");
		add(Registration.ANTILINITE, MaterialParts.CHESTPLATE, "Antilinite Chestplate");
		add(Registration.ANTILINITE, MaterialParts.LEGGINGS, "Antilinite Leggings");
		add(Registration.ANTILINITE, MaterialParts.BOOTS, "Antilinite Boots");
		
		add(Registration.ANTILINITE, MaterialParts.SWORD, "Antilinite Sword");
		add(Registration.ANTILINITE, MaterialParts.SHOVEL, "Antilinite Shovel");
		add(Registration.ANTILINITE, MaterialParts.PICKAXE, "Antilinite Pickaxe");
		add(Registration.ANTILINITE, MaterialParts.AXE, "Antilinite Axe");
		add(Registration.ANTILINITE, MaterialParts.HOE, "Antilinite Hoe");
		
		add(Registration.GOLDEN_ROD.get(), "Golden Rod");
	}

}
