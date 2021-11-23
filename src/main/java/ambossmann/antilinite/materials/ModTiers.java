package ambossmann.antilinite.materials;

import ambossmann.antilinite.setup.Registration;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModTiers implements Tier {
	
	ANTILINITE(4, 1820, 8.7F, 4.0F, 12, Ingredient.of(Registration.ANTILINITE_INGOT.get())),
	ANTERIUM(4, 1820, 8.7F, 4.0F, 12, Ingredient.of(Registration.ANTERIUM.get()));

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Ingredient repairIngredient;

	private ModTiers(int level, int uses, float speed, float damage, int enchantmentValue, Ingredient repairIngredient) {
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
		return this.repairIngredient;
	}

}
