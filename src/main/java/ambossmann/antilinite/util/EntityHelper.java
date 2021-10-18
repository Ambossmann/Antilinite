package ambossmann.antilinite.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class EntityHelper {

	private EntityHelper() {
	}

	public static int entityArmorPieces(LivingEntity entity, ArmorMaterial armorMaterial) {
		int i = 0;
		for (ItemStack stack : entity.getArmorSlots()) {
			if (stack.getItem() instanceof ArmorItem armorItem) {
				if (armorItem.getMaterial() == armorMaterial) {
					i++;
				} 
			}
		}
		return i;
	}
	
	public static boolean isPiglinMob(Entity entity) {
		return entity instanceof AbstractPiglin || entity instanceof ZombifiedPiglin;
	}
}
