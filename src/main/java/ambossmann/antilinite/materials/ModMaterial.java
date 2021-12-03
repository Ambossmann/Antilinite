package ambossmann.antilinite.materials;

import static ambossmann.antilinite.AntiliniteCreativeTab.TAB_INSTANCE;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import ambossmann.antilinite.Antilinite;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class ModMaterial {

	private EnumMap<MaterialParts, RegistryObject<?>> parts;
	private ToolTier toolTier;
	private ArmorMat armorMat;
	private List<MaterialParts> excludedParts;
	private String name;
	private int enchantmentValue;
	private int toolLevel;
	private int toolDurability;
	private float toolSpeed;
	private float toolBaseDamage;
	private int swordDamageMod;
	private float swordAttackSpeedMod;
	private float shovelDamageMod;
	private float shovelAttackSpeedMod;
	private int pickaxeDamageMod;
	private float pickaxeAttackSpeedMod;
	private float axeDamageMod;
	private float axeAttackSpeedMod;
	private int hoeDamageMod;
	private float hoeAttackSpeedMod;
	private int armorDurabilityMod;
	private int[] armorSlotProtections;
	private SoundEvent armorEquipSound;
	private float armorToughness;
	private float armorKnockbackResistance;

	private ModMaterial(Builder builder) {

		excludedParts = builder.getExcludedParts();
		name = builder.getName();
		enchantmentValue = builder.getEnchantmentValue();
		toolLevel = builder.getToolLevel();
		toolDurability = builder.getToolDurability();
		toolSpeed = builder.getToolSpeed();
		toolBaseDamage = builder.getToolBaseDamage();
		swordDamageMod = builder.getSwordDamageMod();
		swordAttackSpeedMod = builder.getSwordAttackSpeedMod();
		shovelDamageMod = builder.getShovelDamageMod();
		shovelAttackSpeedMod = builder.getShovelAttackSpeedMod();
		pickaxeDamageMod = builder.getPickaxeDamageMod();
		pickaxeAttackSpeedMod = builder.getPickaxeAttackSpeedMod();
		axeDamageMod = builder.getAxeDamageMod();
		axeAttackSpeedMod = builder.getAxeAttackSpeedMod();
		hoeDamageMod = builder.getHoeDamageMod();
		hoeAttackSpeedMod = builder.getHoeAttackSpeedMod();
		armorDurabilityMod = builder.getArmorDurabilityMod();
		armorSlotProtections = builder.getArmorSlotProtections();
		armorEquipSound = builder.getArmorEquipSound();
		armorToughness = builder.getArmorToughness();
		armorKnockbackResistance = builder.getArmorKnockbackResistance();

		if (!partNotExcluded(MaterialParts.MATERIAL)) {
			throw new IllegalArgumentException("Can't construct %s Material without base part!".formatted(name));
		}
		parts = new EnumMap<>(MaterialParts.class);
		parts.put(MaterialParts.MATERIAL, Registration.ITEMS.register(name, () -> new Item(tabProperties())));
		toolTier = new ToolTier(toolLevel, toolDurability, toolSpeed, toolBaseDamage, enchantmentValue,
				() -> Ingredient.of((Item) parts.get(MaterialParts.MATERIAL).get()));
		armorMat = new ArmorMat(name, armorDurabilityMod, armorSlotProtections, enchantmentValue, armorEquipSound,
				armorToughness, armorKnockbackResistance,
				() -> Ingredient.of((Item) parts.get(MaterialParts.MATERIAL).get()));

		registerIfNotExcluded(MaterialParts.ORE,
				() -> Registration.BLOCKS.register(name + "_ore", () -> new Block(Properties.of(Material.STONE))));
		registerIfNotExcluded(MaterialParts.BLOCK,
				() -> Registration.BLOCKS.register(name + "_block", () -> new Block(Properties.of(Material.METAL))));
		registerIfNotExcluded(MaterialParts.NUGGET,
				() -> Registration.ITEMS.register(name + "_nugget", () -> new Item(tabProperties())));
		registerIfNotExcluded(MaterialParts.RAW_MATERIAL,
				() -> Registration.ITEMS.register(name + "_raw", () -> new Item(tabProperties())));
		registerIfNotExcluded(MaterialParts.RAW_MATERIAL_BLOCK, () -> Registration.BLOCKS.register(name + "_raw_block",
				() -> new Block(Properties.of(Material.METAL))));

		registerIfNotExcluded(MaterialParts.HELMET, () -> Registration.ITEMS.register(name + "_helmet",
				() -> new ArmorItem(armorMat, EquipmentSlot.HEAD, tabProperties())));
		registerIfNotExcluded(MaterialParts.CHESTPLATE, () -> Registration.ITEMS.register(name + "_chestplate",
				() -> new ArmorItem(armorMat, EquipmentSlot.CHEST, tabProperties())));
		registerIfNotExcluded(MaterialParts.LEGGINGS, () -> Registration.ITEMS.register(name + "_leggings",
				() -> new ArmorItem(armorMat, EquipmentSlot.LEGS, tabProperties())));
		registerIfNotExcluded(MaterialParts.BOOTS, () -> Registration.ITEMS.register(name + "_boots",
				() -> new ArmorItem(armorMat, EquipmentSlot.FEET, tabProperties())));

		registerIfNotExcluded(MaterialParts.SWORD, () -> Registration.ITEMS.register(name + "_sword",
				() -> new SwordItem(toolTier, swordDamageMod, swordAttackSpeedMod, tabProperties())));
		registerIfNotExcluded(MaterialParts.SHOVEL, () -> Registration.ITEMS.register(name + "_shovel",
				() -> new ShovelItem(toolTier, shovelDamageMod, shovelAttackSpeedMod, tabProperties())));
		registerIfNotExcluded(MaterialParts.PICKAXE, () -> Registration.ITEMS.register(name + "_pickaxe",
				() -> new PickaxeItem(toolTier, pickaxeDamageMod, pickaxeAttackSpeedMod, tabProperties())));
		registerIfNotExcluded(MaterialParts.AXE, () -> Registration.ITEMS.register(name + "_axe",
				() -> new AxeItem(toolTier, axeDamageMod, axeAttackSpeedMod, tabProperties())));
		registerIfNotExcluded(MaterialParts.HOE, () -> Registration.ITEMS.register(name + "_hoe",
				() -> new HoeItem(toolTier, hoeDamageMod, hoeAttackSpeedMod, tabProperties())));

		registerIfNotExcluded(MaterialParts.ATTACK_PARTICLE,
				() -> Registration.PARTICLES.register(name + "_attack", () -> new SimpleParticleType(false)));

	}

	private Item.Properties tabProperties() {
		return new Item.Properties().tab(TAB_INSTANCE);
	}

	private void registerIfNotExcluded(MaterialParts part, Supplier<RegistryObject<?>> supplier) {
		if (partNotExcluded(part)) {
			parts.put(part, supplier.get());
		}
	}

	private boolean partNotExcluded(MaterialParts part) {
		return !excludedParts.contains(part);
	}

	public RegistryObject<?> getPart(MaterialParts part) {
		return parts.get(part);
	}

	public String getName() {
		return name;
	}

	public ToolTier getToolTier() {
		return toolTier;
	}

	public ArmorMat getArmorMat() {
		return armorMat;
	}

	public static class ToolTier implements Tier {

		private final int level;
		private final int uses;
		private final float speed;
		private final float damage;
		private final int enchantmentValue;
		private final Supplier<Ingredient> repairIngredient;

		public ToolTier(int level, int uses, float speed, float damage, int enchantmentValue,
				Supplier<Ingredient> repairIngredient) {
			this.level = level;
			this.uses = uses;
			this.speed = speed;
			this.damage = damage;
			this.enchantmentValue = enchantmentValue;
			this.repairIngredient = repairIngredient;
		}

		public int getUses() {
			return this.uses;
		}

		public float getSpeed() {
			return this.speed;
		}

		public float getAttackDamageBonus() {
			return this.damage;
		}

		public int getLevel() {
			return this.level;
		}

		public int getEnchantmentValue() {
			return this.enchantmentValue;
		}

		public Ingredient getRepairIngredient() {
			return this.repairIngredient.get();
		}
	}

	public static class ArmorMat implements ArmorMaterial {

		private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
		private final String name;
		private final int durabilityMultiplier;
		private final int[] slotProtections;
		private final int enchantmentValue;
		private final SoundEvent sound;
		private final float toughness;
		private final float knockbackResistance;
		private final Supplier<Ingredient> repairIngredient;

		public ArmorMat(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
				SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
			this.name = Antilinite.MOD_ID + ":" + name;
			this.durabilityMultiplier = durabilityMultiplier;
			this.slotProtections = slotProtections;
			this.enchantmentValue = enchantmentValue;
			this.sound = sound;
			this.toughness = toughness;
			this.knockbackResistance = knockbackResistance;
			this.repairIngredient = repairIngredient;
		}

		public int getDurabilityForSlot(EquipmentSlot p_40484_) {
			return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
		}

		public int getDefenseForSlot(EquipmentSlot p_40487_) {
			return this.slotProtections[p_40487_.getIndex()];
		}

		public int getEnchantmentValue() {
			return this.enchantmentValue;
		}

		public SoundEvent getEquipSound() {
			return this.sound;
		}

		public Ingredient getRepairIngredient() {
			return this.repairIngredient.get();
		}

		public String getName() {
			return this.name;
		}

		public float getToughness() {
			return this.toughness;
		}

		public float getKnockbackResistance() {
			return this.knockbackResistance;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<MaterialParts> excludedParts = new ArrayList<>();
		private String name = "";
		private int enchantmentValue;
		private int toolLevel = 4;
		private int toolDurability;
		private float toolSpeed;
		private float toolBaseDamage;
		private int swordDamageMod;
		private float swordAttackSpeedMod;
		private float shovelDamageMod;
		private float shovelAttackSpeedMod;
		private int pickaxeDamageMod;
		private float pickaxeAttackSpeedMod;
		private float axeDamageMod;
		private float axeAttackSpeedMod;
		private int hoeDamageMod;
		private float hoeAttackSpeedMod;
		private int armorDurabilityMod;
		private int[] armorSlotProtections;
		private SoundEvent armorEquipSound = SoundEvents.GENERIC_EXPLODE;
		private float armorToughness;
		private float armorKnockbackResistance;

		public ModMaterial build() {
			return new ModMaterial(this);
		}

		public List<MaterialParts> getExcludedParts() {
			return excludedParts;
		}

		public String getName() {
			return name;
		}

		public int getEnchantmentValue() {
			return enchantmentValue;
		}

		@Deprecated
		public int getToolLevel() {
			return toolLevel;
		}

		public int getToolDurability() {
			return toolDurability;
		}

		public float getToolSpeed() {
			return toolSpeed;
		}

		public float getToolBaseDamage() {
			return toolBaseDamage;
		}

		public int getSwordDamageMod() {
			return swordDamageMod;
		}

		public float getSwordAttackSpeedMod() {
			return swordAttackSpeedMod;
		}

		public float getShovelDamageMod() {
			return shovelDamageMod;
		}

		public float getShovelAttackSpeedMod() {
			return shovelAttackSpeedMod;
		}

		public int getPickaxeDamageMod() {
			return pickaxeDamageMod;
		}

		public float getPickaxeAttackSpeedMod() {
			return pickaxeAttackSpeedMod;
		}

		public float getAxeDamageMod() {
			return axeDamageMod;
		}

		public float getAxeAttackSpeedMod() {
			return axeAttackSpeedMod;
		}

		public int getHoeDamageMod() {
			return hoeDamageMod;
		}

		public float getHoeAttackSpeedMod() {
			return hoeAttackSpeedMod;
		}

		public int getArmorDurabilityMod() {
			return armorDurabilityMod;
		}

		public int[] getArmorSlotProtections() {
			return armorSlotProtections;
		}

		public SoundEvent getArmorEquipSound() {
			return armorEquipSound;
		}

		public float getArmorToughness() {
			return armorToughness;
		}

		public float getArmorKnockbackResistance() {
			return armorKnockbackResistance;
		}

		public Builder excludePart(MaterialParts part) {
			this.excludedParts.add(part);
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder enchantmentValue(int enchantmentValue) {
			this.enchantmentValue = enchantmentValue;
			return this;
		}

		@Deprecated
		public Builder toolLevel(int toolLevel) {
			this.toolLevel = toolLevel;
			return this;
		}

		public Builder toolDurability(int toolDurability) {
			this.toolDurability = toolDurability;
			return this;
		}

		public Builder toolSpeed(float toolSpeed) {
			this.toolSpeed = toolSpeed;
			return this;
		}

		public Builder toolBaseDamage(float toolBaseDamage) {
			this.toolBaseDamage = toolBaseDamage;
			return this;
		}

		public Builder swordDamageMod(int swordDamageMod) {
			this.swordDamageMod = swordDamageMod;
			return this;
		}

		public Builder swordAttackSpeedMod(float swordAttackSpeedMod) {
			this.swordAttackSpeedMod = swordAttackSpeedMod;
			return this;
		}

		public Builder shovelDamageMod(float shovelDamageMod) {
			this.shovelDamageMod = shovelDamageMod;
			return this;
		}

		public Builder shovelAttackSpeedMod(float shovelAttackSpeedMod) {
			this.shovelAttackSpeedMod = shovelAttackSpeedMod;
			return this;
		}

		public Builder pickaxeDamageMod(int pickaxeDamageMod) {
			this.pickaxeDamageMod = pickaxeDamageMod;
			return this;
		}

		public Builder pickaxeAttackSpeedMod(float pickaxeAttackSpeedMod) {
			this.pickaxeAttackSpeedMod = pickaxeAttackSpeedMod;
			return this;
		}

		public Builder axeDamageMod(float axeDamageMod) {
			this.axeDamageMod = axeDamageMod;
			return this;
		}

		public Builder axeAttackSpeedMod(float axeAttackSpeedMod) {
			this.axeAttackSpeedMod = axeAttackSpeedMod;
			return this;
		}

		public Builder hoeDamageMod(int hoeDamageMod) {
			this.hoeDamageMod = hoeDamageMod;
			return this;
		}

		public Builder hoeAttackSpeedMod(float hoeAttackSpeedMod) {
			this.hoeAttackSpeedMod = hoeAttackSpeedMod;
			return this;
		}

		public Builder armorDurabilityMod(int armorDurabilityMod) {
			this.armorDurabilityMod = armorDurabilityMod;
			return this;
		}

		public Builder armorSlotProtections(int[] armorSlotProtections) {
			this.armorSlotProtections = armorSlotProtections;
			return this;
		}

		public Builder armorEquipSound(SoundEvent armorEquipSound) {
			this.armorEquipSound = armorEquipSound;
			return this;
		}

		public Builder armorToughness(float armorToughness) {
			this.armorToughness = armorToughness;
			return this;
		}

		public Builder armorKnockbackResistance(float armorKnockbackResistance) {
			this.armorKnockbackResistance = armorKnockbackResistance;
			return this;
		}

	}

}
