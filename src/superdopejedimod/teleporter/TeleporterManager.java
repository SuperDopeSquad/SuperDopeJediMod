package superdopesquad.superdopejedimod.teleporter;


import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.GeometryUtil;
import superdopesquad.superdopejedimod.SuperDopeJediMod;




public class TeleporterManager {
	
	public static int TELEPORTER_HEIGHT = 5;

	public static TeleporterParts teleporterParts = new TeleporterParts("teleporterParts");
	public static TeleporterWrench teleporterWrench = new TeleporterWrench("teleporterWrench");
	public static TeleporterStartingKit teleporterStartingKit = new TeleporterStartingKit("teleporterStartingKit");
	public static TeleporterFinishingKit teleporterFinishingKit = new TeleporterFinishingKit("teleporterFinishingKit");
	public static TeleporterFinishingKitItem teleporterFinishingKitItem = new TeleporterFinishingKitItem("teleporterFinishingKitItem");
	public static TeleporterEdgeBlock teleporterEdgeBlock = new TeleporterEdgeBlock("teleporterEdgeBlock");
	public static TeleporterPortalBlock teleporterPortalBlock = new TeleporterPortalBlock("teleporterPortalBlock");

	
	public TeleporterManager() {}
	
	
	public static boolean createTeleporter(EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, TeleporterSide teleporterSide) {
		
		boolean goNorthAndSouth = true;
		int heightOfPortal = TELEPORTER_HEIGHT;
		
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
      	IBlockState blockStateTeleporterFinishingKit = SuperDopeJediMod.teleporterManager.teleporterFinishingKit.getDefaultState();
      	BlockPos blockPosLeftColumn;
    	BlockPos blockPosRightColumn;
    	BlockPos blockPosFinishingKit;
    	BlockPos blockPosMiddleColumnTop = blockPos.up(heightOfPortal - 1);
    	BlockPos blockPosMiddleColumnBottomPortalBlock = blockPos.up(1);
    	
    	if (goNorthAndSouth) {
    		blockPosLeftColumn = blockPos.north();
    		blockPosRightColumn = blockPos.south();
    		blockPosFinishingKit = blockPos.west();
    	}
    	else {
    		blockPosLeftColumn = blockPos.west();
    		blockPosRightColumn = blockPos.east();
    		blockPosFinishingKit = blockPos.north();
    	}
    	
    	// Is there enough space for the portal?
        if ((GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosMiddleColumnBottomPortalBlock, (heightOfPortal - 1))) ||
        		(GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosLeftColumn, heightOfPortal)) ||
        		(GeometryUtil.checkIfColumnWouldBeDestructive(world, blockPosRightColumn, heightOfPortal))) {
        	
        	player.addChatMessage(new TextComponentString("Not enough space to build a teleporter here.")); 
        	return false;	
        }
        
               
        //teleporterDataHere.setSide(teleporterSide);
        
        // Only used in the SIDE_B case.
        TeleporterData teleporterDataFromFinishingKit = null;
        
        
        if (teleporterSide == TeleporterSide.SIDE_A) {
        	
            // Let's store critical information in TeleporterData.
            //TeleporterData teleporterDataIntoTeleporterA; // = new TeleporterData();
            TeleporterData teleporterDataIntoFinishingKit;
        	
            //teleporterDataIntoTeleporterA = new TeleporterData();
        	teleporterDataIntoFinishingKit = new TeleporterData();
        	
        	//teleporterDataIntoTeleporterA.setSide(TeleporterSide.SIDE_A);
        	teleporterDataIntoFinishingKit.setSide(TeleporterSide.SIDE_B);
        	
        	//teleporterDataIntoTeleporterA.setHere(blockPos, facing);
        	teleporterDataIntoFinishingKit.setThere(blockPos, facing);
        	
            // Create the finishing kit and drop it on the ground.
        	ItemStack itemStack = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterFinishingKitItem);
        	((TeleporterFinishingKitItem)itemStack.getItem()).setTeleporterData(teleporterDataIntoFinishingKit);
        	EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);		
        	boolean success = world.spawnEntityInWorld(entityItem);
        }
        
        else {
        	
            // Create the B block.
            //world.setBlockState(blockPosFinishingKit, blockStateTeleporterFinishingKit);
            
        	
        	// Useles code except for the ASSERT.
        	Block blockFinishingKit = world.getBlockState(blockPos).getBlock();
            if (!(blockFinishingKit instanceof TeleporterFinishingKit) ){
             	System.out.println("WTF! block i just hit is not TeleporterFinishingKit: " + blockFinishingKit.toString());        	
             	return false;
            }
        	
            teleporterDataFromFinishingKit = ((TeleporterFinishingKit)blockFinishingKit).getTeleporterData();
//            	
//            TeleporterData teleporterDataFromFinishingKit = ((TeleporterFinishingKit)blockFinishingKit).getTeleporterData();
//            if (teleporterDataFromFinishingKit == null) {		
//            	System.out.println("WTF! teleporterData is null in the finishing kit.");
//            	return false;
//            }
//            	
//            teleporterDataFromFinishingKit.setHere(blockPos, facing);
//            System.out.println("inside createTransporter: here: " + teleporterDataFromFinishingKit.getBlockPosHere().toString() + " there: " + teleporterDataFromFinishingKit.getBlockPosThere().toString());
//            
//            System.out.println("teleporterData made it here: " + teleporterDataHere.toString());
//        	
//            teleporterDataHere.setSide(teleporterSide);
//           	teleporterDataThere.setSide(TeleporterSide.SIDE_B);
//            
//           	teleporterDataThere.setThere(blockPos, facing);
        }
        
        
        
		// Destroy the existing block.
        // MC-to-do: assert what blocks are already there.
		world.setBlockToAir(blockPos); 
        	  
		// Create the columns on the left and right.
    	GeometryUtil.buildColumnDestructive(world, blockPosLeftColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	GeometryUtil.buildColumnDestructive(world, blockPosRightColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	    	
     	// Create the middle column, which includes the portal blocks.
        world.setBlockState(blockPos, blockStateTeleporterEdgeBlock);
    	List<BlockPos> blockPositions = GeometryUtil.buildColumnDestructive(world, blockPosMiddleColumnBottomPortalBlock, (heightOfPortal - 2), blockStateTeleporterPortalBlock);
        world.setBlockState(blockPosMiddleColumnTop, blockStateTeleporterEdgeBlock);
        
        // Let's drop the Finishing Kit.
        //TeleporterFinishingKit item = new TeleporterFinishingKit("teleporterFinishingKit");
        //item.setTeleporterData(teleporterDataIntoFinishingKit);
        //ItemStack itemStack = new ItemStack(item);
        
//        // Let's set the teleporterData in this portal.
//        for (int i = 0; i < blockPositions.size(); i++) {
//        	BlockPos blockPosTemp = blockPositions.get(i);
//        	Block block = world.getBlockState(blockPosTemp).getBlock();
//        	System.out.println("DEBUG: " + block.toString());
//        	if (block instanceof TeleporterPortalBlock) {
//        		((TeleporterPortalBlock) block).setTeleporterData(teleporterDataHere);
//        	}
//        }
        
        // Create the B block.
        //world.setBlockState(blockPosFinishingKit, blockStateTeleporterFinishingKit);
        //Block block = world.getBlockState(blockPosFinishingKit).getBlock();
        //((TeleporterFinishingKit)block).setTeleporterData(teleporterDataIntoFinishingKit);
        
        if (teleporterSide == TeleporterSide.SIDE_A) {
        
//        	System.out.println("before i set: " + teleporterDataIntoFinishingKit.getDestination().toString());
//        
//        	ItemStack itemStack = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterFinishingKitItem);
//        	((TeleporterFinishingKitItem)itemStack.getItem()).setTeleporterData(teleporterDataIntoFinishingKit);
//		
//        	EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);		
//        	boolean success = world.spawnEntityInWorld(entityItem);
        }
        else {
        	
//        	Block blockFinishingKit = world.getBlockState(blockPos).getBlock();
//            if (!(blockFinishingKit instanceof TeleporterFinishingKit) ){
//             	System.out.println("WTF! block i just hit is not TeleporterFinishingKit: " + blockFinishingKit.toString());        	
//             	return false;
//            }
        	
        	if (teleporterDataFromFinishingKit == null) {
        		System.out.println("WTF! forgot to retrieve teleporterDataFromFinishingKit");        	
             	return false;
        	}
        	
        	Block blockFinishingKit = world.getBlockState(blockPos).getBlock();
            if (!(blockFinishingKit instanceof TeleporterEdgeBlock) ){
             	System.out.println("WTF! block i just hit is not TeleporterEdgeBlock: " + blockFinishingKit.toString());        	
             	return false;
            }
        	
//            TeleporterData teleporterDataFromFinishingKit = ((TeleporterFinishingKit)blockFinishingKit).getTeleporterData();
//            if (teleporterDataFromFinishingKit == null) {		
//            	System.out.println("WTF! teleporterData is null in the finishing kit.");
//            	return false;
//            }
            
            // The four pieces of data we need to connect the teleporters.
            BlockPos blockPosA = teleporterDataFromFinishingKit.getBlockPosThere();
            EnumFacing facingA = teleporterDataFromFinishingKit.getFacingThere();
            BlockPos blockPosB = blockPos;
            EnumFacing facingB = facing;
            
            // Let's set up the teleporterData that the first Portal needs.
            TeleporterData teleporterDataForPortalA = new TeleporterData();
            teleporterDataForPortalA.setHere(blockPosA, facingA);
            teleporterDataForPortalA.setThere(blockPosB, facingB);
            teleporterDataForPortalA.setSide(TeleporterSide.SIDE_A);
  
            // Let's set up the teleporterData that the second Portal needs.
            TeleporterData teleporterDataForPortalB = new TeleporterData();
            teleporterDataForPortalB.setHere(blockPosB, facingB);         	
            teleporterDataForPortalB.setThere(blockPosA, facingA);
            teleporterDataForPortalB.setSide(TeleporterSide.SIDE_B);
            
            //teleporterDataFromFinishingKit.setHere(blockPos, facing);
            //System.out.println("inside createTransporter: here: " + teleporterDataFromFinishingKit.getBlockPosHere().toString() + " there: " + teleporterDataFromFinishingKit.getBlockPosThere().toString());
  
        	
        	// Set the teleport information for the original teleporter that points to this one.
        	BlockPos blockPosThere = teleporterDataFromFinishingKit.getBlockPosThere();
        	if (blockPosThere == null) {
        		System.out.println("ERROR! bad pointer to original teleporter.");
        		return false;
        	}
        	EnumFacing enumFacingThere = teleporterDataFromFinishingKit.getFacingThere();
        	if (enumFacingThere == null) {
        		System.out.println("ERROR! bad pointer to original teleporter.");
        		return false;
        	}
        	
        	// Set the first teleporter, and get it to point here.
        	System.out.println("Do i trust that this is the data for the first one?" + blockPosThere.toString() + " setting there to : " + blockPos.toString());
        	//TeleporterManager.findAndSetDestination(world, blockPosThere, blockPos, facing);
        	TeleporterManager.findAndSetTeleporterData(world, blockPosA, teleporterDataForPortalA);
        	
        	
        	// Set the second teleporter, and get it to point there.
          	System.out.println("Do i trust that this is the data for the second one?" + blockPos.toString() + " setting there to : " + blockPosThere.toString());
        	//TeleporterManager.findAndSetDestination(world, blockPos, blockPosThere, enumFacingThere);
          	TeleporterManager.findAndSetTeleporterData(world, blockPosB, teleporterDataForPortalB);
        }
                
		return true;
	}
	
		
//	public static boolean findAndSetDestination(World world, BlockPos blockPosHere, BlockPos blockPosDestination, EnumFacing facingDestination) {
//		
//		// We assume this initial blockPos is an Edge Block.
//		Block blockHere = world.getBlockState(blockPosHere).getBlock();
//    	if (!(blockHere instanceof TeleporterEdgeBlock)) {
//    		System.out.println("ERROR! bad value found in findAndSetDestination: " + blockHere.toString());
//    		return false;
//    	}
// 
//    	int countOfPortalBlocks = TeleporterManager.TELEPORTER_HEIGHT - 2;
//    	BlockPos blockPosBottomPortalBlock = blockPosHere.up(1);
//    	
//    	//TeleporterData teleporterData = new TeleporterData();
//    	//teleporterData.
//    	
//    	for (int i = 0; i < countOfPortalBlocks; i++) {
//    		
//    		Block blockPortal = world.getBlockState(blockPosBottomPortalBlock.up(i)).getBlock();
//        	if (!(blockPortal instanceof TeleporterPortalBlock)) {
//        		System.out.println("ERROR2! bad value found in findAndSetDestination: " + blockPortal.toString());
//        		return false;
//        	}
//        	
//        	System.out.println("SUCCESS! good value found in findAndSetDestination: " + blockPortal.toString());
//        	((TeleporterPortalBlock)blockPortal).setDestination(blockPosDestination, facingDestination);
//    	}
//		
//		return true;
//	}
	
	
	public static boolean findAndSetTeleporterData(World world, BlockPos blockPosKeystone, TeleporterData teleporterData) {
		
		// We assume this initial blockPos is an Edge Block.
		Block blockKeystone = world.getBlockState(blockPosKeystone).getBlock();
    	if (!(blockKeystone instanceof TeleporterEdgeBlock)) {
    		System.out.println("ERROR! bad value found in findAndSetDestination: " + blockKeystone.toString());
    		return false;
    	}
 
    	int countOfPortalBlocks = TeleporterManager.TELEPORTER_HEIGHT - 2;
    	BlockPos blockPosBottomPortalBlock = blockPosKeystone.up(1);
    	
    	//TeleporterData teleporterData = new TeleporterData();
    	//teleporterData.
    	
    	for (int i = 0; i < countOfPortalBlocks; i++) {
    		
    		Block blockPortal = world.getBlockState(blockPosBottomPortalBlock.up(i)).getBlock();
        	if (!(blockPortal instanceof TeleporterPortalBlock)) {
        		System.out.println("ERROR2! bad value found in findAndSetDestination: " + blockPortal.toString());
        		return false;
        	}
        	
        	System.out.println("SUCCESS! good value found in findAndSetDestination: " + blockPortal.toString());
        	//((TeleporterPortalBlock)blockPortal).setDestination(blockPosDestination, facingDestination);
           	((TeleporterPortalBlock)blockPortal).setTeleporterData(teleporterData); //(blockPosDestination, facingDestination);
    	}
		
		return true;
	}
}
