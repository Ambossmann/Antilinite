package ambossmann.antilinite;

import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.setup.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AntiliniteCreativeTab extends CreativeModeTab {
	
	public static final AntiliniteCreativeTab TAB_INSTANCE = new AntiliniteCreativeTab();

	private AntiliniteCreativeTab() {
		super(CreativeModeTab.TABS.length, "antilinitetab");
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack((Item)Registration.ANTILINITE.getPart(MaterialParts.MATERIAL).get());
	}

}
