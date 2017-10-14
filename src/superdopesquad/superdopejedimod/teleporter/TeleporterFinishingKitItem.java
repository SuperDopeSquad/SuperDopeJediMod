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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class TeleporterFinishingKitItem extends BaseItem {

	
	private TeleporterEntity _entityTeleporterA;
	private EnumFacing _facingTeleporterA;
	private BlockPos _blockPosTeleporterA;

		
	public TeleporterFinishingKitItem(String unlocalizedName) {
			      
		super(unlocalizedName, false);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setMaxStackSize(1);
	}
			

	public void setTeleporterData(EnumFacing facing, BlockPos blockPos, TeleporterEntity entity) {
		
		this._facingTeleporterA = facing;
		this._blockPosTeleporterA = blockPos;
		this._entityTeleporterA = entity;
	}
	

	public Item getItemDropped(int metadata, Random random, int fortune) {
        		
		return SuperDopeJediMod.teleporterManager.teleporterFinishingKitItem;
	}
	
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		//System.out.println("DEBUG: inside TeleporterFinishingKitKitItem:onItemUse");
		
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
           
		// Create the B block.
      	IBlockState blockStateTeleporterFinishingKit = SuperDopeJediMod.teleporterManager.teleporterFinishingKit.getDefaultState();
      	BlockPos blockPosReal = blockPos.up(1);
        world.setBlockState(blockPosReal, blockStateTeleporterFinishingKit);
        Block block = world.getBlockState(blockPosReal).getBlock();
        ((TeleporterFinishingKit)block).setTeleporterData(this._facingTeleporterA, this._blockPosTeleporterA, this._entityTeleporterA);
   	
    	return EnumActionResult.PASS;
    }
}
