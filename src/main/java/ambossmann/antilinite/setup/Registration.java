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
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

	// Items
	// public static final RegistryObject<Item> ANTILINITE_INGOT =
	// ITEMS.register("antilinite_ingot",
	// () -> new Item(new
	// Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<Item> ANTERIUM =
	// ITEMS.register("anterium",
	// () -> new Item(new
	// Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));

	public static final RegistryObject<Item> GOLDEN_ROD = ITEMS.register("golden_rod",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));

	// public static final RegistryObject<Item> RAW_ANTILINITE =
	// ITEMS.register("raw_antilinite",
	// () -> new Item(new
	// Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<Item> ANTILINITE_NUGGET =
	// ITEMS.register("antilinite_nugget",
	// () -> new Item(new
	// Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// //ArmorMat
	// public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_HELMET
	// = ITEMS.register("antilinite_helmet",
	// () -> new AntiliniteArmorItem(EquipmentSlot.HEAD,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AntiliniteArmorItem>
	// ANTILINITE_CHESTPLATE = ITEMS.register(
	// "antilinite_chestplate",
	// () -> new AntiliniteArmorItem(EquipmentSlot.CHEST,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AntiliniteArmorItem>
	// ANTILINITE_LEGGINGS = ITEMS.register("antilinite_leggings",
	// () -> new AntiliniteArmorItem(EquipmentSlot.LEGS,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_BOOTS
	// = ITEMS.register("antilinite_boots",
	// () -> new AntiliniteArmorItem(EquipmentSlot.FEET,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AnteriumArmorItem> ANTERIUM_HELMET =
	// ITEMS.register("anterium_helmet",
	// () -> new AnteriumArmorItem(EquipmentSlot.HEAD,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AnteriumArmorItem> ANTERIUM_CHESTPLATE
	// = ITEMS.register(
	// "anterium_chestplate",
	// () -> new AnteriumArmorItem(EquipmentSlot.CHEST,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AnteriumArmorItem> ANTERIUM_LEGGINGS =
	// ITEMS.register("anterium_leggings",
	// () -> new AnteriumArmorItem(EquipmentSlot.LEGS,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AnteriumArmorItem> ANTERIUM_BOOTS =
	// ITEMS.register("anterium_boots",
	// () -> new AnteriumArmorItem(EquipmentSlot.FEET,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// //Tools
	// public static final RegistryObject<SwordItem> ANTILINITE_SWORD =
	// ITEMS.register("antilinite_sword",
	// () -> new SwordItem(ModTiers.ANTILINITE, 3, -2.4F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<ShovelItem> ANTILINITE_SHOVEL =
	// ITEMS.register("antilinite_shovel",
	// () -> new ShovelItem(ModTiers.ANTILINITE, 0.9F, -3.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<PickaxeItem> ANTILINITE_PICKAXE =
	// ITEMS.register("antilinite_pickaxe",
	// () -> new PickaxeItem(ModTiers.ANTILINITE, 1, -2.8F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AxeItem> ANTILINITE_AXE =
	// ITEMS.register("antilinite_axe",
	// () -> new AxeItem(ModTiers.ANTILINITE, 4.6F, -3.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<HoeItem> ANTILINITE_HOE =
	// ITEMS.register("antilinite_hoe",
	// () -> new HoeItem(ModTiers.ANTILINITE, -4, 0.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<SwordItem> ANTERIUM_SWORD =
	// ITEMS.register("anterium_sword",
	// () -> new SwordItem(ModTiers.ANTERIUM, 3, -2.4F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<ShovelItem> ANTERIUM_SHOVEL =
	// ITEMS.register("anterium_shovel",
	// () -> new ShovelItem(ModTiers.ANTERIUM, 0.9F, -3.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<PickaxeItem> ANTERIUM_PICKAXE =
	// ITEMS.register("anterium_pickaxe",
	// () -> new PickaxeItem(ModTiers.ANTERIUM, 1, -2.8F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<AxeItem> ANTERIUM_AXE =
	// ITEMS.register("anterium_axe",
	// () -> new AxeItem(ModTiers.ANTERIUM, 4.6F, -3.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// public static final RegistryObject<HoeItem> ANTERIUM_HOE =
	// ITEMS.register("anterium_hoe",
	// () -> new HoeItem(ModTiers.ANTERIUM, -4, 0.0F,
	// new Item.Properties().tab(AntiliniteCreativeTab.TAB_INSTANCE)));
	//
	// // Blocks
	// public static final RegistryObject<Block> ANTILINITE_BLOCK =
	// BLOCKS.register("antilinite_block",
	// () -> new Block(Properties.of(Material.METAL).sound(SoundType.METAL)));
	//
	// public static final RegistryObject<Block> ANTILINITE_ORE =
	// BLOCKS.register("antilinite_ore",
	// () -> new Block(Properties.of(Material.STONE).sound(SoundType.STONE)));
	//
	// public static final RegistryObject<Block> RAW_ANTILINITE_BLOCK =
	// BLOCKS.register("raw_antilinite_block",
	// () -> new Block(Properties.of(Material.METAL).sound(SoundType.METAL)));
	//
	// public static final RegistryObject<Block> ANTERIUM_BLOCK =
	// BLOCKS.register("anterium_block",
	// () -> new Block(Properties.of(Material.METAL).sound(SoundType.METAL)));
	//
	// public static final RegistryObject<Block> ANTERIUM_ORE =
	// BLOCKS.register("anterium_ore",
	// () -> new Block(Properties.of(Material.STONE).sound(SoundType.STONE)));

	// Particles
//	public static final RegistryObject<SimpleParticleType> ANTILINITE_ATTACK = PARTICLES.register("antilinite_attack",
//			() -> new SimpleParticleType(false));
//
//	public static final RegistryObject<SimpleParticleType> ANTERIUM_ATTACK = PARTICLES.register("anterium_attack",
//			() -> new SimpleParticleType(false));

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
