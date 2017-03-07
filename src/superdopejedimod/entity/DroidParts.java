package superdopesquad.superdopejedimod.entity;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class DroidParts extends BaseItem {

	
	public DroidParts(String unlocalizedName) {
		
        super(unlocalizedName);
        this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	
	public void registerRecipe() {
		
		// 1 Mandalorian Iron Ingot and 1 Quadanium Steel Ingot create 8 Droid Parts.
    	ItemStack itemStackmandalorianIronIngot = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
    	ItemStack itemStackquadaniumSteelIngot = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
    	ItemStack itemStackDroidParts = new ItemStack(this, 8);
    	GameRegistry.addRecipe(itemStackDroidParts, "xx", 'x', itemStackmandalorianIronIngot, 'y', itemStackquadaniumSteelIngot);	
	}
}
