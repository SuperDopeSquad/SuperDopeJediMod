package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

	
public class TeleporterWrench extends BaseItem {

		
		
	public TeleporterWrench(String unlocalizedName) {
			
	       
		super(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
		
		
	@Override
	public void registerRecipe() {
		
		ItemStack itemStackTeleporterParts = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts);
		ItemStack itemStackThis = new ItemStack(this);
		ItemStack itemStackTeleporterPartsMany = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts, 3);
		
    	GameRegistry.addRecipe(itemStackThis, " x ", " x ", " x ", 'x', itemStackTeleporterParts);	
    	GameRegistry.addRecipe(itemStackTeleporterPartsMany, "x", 'x', itemStackThis);	
	}

	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isTeleporterStarterKit = (blockClicked instanceof TeleporterStarterKit);
       	boolean isTeleporterKit = (blockClicked instanceof TeleporterFinishingKit);
           	
    	System.out.println("DEBUG: inside TeleporterWrench:onItemUse: " + blockClicked.toString() + 
    			" : " + hand.name() + " : " + facing.getName() + " : " + (isTeleporterStarterKit) + " : " + (isTeleporterKit));
    	
    	// If we are on the server, and we are being held in the main hand, and this is actually a droidkit , ...
    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND) && (isTeleporterStarterKit)) {
        	
    		// Destroy the existing block.
    		world.setBlockToAir(blockPos);
    		
    		// Create a starter teleporter.
    		this.createStarterTeleporter(world, blockPos, facing);
    		
    		// Let's drop two teleporter Kits.
    		//TeleporterKit teleporterKit = new TeleporterKit("teleporterKit");
    		ItemStack itemStack = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterFinishingKit);
    		EntityItem entityItem = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);
    		boolean success = world.spawnEntityInWorld(entityItem);
    		System.out.println("did we succeed in dropping a teleporterKit? " + (success));
    		
    		// Return something relevant.
    		return EnumActionResult.SUCCESS;
    	}
   	
    	return EnumActionResult.PASS;
    }
	
	
	private boolean createStarterTeleporter(World world, BlockPos blockPos, EnumFacing facing) {
		
		boolean goNorthAndSouth = true;
		
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			goNorthAndSouth = false;
		}
		else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			goNorthAndSouth = true;
		}
		else {
			System.out.println("bailing on createStarterTeleporter because of bad EnumFacing: " + facing.toString());
			return false;
		}
		
		 /* Make sure the block above is empty, and the user clicked on one of the sides. */
//        BlockPos triggerPos = pos.up();
//        if (!world.isAirBlock(triggerPos))
//        	return false;
        
        if (!(world.isAirBlock(blockPos))) {
        	System.out.println("bailing on createStarterTeleporter because we found a block we didn't expect.");
        	return false;
        }
        
    	IBlockState blockStateTeleporterEdgeBlock = SuperDopeJediMod.teleporterManager.teleporterEdgeBlock.getDefaultState();
    	BlockPos blockPosLeftColumn;
    	BlockPos blockPosRightColumn;
    	
    	if (goNorthAndSouth) {
    		blockPosLeftColumn = blockPos.north();
    		blockPosRightColumn = blockPos.south();
    	}
    	else {
    		blockPosLeftColumn = blockPos.west();
    		blockPosRightColumn = blockPos.east();
    	}
    	
    	
        world.setBlockState(blockPos, blockStateTeleporterEdgeBlock);
        world.setBlockState(blockPosLeftColumn, blockStateTeleporterEdgeBlock);
        world.setBlockState(blockPosRightColumn, blockStateTeleporterEdgeBlock);
        
        
//        public static void buildColumnDestructive(World world, BlockPos startPos, int height, IBlockState bstate) {
//    		// Sanity check the params.
//    		if (height < 0) {
//    			throw new IllegalArgumentException("buildColumnDestructive: need a positive height");
//    		}
//    		
//    		// Lay down the column.
//    		for (int h = 0 ; h < height ; ++h) {
//    			world.setBlockState(startPos.up(h), bstate);
//    		}
//    	}
//        
        
		
		return true;
	}
	
	
}



