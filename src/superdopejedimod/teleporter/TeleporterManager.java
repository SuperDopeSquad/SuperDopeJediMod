package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.GeometryUtil;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class TeleporterManager {
	
	public static TeleporterParts teleporterParts = new TeleporterParts("teleporterParts");
	public static TeleporterWrench teleporterWrench = new TeleporterWrench("teleporterWrench");
	public static TeleporterStartingKit teleporterStartingKit = new TeleporterStartingKit("teleporterStartingKit");
	public static TeleporterFinishingKit teleporterFinishingKit = new TeleporterFinishingKit("teleporterFinishingKit");
	public static TeleporterEdgeBlock teleporterEdgeBlock = new TeleporterEdgeBlock("teleporterEdgeBlock");
	public static TeleporterPortalBlock teleporterPortalBlock = new TeleporterPortalBlock("teleporterPortalBlock");

	
	public TeleporterManager() {}
	
	
	public static boolean createStarterTeleporter(EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing) {
		
		boolean goNorthAndSouth = true;
		int heightOfPortal = 5;
		
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			goNorthAndSouth = false;
		}
		else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			goNorthAndSouth = true;
		}
		else {
			player.addChatMessage(new TextComponentString("Try to tap it on one of the sides, not the top or bottom."));
			return false;
		}
		   
    	IBlockState blockStateTeleporterEdgeBlock = SuperDopeJediMod.teleporterManager.teleporterEdgeBlock.getDefaultState();
      	IBlockState blockStateTeleporterPortalBlock = SuperDopeJediMod.teleporterManager.teleporterPortalBlock.getDefaultState();
      	BlockPos blockPosLeftColumn;
    	BlockPos blockPosRightColumn;
    	BlockPos blockPosMiddleColumnTop = blockPos.up(heightOfPortal - 1);
    	BlockPos blockPosMiddleColumnBottomPortalBlock = blockPos.up(1);
    	
    	if (goNorthAndSouth) {
    		blockPosLeftColumn = blockPos.north();
    		blockPosRightColumn = blockPos.south();
    	}
    	else {
    		blockPosLeftColumn = blockPos.west();
    		blockPosRightColumn = blockPos.east();
    	}
    	
    	// Is there enough space for the portal?
        if ((GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosMiddleColumnBottomPortalBlock, (heightOfPortal - 1))) ||
        		(GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosLeftColumn, heightOfPortal)) ||
        		(GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosRightColumn, heightOfPortal))) {
        	
        	player.addChatMessage(new TextComponentString("Not enough space to build a teleporter here.")); 
        	return false;	
        }
        
		// Destroy the existing block.
		world.setBlockToAir(blockPos); 
        	  
		// Create the columns on the left and right.
    	GeometryUtil.buildColumnDestructive(world, blockPosLeftColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	GeometryUtil.buildColumnDestructive(world, blockPosRightColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	    	
     	// Create the middle column, which includes the portal blocks.
        world.setBlockState(blockPos, blockStateTeleporterEdgeBlock);
    	GeometryUtil.buildColumnDestructive(world, blockPosMiddleColumnBottomPortalBlock, (heightOfPortal - 2), blockStateTeleporterPortalBlock);
        world.setBlockState(blockPosMiddleColumnTop, blockStateTeleporterEdgeBlock);
        
        // Let's drop the Finishing Kit.
		ItemStack itemStack = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterFinishingKit);
		EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);
		boolean success = world.spawnEntityInWorld(entityItem);
		//System.out.println("did we succeed in dropping a teleporterKit? " + (success));
        
		return true;
	}
}
