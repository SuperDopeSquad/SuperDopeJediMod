package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class Credit extends BaseItem {

	
	public Credit(String unlocalizedName) {
		
		super(unlocalizedName);
		
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	
	public void registerRecipe() {
		
		// Recipe for creating a Credit.
    	ItemStack spiderEyeStack = new ItemStack(Items.spider_eye);
    	ItemStack rottenFleshStack = new ItemStack(Items.rotten_flesh);
    	ItemStack boneStack = new ItemStack(Items.bone);
    	
    	GameRegistry.addRecipe(new ItemStack(this), 
    			"x", 
    			"y", 
    			"z", 
    			
    			'x', spiderEyeStack, 'y', rottenFleshStack, 'z', boneStack);
	}
	
}
