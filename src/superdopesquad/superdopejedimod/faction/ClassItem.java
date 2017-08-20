package superdopesquad.superdopejedimod.faction;


import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class ClassItem extends BaseItem {

	
	public ClassItem(String unlocalizedName) {
		
		super(unlocalizedName);
		
		setCreativeTab(CreativeTabs.MISC);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	
		ItemStack itemStack = player.getHeldItem(hand);
		SuperDopeJediMod.superDopeCommonProxy.displayClassGui(player);

		return super.onItemRightClick(world, player, hand);
	}

	
	@Override
	public void registerRecipe() {
		
		ItemStack cobbleStack = new ItemStack(Blocks.COBBLESTONE);
		ItemStack dirtStack = new ItemStack(Blocks.DIRT);
		
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(SuperDopeJediMod.classItem), "xxx", "xyx", "xxx",'x', cobbleStack, 'y', dirtStack);		
	}	
}
