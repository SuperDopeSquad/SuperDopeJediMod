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


public class Faction extends BaseItem {

	
	public Faction(String unlocalizedName) {
		
		super(unlocalizedName);
		
		setCreativeTab(CreativeTabs.MISC);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	
		ItemStack itemStack = player.getHeldItem(hand);
		SuperDopeJediMod.superDopeCommonProxy.displayFactionGui(player);

		return super.onItemRightClick(world, player, hand);
	}
}