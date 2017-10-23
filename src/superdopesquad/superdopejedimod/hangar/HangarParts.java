package superdopesquad.superdopejedimod.hangar;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class HangarParts extends BaseItem {

	
	public HangarParts(String unlocalizedName) {
		
        super(unlocalizedName);
        this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	
	public void registerRecipe() {
		
    	ItemStack itemStackmandalorianIronIngot = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
    	ItemStack itemStackquadaniumSteelIngot = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
    	ItemStack itemStackThis = new ItemStack(this, 8);
    	
    	GameRegistry.addShapedRecipe(getRegistryName(), null, itemStackThis, "yxy", "xyx", "yxy", 'x', itemStackmandalorianIronIngot, 'y', itemStackquadaniumSteelIngot);	
	}
}
