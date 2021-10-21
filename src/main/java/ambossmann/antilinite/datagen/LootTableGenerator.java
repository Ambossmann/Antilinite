package ambossmann.antilinite.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ambossmann.antilinite.Antilinite;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.fmllegacy.RegistryObject;

public class LootTableGenerator extends LootTableProvider {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	private Map<ResourceLocation, LootTable> tables = new HashMap<>();
	protected final DataGenerator generator;

	public LootTableGenerator(DataGenerator generator) {
		super(generator);
		this.generator = generator;
	}

	@Override
	public void run(HashCache cache) {
		createLootTables();
		writeTables(cache);
	}

	private void createLootTables() {
		Registration.getBlocks().stream()
				.forEach(registryBlock -> createStandardBlockTable(registryBlock));
	}

	private void writeTables(HashCache cache) {

		Path outputFolder = this.generator.getOutputFolder();
		tables.forEach((key, lootTable) -> {
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			try {
				DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
			} catch (IOException e) {
				Antilinite.LOGGER.error("Couldn't write loot table {}", path, (Object) e);
			}
		});
	}

	private void createStandardBlockTable(RegistryObject<? extends Block> registryBlock) {
		Block block = registryBlock.get();
		LootPool.Builder builder = LootPool.lootPool()
				.name(registryBlock.getId().getPath())
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(block))
				.when(ExplosionCondition.survivesExplosion());
		tables.put(block.getLootTable(),
				LootTable.lootTable().withPool(builder).setParamSet(LootContextParamSets.BLOCK).build());
	}

}
