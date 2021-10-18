package ambossmann.antilinite.items;

import ambossmann.antilinite.materials.ModArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class AntiliniteArmorItem extends ArmorItem {

	public AntiliniteArmorItem(EquipmentSlot slot, Properties properties) {
		super(ModArmorMaterials.ANTILINITE, slot, properties);
	}

}
