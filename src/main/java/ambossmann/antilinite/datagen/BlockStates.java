package ambossmann.antilinite.datagen;

import static ambossmann.antilinite.Antilinite.MOD_ID;

import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		registerStandardBlock(Registration.ANTILINITE_BLOCK);
		registerStandardBlock(Registration.ANTILINITE_ORE);
		registerStandardBlock(Registration.RAW_ANTILINITE_BLOCK);
	}
	
	private void registerStandardBlock(RegistryObject<? extends Block> registryBlock) {
		Block block = registryBlock.get();
		ResourceLocation registryName = block.getRegistryName();
		BlockModelBuilder blockModel = models().cubeAll(registryName.getPath(),
				new ResourceLocation(MOD_ID, "block/"+registryName.getPath()));
		getVariantBuilder(block).forAllStates((state) -> {
			return ConfiguredModel.builder().modelFile(blockModel).build();
		});
		itemModels().withExistingParent(registryName.getPath(), new ResourceLocation(MOD_ID, "block/"+registryName.getPath()));
	}

}
