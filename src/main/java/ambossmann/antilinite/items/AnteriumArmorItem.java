package ambossmann.antilinite.items;

import ambossmann.antilinite.materials.ModArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class AnteriumArmorItem extends ArmorItem {
	
	public AnteriumArmorItem(EquipmentSlot slot, Properties properties) {
		super(ModArmorMaterials.ANTERIUM, slot, properties);
	}

}
