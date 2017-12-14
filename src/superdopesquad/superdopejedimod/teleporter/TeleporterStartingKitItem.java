package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.Utilities;


public class TeleporterStartingKitItem extends BaseItem {

		
	public TeleporterStartingKitItem(String unlocalizedName) {
			      
		super(unlocalizedName);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setMaxStackSize(1);
	}
		
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        		
		return SuperDopeJediMod.teleporterManager.teleporterStartingKitItem;
	}
	
	
	@Override
	public void registerRecipe() {
		
		ItemStack itemStackTeleporterParts = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts);
		ItemStack itemStackTeleporterPartsMany = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts, 8);
		ItemStack itemStackThis = new ItemStack(this);
		
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackThis, "xxx", "x x", "xxx", 'x', itemStackTeleporterParts);
    	
		// The recipe to recycle this item and return it's ingredients.
     	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameRecycler(this), null, itemStackTeleporterPartsMany, "x", 'x', itemStackThis);	
	}
	

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		//System.out.println("DEBUG: inside TeleporterStartingKitKitItem:onItemUse");
			
		if (hand != EnumHand.MAIN_HAND) {
			player.sendMessage(new TextComponentString("Try to use your main hand, not your off-hand."));
			return EnumActionResult.PASS;
		}
		
		//boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
     	
    	// Destroy the item in hand, which is THIS.  This is somewhat dangerous, so i do a double-check
		// that the current item actually equals THIS before i do the delete.
    	int index = player.inventory.currentItem;
		ItemStack itemStackCurrentItem = player.inventory.getCurrentItem();
		Item itemCurrentItem = itemStackCurrentItem.getItem();
		if (itemCurrentItem.equals(this)) {
			player.inventory.deleteStack(itemStackCurrentItem);
			//player.inventory.decrStackSize(index, 1);
		}
		else {
			System.out.println("ERROR: current item wasn't what we expected.  Not deleting current item.");
		}
    	
        // Create the A block.
      	IBlockState blockStateTeleporterStartingKit = SuperDopeJediMod.teleporterManager.teleporterStartingKit.getDefaultState();
      	BlockPos blockPosReal = blockPos.up(1);
        world.setBlockState(blockPosReal, blockStateTeleporterStartingKit);
        Block block = world.getBlockState(blockPosReal).getBlock();
   	
    	return EnumActionResult.PASS;
    }
}
