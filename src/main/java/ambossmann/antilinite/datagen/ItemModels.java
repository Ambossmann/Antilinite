package ambossmann.antilinite.datagen;

import ambossmann.antilinite.Antilinite;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ItemModels extends ItemModelProvider {

	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Antilinite.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		registerGeneratedItem(Registration.ANTILINITE_INGOT);
		registerGeneratedItem(Registration.GOLDEN_ROD);
		registerGeneratedItem(Registration.RAW_ANTILINITE);
		registerGeneratedItem(Registration.ANTILINITE_NUGGET);
		
		registerGeneratedItem(Registration.ANTILINITE_HELMET);
		registerGeneratedItem(Registration.ANTILINITE_CHESTPLATE);
		registerGeneratedItem(Registration.ANTILINITE_LEGGINGS);
		registerGeneratedItem(Registration.ANTILINITE_BOOTS);
		
		registerHandheldItem(Registration.ANTILINITE_SWORD);
		registerHandheldItem(Registration.ANTILINITE_SHOVEL);
		registerHandheldItem(Registration.ANTILINITE_PICKAXE);
		registerHandheldItem(Registration.ANTILINITE_AXE);
		registerHandheldItem(Registration.ANTILINITE_HOE);
	}

	private ItemModelBuilder registerGeneratedItem(RegistryObject<? extends Item> registryItem) {
		ResourceLocation registryName = registryItem.get().getRegistryName();
		return getBuilder(registryName.getPath()).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", "item/" + registryName.getPath());
	}
	
	private ItemModelBuilder registerHandheldItem(RegistryObject<? extends Item> registryItem) {
		ResourceLocation registryName = registryItem.get().getRegistryName();
		return getBuilder(registryName.getPath()).parent(getExistingFile(mcLoc("item/handheld")))
				.texture("layer0", "item/" + registryName.getPath());
	}

}
