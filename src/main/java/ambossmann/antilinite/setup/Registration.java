package ambossmann.antilinite.setup;

import static ambossmann.antilinite.Antilinite.MOD_EVENT_BUS;
import static ambossmann.antilinite.Antilinite.MOD_ID;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ambossmann.antilinite.AntiliniteCreativeTab;
import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.materials.ModMaterial;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

	private static final Map<RegistryObject<? extends Block>, RegistryObject<BlockItem>> BLOCK_ITEMS = new HashMap<>();

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
			ForgeRegistries.PARTICLE_TYPES,
			MOD_ID);

	public static void init() {
		ITEMS.register(MOD_EVENT_BUS);
		BLOCKS.register(MOD_EVENT_BUS);
		PARTICLES.register(MOD_EVENT_BUS);
		registerBlockItems();
	}

	public static final ModMaterial ANTILINITE = ModMaterial.builder().name("antilinite").enchantmentValue(12)
			.toolDurability(1820).toolSpeed(8.7F).toolBaseDamage(4.0F).swordDamageMod(3).swordAttackSpeedMod(-2.4F)
			.shovelDamageMod(0.9F).shovelAttackSpeedMod(-3.0F).pickaxeDamageMod(1).pickaxeAttackSpeedMod(-2.8F)
			.axeDamageMod(4.6F).axeAttackSpeedMod(-3.0F).hoeDamageMod(-4).armorDurabilityMod(35)
			.armorSlotProtections(new int[] { 3, 6, 8, 3 }).armorToughness(2.6F).armorKnockbackResistance(0.01F)
			.build();

	public static final ModMaterial ANTERIUM = ModMaterial.builder().name("anterium").enchantmentValue(12)
			.toolDurability(1820).toolSpeed(8.7F).toolBaseDamage(4.0F).swordDamageMod(3).swordAttackSpeedMod(-2.4F)
			.shovelDamageMod(0.9F).shovelAttackSpeedMod(-3.0F).pickaxeDamageMod(1).pickaxeAttackSpeedMod(-2.8F)
			.axeDamageMod(4.6F).axeAttackSpeedMod(-3.0F).hoeDamageMod(-4).armorDurabilityMod(35)
			.armorSlotProtections(new int[] { 3, 6, 8, 3 }).armorToughness(2.6F).armorKnockbackResistance(0.01F)
			.excludePart(MaterialParts.NUGGET).excludePart(MaterialParts.RAW_MATERIAL)
			.excludePart(MaterialParts.RAW_MATERIAL_BLOCK).build();
	
	public static final RegistryObject<Item> GOLDEN_ROD = ITEMS.register("golden_rod",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));

	public static void registerBlockItems() {
		BLOCKS.getEntries().stream().forEach(block -> {
			final Item.Properties properties = new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE);
			final RegistryObject<BlockItem> blockItem = ITEMS.register(block.getId().getPath(),
					() -> new BlockItem(block.get(), properties));
			BLOCK_ITEMS.put(block, blockItem);
		});
	}

	public static Collection<RegistryObject<Block>> getBlocks() {
		return BLOCKS.getEntries();
	}

	public static RegistryObject<BlockItem> getBlockItem(RegistryObject<? extends Block> block) {
		return BLOCK_ITEMS.get(block);
	}

}
