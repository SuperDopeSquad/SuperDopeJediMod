package superdopesquad.superdopejedimod;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class Credit extends BaseItem {

	
	public Credit(String unlocalizedName) {
		
		super(unlocalizedName);
		
		setCreativeTab(CreativeTabs.MISC);
	}
	
	
	public void registerRecipe() {
		
		// Recipe for creating a Credit.
    	ItemStack spiderEyeStack = new ItemStack(Items.SPIDER_EYE);
    	ItemStack rottenFleshStack = new ItemStack(Items.ROTTEN_FLESH);
    	ItemStack boneStack = new ItemStack(Items.BONE);
    	
    	GameRegistry.addRecipe(new ItemStack(this), 
    			"x", 
    			"y", 
    			"z", 
    			
    			'x', spiderEyeStack, 'y', rottenFleshStack, 'z', boneStack);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world,EntityPlayer player) {
		
		Minecraft.getMinecraft().displayGuiScreen(new CreditGUI(item.stackSize));
		
		return super.onItemRightClick(item, world, player);
	}
	
}
