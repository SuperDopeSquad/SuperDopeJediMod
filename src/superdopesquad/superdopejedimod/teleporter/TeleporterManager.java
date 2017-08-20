package superdopesquad.superdopejedimod.teleporter;


import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import superdopesquad.superdopejedimod.GeometryUtil;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.EntityManager;
import superdopesquad.superdopejedimod.entity.droid.RepublicBaseDroidEntity;
import superdopesquad.superdopejedimod.faction.ClassCapability;
import superdopesquad.superdopejedimod.faction.ClassCapabilityInterface;
import superdopesquad.superdopejedimod.faction.ClassCapabilityProvider;
import superdopesquad.superdopejedimod.faction.ClassCapabilityStorage;
import superdopesquad.superdopejedimod.faction.PacketPlayerSetClass;


public class TeleporterManager {
	
	public static int TELEPORTER_HEIGHT = 5;

	public static TeleporterParts teleporterParts = new TeleporterParts("teleporterParts");
	public static TeleporterWrench teleporterWrench = new TeleporterWrench("teleporterWrench");
	public static TeleporterStartingKit teleporterStartingKit = new TeleporterStartingKit("teleporterStartingKit");
	public static TeleporterStartingKitItem teleporterStartingKitItem = new TeleporterStartingKitItem("teleporterStartingKitItem");
	public static TeleporterFinishingKit teleporterFinishingKit = new TeleporterFinishingKit("teleporterFinishingKit");
	public static TeleporterFinishingKitItem teleporterFinishingKitItem = new TeleporterFinishingKitItem("teleporterFinishingKitItem");
	public static TeleporterEdgeBlock teleporterEdgeBlock = new TeleporterEdgeBlock("teleporterEdgeBlock");

	  // entities used for teleporters.
    public static TeleporterEntity teleporterEntity = new TeleporterEntity(null);
  
	
	public TeleporterManager() {}
	
	
	public void preInit() {
		
		// Register the 'class' capability.
		CapabilityManager.INSTANCE.register(TeleporterCapabilityInterface.class, new TeleporterCapabilityStorage(), TeleporterCapability.class);	
	}
	
	
	public static boolean createTeleporter(EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, TeleporterSide teleporterSide) {
		
		boolean goNorthAndSouth = true;
		int heightOfPortal = TELEPORTER_HEIGHT;
		
		// Figure out which face they tapped, and bail if it was top or bottom.
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			goNorthAndSouth = false;
		}
		else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			goNorthAndSouth = true;
		}
		else {
			player.sendMessage(new TextComponentString("Try to tap it on one of the sides, not the top or bottom."));
			return false;
		}
		
	  	TeleporterEntity teleporterEntityA = null;
        BlockPos blockPosA = null;
        EnumFacing facingA = null;
    	IBlockState blockStateTeleporterEdgeBlock = SuperDopeJediMod.teleporterManager.teleporterEdgeBlock.getDefaultState();
      	BlockPos blockPosLeftColumn;
    	BlockPos blockPosRightColumn;
    	BlockPos blockPosFinishingKit;
    	BlockPos blockPosMiddleColumnTop = blockPos.up(heightOfPortal - 1);
    	BlockPos blockPosMiddleColumnBottomPortalBlock = blockPos.up(1);
    	Block blockStartingKit = null;
    	Block blockFinishingKit = null;
    	
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
        	
        	player.sendMessage(new TextComponentString("Not enough space to build a teleporter here.")); 
        	return false;	
        }
             
        // Any work we have to do to extract information out of the starting and finishing kits before we destroy them?
        if (teleporterSide == TeleporterSide.SIDE_A) {

           	// We are making this assumption right now.  Check it!
        	blockStartingKit = world.getBlockState(blockPos).getBlock();
            if (!(blockStartingKit instanceof TeleporterStartingKit) ){
             	System.out.println("WTF! block i just hit is not TeleporterStartingKit: " + blockStartingKit.toString());        	
             	return false;
            }
        }
        
        else { // This is Teleporter B!
        	
        	// We are making this assumption right now.  Check it!
        	blockFinishingKit = world.getBlockState(blockPos).getBlock();
            if (!(blockFinishingKit instanceof TeleporterFinishingKit) ){
             	System.out.println("WTF! block i just hit is not TeleporterFinishingKit: " + blockFinishingKit.toString());        	
             	return false;
            }
            
            // Let's extract the meta data that is stored in the Finishing Kit.
          	teleporterEntityA = ((TeleporterFinishingKit)blockFinishingKit).getEntityForTeleporterA();
            blockPosA = ((TeleporterFinishingKit)blockFinishingKit).getBlockPosForTeleporterA();
            facingA = ((TeleporterFinishingKit)blockFinishingKit).getFacingForTeleporterA();
        }
          
		// Destroy the existing block, which we already asserted was a starting kit or finishing kit.
		world.setBlockToAir(blockPos); 
        	  
		// Create the columns on the left and right.
    	GeometryUtil.buildColumnDestructive(world, blockPosLeftColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	GeometryUtil.buildColumnDestructive(world, blockPosRightColumn, heightOfPortal, blockStateTeleporterEdgeBlock);
     	    	
     	// Create the middle top and bottom block.
        world.setBlockState(blockPos, blockStateTeleporterEdgeBlock);
        world.setBlockState(blockPosMiddleColumnTop, blockStateTeleporterEdgeBlock);
        
 		// Create the new teleporter entity.
		TeleporterEntity entity = (TeleporterEntity) EntityManager.createEntity(TeleporterEntity.class, world, blockPosMiddleColumnBottomPortalBlock);

		// OK, any finish up work that either side has to do, like drop anything?
        if (teleporterSide == TeleporterSide.SIDE_A) {
        
        	// Create the finishing kit and drop it on the ground.
        	ItemStack itemStack = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterFinishingKitItem);
        	((TeleporterFinishingKitItem)itemStack.getItem()).setTeleporterData(facing, blockPos, entity);
            EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);		
        	//boolean success = world.spawnEntityInWorld(entityItem);
        	boolean success = world.spawnEntity(entityItem);

        }
        
        else { // We are in Teleporter B!
        	
        	// We need to get a handle to TeleporterA, and set it's teleporter data towards where Teleporter B is.
            BlockPos blockPosB = blockPos;
            EnumFacing facingB = facing;
            
            // DEBUG TESTING ONLY.  In many cases, like in creative mode, you can create a FinishingKit that doesn't
            // have the data we need in it to point to teleporterA.  Non-critical, but helpful for debugging.
            if (blockPosA != null) {
            	System.out.println("DEBUG: blockPosA: " + blockPosA.toString());
            }
            else {
            	System.out.println("ERROR: blockPosA is NULL when trying to create portal B.");
        	}
            if (facingA != null) {
            	System.out.println("DEBUG: facingA: " + facingA.toString());
            }
            else {
            	System.out.println("ERROR: facingA is NULL when trying to create portal B.");
        	}
	      	
        	// grab a handle to EntityA, and set it's teleporter data correctly.
        	if (teleporterEntityA == null) {
        		System.out.println("ERROR! handle to the first teleporter is null, can't set it's destination");
        	}
        	else {
        		//System.out.println("About to set EntityA's teleporterData from:" + teleporterEntityA.getTeleporterDestination().toString() + " to : " + blockPosB.toString());
        		teleporterEntityA.setTeleporterDestination(TeleporterManager.adjustBlockPos(blockPosB, facingB, 3));
        	}
         	
        	// grab a handle to EntityB, and set it's teleporter data correctly.
        	TeleporterEntity teleporterEntityB = entity;
        	if (teleporterEntityB == null) {
        		System.out.println("ERROR setting EntityB's teleporterData because teleporterEntityB is null.");
        	}
        	else if ((blockPosA == null) || (facingA == null)) {
        		System.out.println("ERROR setting blockPosA or facingA to EntityB because one of them is null." );
        	}
        	else {
         		System.out.println("About to set EntityB's teleporterData from:" + teleporterEntityB.getTeleporterDestination().toString() + " to : " + blockPosA.toString());
        		teleporterEntityB.setTeleporterDestination(TeleporterManager.adjustBlockPos(blockPosA, facingA, 3));
        	}
        }
                
		return true;
	}
	
	
	public static BlockPos adjustBlockPos(BlockPos blockPosOriginal, EnumFacing facing, int gapDistance) {
		
		if (facing == EnumFacing.NORTH) {
			return blockPosOriginal.north(gapDistance);
		}
		else if (facing == EnumFacing.SOUTH) {
			return blockPosOriginal.south(gapDistance);
		}
		else if (facing == EnumFacing.WEST) {
			return blockPosOriginal.west(gapDistance);
		}
		else if (facing == EnumFacing.EAST) {
			return blockPosOriginal.east(gapDistance);
		}
		else {
			System.out.println("unexpected value found in TeleporterManager.adjustBlockPos");
			return blockPosOriginal.north(gapDistance);
		}
	}
	
	
	public static boolean teleportSomeoneSomewhere(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
		     	
		// Check to make sure this blockPos is clear.
		if (blockPos == null) {
			System.out.println("ERROR: Sent a null blockpos in a PacketTeleporterSetDestination.");
			return false;		
		}
		
		// We are cool with these type of blocks.
		boolean isAirBlock = world.isAirBlock(blockPos);
		boolean isPassableBlock = world.getBlockState(blockPos).getBlock().isPassable(world, blockPos);		
		if (!(isAirBlock || isPassableBlock)) {
			entityPlayer.sendMessage(new TextComponentString("Your destination is blocked!  Teleportation suspended.")); 
		    return false;
		}
		
		// Move the current player.
		double x = blockPos.getX() + 0.5;
		double y = blockPos.getY();
		double z = blockPos.getZ() + 0.5;
 		entityPlayer.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
 		
 		return true;
	}
	
}
