package ambossmann.antilinite.setup;

import static ambossmann.antilinite.Antilinite.MOD_EVENT_BUS;
import static ambossmann.antilinite.Antilinite.MOD_ID;

import java.util.HashMap;
import java.util.Map;

import ambossmann.antilinite.AntiliniteCreativeTab;
import ambossmann.antilinite.items.AntiliniteArmorItem;
import ambossmann.antilinite.materials.ModTiers;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	private static final Map<RegistryObject<? extends Block>, RegistryObject<BlockItem>> BLOCK_ITEMS = new HashMap<>();

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
			ForgeRegistries.PARTICLE_TYPES,
			MOD_ID);

	public static void init() {
		ITEMS.register(MOD_EVENT_BUS);
		BLOCKS.register(MOD_EVENT_BUS);
		PARTICLES.register(MOD_EVENT_BUS);
		registerBlockItems();
	}

	// Items
	public static final RegistryObject<Item> ANTILINITE_INGOT = ITEMS.register("antilinite_ingot",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<Item> GOLDEN_ROD = ITEMS.register("golden_rod",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<Item> RAW_ANTILINITE = ITEMS.register("raw_antilinite",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<Item> ANTILINITE_NUGGET = ITEMS.register("antilinite_nugget",
			() -> new Item(new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_HELMET = ITEMS.register("antilinite_helmet",
			() -> new AntiliniteArmorItem(EquipmentSlot.HEAD,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_CHESTPLATE = ITEMS.register(
			"antilinite_chestplate",
			() -> new AntiliniteArmorItem(EquipmentSlot.CHEST,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_LEGGINGS = ITEMS.register("antilinite_leggings",
			() -> new AntiliniteArmorItem(EquipmentSlot.LEGS,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<AntiliniteArmorItem> ANTILINITE_BOOTS = ITEMS.register("antilinite_boots",
			() -> new AntiliniteArmorItem(EquipmentSlot.FEET,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<SwordItem> ANTILINITE_SWORD = ITEMS.register("antilinite_sword",
			() -> new SwordItem(ModTiers.ANTILINITE, 3, -2.4F,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<ShovelItem> ANTILINITE_SHOVEL = ITEMS.register("antilinite_shovel",
			() -> new ShovelItem(ModTiers.ANTILINITE, 0.9F, -3.0F,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<PickaxeItem> ANTILINITE_PICKAXE = ITEMS.register("antilinite_pickaxe",
			() -> new PickaxeItem(ModTiers.ANTILINITE, 1, -2.8F,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<AxeItem> ANTILINITE_AXE = ITEMS.register("antilinite_axe",
			() -> new AxeItem(ModTiers.ANTILINITE, 4.6F, -3.0F,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	public static final RegistryObject<HoeItem> ANTILINITE_HOE = ITEMS.register("antilinite_hoe",
			() -> new HoeItem(ModTiers.ANTILINITE, -4, 0.0F,
					new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE)));

	// Blocks
	public static final RegistryObject<Block> ANTILINITE_BLOCK = BLOCKS.register("antilinite_block",
			() -> new Block(Properties.of(Material.METAL).sound(SoundType.METAL)));

	public static final RegistryObject<Block> ANTILINITE_ORE = BLOCKS.register("antilinite_ore",
			() -> new Block(Properties.of(Material.STONE).sound(SoundType.STONE)));

	public static final RegistryObject<Block> RAW_ANTILINITE_BLOCK = BLOCKS.register("raw_antilinite_block",
			() -> new Block(Properties.of(Material.METAL).sound(SoundType.METAL)));

	// Particles
	public static final RegistryObject<SimpleParticleType> ANTILINITE_ATTACK = PARTICLES.register("antilinite_attack",
			() -> new SimpleParticleType(false));

	public static void registerBlockItems() {
		BLOCKS.getEntries().stream().forEach(block -> {
			final Item.Properties properties = new Item.Properties().tab(AntiliniteCreativeTab.INSTANCE);
			final RegistryObject<BlockItem> blockItem = ITEMS.register(block.getId().getPath(),
					() -> new BlockItem(block.get(), properties));
			BLOCK_ITEMS.put(block, blockItem);
		});
	}

	public static RegistryObject<BlockItem> getBlockItem(RegistryObject<? extends Block> block) {
		return BLOCK_ITEMS.get(block);
	}

}
