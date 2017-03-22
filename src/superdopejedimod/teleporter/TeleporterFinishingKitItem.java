package superdopesquad.superdopejedimod.teleporter;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class TeleporterFinishingKitItem extends BaseItem {

	
	private TeleporterData _teleporterData;


		
	public TeleporterFinishingKitItem(String unlocalizedName) {
			      
		super(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(1);
	}
		
		
//	@Override
//	public void registerRecipe() {
//		
//		ItemStack itemStackTeleporterParts = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts);
//		ItemStack itemStackThis = new ItemStack(this);
//		ItemStack itemStackTeleporterPartsMany = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts, 3);
//		
//    	GameRegistry.addRecipe(itemStackThis, " x ", " x ", " x ", 'x', itemStackTeleporterParts);	
//    	GameRegistry.addRecipe(itemStackTeleporterPartsMany, "x", 'x', itemStackThis);	
//	}
	
	
	public void setTeleporterData(TeleporterData teleporterData) {
		
		this._teleporterData = teleporterData;
		System.out.println("finishingKit's teleportData set: " + this._teleporterData.getBlockPosThere().toString());
	}
	
	
	public TeleporterData getTeleporterData() {
		
		return this._teleporterData;
	}
	

	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isTeleporterStarterKit = (blockClicked instanceof TeleporterStartingKit);
       	boolean isTeleporterFinishingKit = (blockClicked instanceof TeleporterFinishingKit);
           	
       	String teleporterDataString;
       	if (this._teleporterData == null) {
       		teleporterDataString = "teleporterData is NULL.";
       	}
       	else {
       		teleporterDataString = this._teleporterData.getBlockPosThere().toString();
       	}
       	
       	System.out.println("inside onItemUse: " + teleporterDataString);
       	
        //Create the B block.
      	IBlockState blockStateTeleporterFinishingKit = SuperDopeJediMod.teleporterManager.teleporterFinishingKit.getDefaultState();
      	//BlockPos blockPosLeftColumn;
    	//BlockPos blockPosRightColumn;
    	//BlockPos blockPosFinishingKit;
      	
      	BlockPos blockPosReal = blockPos.up(1);
        world.setBlockState(blockPosReal, blockStateTeleporterFinishingKit);
        Block block = world.getBlockState(blockPosReal).getBlock();
        ((TeleporterFinishingKit)block).setTeleporterData(this.getTeleporterData());
  
       	
       	
       	//return;
       	
    	//System.out.println("DEBUG: inside TeleporterWrench:onItemUse: " + blockClicked.toString() + 
    	//		" : " + hand.name() + " : " + facing.getName() + " : " + (isTeleporterStarterKit) + " : " + (isTeleporterKit));
    	
//    	// If we are on the server, and we are being held in the main hand, and this is actually a droidkit , ...
//    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND) && (isTeleporterStarterKit)) {
//        	    		
//    		// Create a starter teleporter.
//    		if (TeleporterManager.createTeleporter(player, world, blockPos, facing, TeleporterSide.SIDE_B)) {
//    		
//    			// Return something relevant.
//    			return EnumActionResult.SUCCESS;
//    		}
//    	}
   	
    	return EnumActionResult.PASS;
    }
}
