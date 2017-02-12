package superdopesquad.superdopejedimod;


import net.minecraft.block.material.Material;

import net.minecraft.client.Minecraft;

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
import net.minecraftforge.fml.relauncher.SideOnly;


public class FactionBlock extends BaseBlock {

	
	public FactionBlock(String unlocalizedName) {
		
		super(Material.IRON, unlocalizedName);
		
		setCreativeTab(CreativeTabs.MISC);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		  
		Minecraft.getMinecraft().displayGuiScreen(new FactionGUI());
		  
		 return super.onItemRightClick(worldIn, playerIn, hand);
	}
}
