package ambossmann.antilinite.datagen;

import ambossmann.antilinite.Antilinite;
import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.materials.ModMaterial;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModels extends ItemModelProvider {

	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Antilinite.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		registerGeneratedItem(Registration.GOLDEN_ROD);
		
		registerMaterial(Registration.ANTILINITE);
		registerMaterial(Registration.ANTERIUM);
	}

	private void registerMaterial(ModMaterial material) {
		for (MaterialParts part : MaterialParts.values()) {
			@SuppressWarnings("unchecked")
			RegistryObject<Item> partItem = (RegistryObject<Item>) material.getPart(part);
			if (partItem != null) {
				switch (part) {
					case HELMET:
					case CHESTPLATE:
					case LEGGINGS:
					case BOOTS:
					case MATERIAL:
					case NUGGET:
					case RAW_MATERIAL:
						registerGeneratedItem(partItem);
						break;
					case SWORD:
					case SHOVEL:
					case PICKAXE:
					case AXE:
					case HOE:
						registerHandheldItem(partItem);
						break;
					default:
						break;
				}
			}
		}
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
