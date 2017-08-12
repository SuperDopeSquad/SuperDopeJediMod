package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class Credit extends BaseItem {

	
	public Credit(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MISC);
		
		this.setMaxStackSize(100);
	}
	
	
	public void registerRecipe() {
		
		// Recipe for creating a Credit.
    	ItemStack spiderEyeStack = new ItemStack(Items.SPIDER_EYE);
    	ItemStack rottenFleshStack = new ItemStack(Items.ROTTEN_FLESH);
    	ItemStack boneStack = new ItemStack(Items.BONE);
    	ItemStack itemStackCredits = new ItemStack(this, 50);
    	
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackCredits, 
    			"x", 
    			"y", 
    			"z", 
    			
    			'x', spiderEyeStack, 'y', rottenFleshStack, 'z', boneStack);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
	
		ItemStack itemStack = worldIn.getHeldItem(playerIn);
		SuperDopeJediMod.superDopeCommonProxy.displayCreditGui(itemStack.getMaxStackSize());

		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
