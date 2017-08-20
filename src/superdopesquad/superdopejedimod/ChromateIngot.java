package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ChromateIngot extends BaseItem{

public ChromateIngot(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
public void registerRecipe() {
	
	GameRegistry.addSmelting(SuperDopeJediMod.chromateOre, new ItemStack(SuperDopeJediMod.chromateIngot), 1.0F);
}

}
