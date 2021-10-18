package ambossmann.antilinite.datagen;

import java.util.function.Consumer;

import ambossmann.antilinite.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fmllegacy.RegistryObject;

import static ambossmann.antilinite.Antilinite.MOD_ID;

public class Recipes extends RecipeProvider {

	private static final String BOOK_GROUP = "antilinite";

	public Recipes(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

		registerCompressionAndDecompression(Registration.getBlockItem(Registration.ANTILINITE_BLOCK),
				Registration.ANTILINITE_INGOT, 9, consumer);

		registerCompressionAndDecompression(Registration.ANTILINITE_INGOT, Registration.ANTILINITE_NUGGET, 9, consumer);

		registerCompressionAndDecompression(Registration.getBlockItem(Registration.RAW_ANTILINITE_BLOCK),
				Registration.RAW_ANTILINITE, 4, consumer);

		registerSmeltingRecipe(Registration.ANTILINITE_INGOT, Registration.RAW_ANTILINITE, 2.0F, consumer);
		registerBlastingRecipe(Registration.ANTILINITE_INGOT, Registration.RAW_ANTILINITE, 2.0F, consumer);

		ShapedRecipeBuilder.shaped(Registration.GOLDEN_ROD.get(), 3)
				.pattern("g")
				.pattern("g")
				.define('g', Tags.Items.INGOTS_GOLD).group(BOOK_GROUP)
				.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
				.save(consumer);

	}

	private void registerSmeltingRecipe(RegistryObject<? extends Item> result,
			RegistryObject<? extends Item> ingredient, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), result.get(), xp, 200)
				.group(BOOK_GROUP)
				.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
				.save(consumer, new ResourceLocation(MOD_ID,
						result.getId().getPath() + "_from_smelting_" + ingredient.getId().getPath()));
		;
	}

	private void registerBlastingRecipe(RegistryObject<? extends Item> result,
			RegistryObject<? extends Item> ingredient, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient.get()), result.get(), xp, 100)
				.group(BOOK_GROUP)
				.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
				.save(consumer, new ResourceLocation(MOD_ID,
						result.getId().getPath() + "_from_blasting_" + ingredient.getId().getPath()));
	}

	@SuppressWarnings("unused")
	private void registerSmokingRecipe(RegistryObject<? extends Item> result,
			RegistryObject<? extends Item> ingredient, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient.get()), result.get(), xp, 100)
				.group(BOOK_GROUP)
				.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
				.save(consumer, new ResourceLocation(MOD_ID,
						result.getId().getPath() + "_from_smoking_" + ingredient.getId().getPath()));
	}

	private void registerCompressionAndDecompression(RegistryObject<? extends Item> compressed,
			RegistryObject<? extends Item> uncompressed, int count, Consumer<FinishedRecipe> consumer) {
		registerCompression(uncompressed, compressed, count, consumer);
		registerDecompression(compressed, uncompressed, count, consumer);
	}

	private void registerCompression(RegistryObject<? extends Item> ingredient, RegistryObject<? extends Item> result,
			int count, Consumer<FinishedRecipe> consumer) {
		ResourceLocation name = new ResourceLocation(MOD_ID,
				result.getId().getPath() + "_from_" + ingredient.getId().getPath());
		if (count <= 6) {
			String columnOne = "";
			String columnTwo = "";
			for (int i = 1; i <= count; i++) {
				if (i % 2 == 1) {
					columnOne += "i";
				} else {
					columnTwo += "i";
				}
			}
			if (count % 2 == 1) {
				columnTwo = columnTwo + " ";
			}
			ShapedRecipeBuilder.shaped(result.get())
					.pattern(columnOne)
					.pattern(columnTwo)
					.define('i', ingredient.get())
					.group(BOOK_GROUP)
					.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
					.save(consumer, name);
		} else if (count <= 9) {
			String columnThree = "";
			for (int i = 0; i < count - 6; i++) {
				columnThree += "i";
			}
			for (int i = 0; i < 3 - (count - 6); i++) {
				columnThree += " ";
			}
			ShapedRecipeBuilder.shaped(result.get())
					.pattern("iii")
					.pattern("iii")
					.pattern(columnThree)
					.define('i', ingredient.get())
					.group(BOOK_GROUP)
					.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
					.save(consumer, name);
		} else if (count <= 1 || count > 9) {
			throw new IllegalArgumentException("count needs to be beetwenn 2 and 9");
		}
	}

	private void registerDecompression(RegistryObject<? extends Item> ingredient, RegistryObject<? extends Item> result,
			int count, Consumer<FinishedRecipe> consumer) {
		ResourceLocation name = new ResourceLocation(MOD_ID,
				result.getId().getPath() + "_from_" + ingredient.getId().getPath());
		ShapelessRecipeBuilder.shapeless(result.get(), count)
				.requires(ingredient.get())
				.group(BOOK_GROUP)
				.unlockedBy("", InventoryChangeTrigger.TriggerInstance.hasItems(ingredient.get()))
				.save(consumer, name);
	}

}
