package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/*
 * 		This block summons in a dome made of iron with a iron door ad some buttons. a work in progress. PEACE OUT HOMIES!
*/
public class OHUMBlock extends BaseBlock 
{
	/* "Blocks" is the database of all blocks that are in the default Minecraft block collection. Here we pick out the one we want to use for
	 * our column of blocks. */
	protected static IBlockState DOME_BLOCK = Blocks.IRON_BLOCK.getDefaultState();
	protected static IBlockState MATERIAL_AIR = Blocks.AIR.getDefaultState();
	protected static IBlockState MATERIAL_DOOR = Blocks.IRON_DOOR.getDefaultState();
	protected static IBlockState MATERIAL_BUTTON = Blocks.STONE_BUTTON.getDefaultState();
	
	/*
	 * This is the special constructor function that is called on game startup when the first "JediMark" object is created.
	*/
	public OHUMBlock(String localName) 
	{
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	/* computes the distance between two three-dimensional points. */
	protected int distance(BlockPos pos1, BlockPos pos2) {
		double d0 = (double)(pos1.getX() - pos2.getX());
	    double d1 = (double)(pos1.getY() - pos2.getY());
	    double d2 = (double)(pos1.getZ() - pos2.getZ());
	    return (int) Math.round(Math.sqrt((d0 * d0) + (d1 * d1) + (d2 * d2)));
	}
	

	/*
	 * This is not the most efficient algorithm, but it works. I iterate over every block in the 
	 *  [radius x radius x radius ] cube, and then calculate the distance from the center of that block
	 *  to pos. If it equals the radius (after rounding), then that block is on the border of the sphere,
	 *  and should be turned "on".
	 * 
	 */
	protected void PlaceDome(World worldIn, BlockPos pos, int radius, IBlockState block) {
	
		for (int x = -radius ; x <= radius ; ++x) {
			for (int z = -radius ; z <= radius ; ++z) {
				for (int y = 0 ; y <= radius ; ++y) {
					BlockPos currpos = pos.add(x, y, z);
					int d = distance(currpos, pos);
					if (d == radius) {
						worldIn.setBlockState(currpos, block);
					}
				}
			}
		}
	}
	
	
	/*
	 * 
	 */
	public void buildDomeFort(World worldIn, BlockPos pos, EnumFacing side) {
		/* Build central dome. */
		PlaceDome(worldIn, pos, 7, DOME_BLOCK);
		
		/* Build the door. */
		BlockPos doorPos = pos.offset(side, 7);
		worldIn.setBlockState(doorPos, MATERIAL_AIR);
		worldIn.setBlockState(doorPos.up(), MATERIAL_AIR);
		
		/* Now the door itself. */
		//doorPos.offset(side, 1);
		worldIn.setBlockState(doorPos, MATERIAL_DOOR
				.withProperty(BlockDoor.FACING, side)
				.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER));
		worldIn.setBlockState(doorPos.up(), MATERIAL_DOOR
				.withProperty(BlockDoor.FACING, side)
				.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));
		
		/* Add the buttons. */
		BlockPos buttonPos = doorPos.up().offset(side, 1).offset(side.rotateYCCW(), 1);
		worldIn.setBlockState(buttonPos, MATERIAL_BUTTON.withProperty(BlockDirectional.FACING, side));
		buttonPos = doorPos.up().offset(side.getOpposite(), 1).offset(side.rotateY(), 1);
		worldIn.setBlockState(buttonPos, MATERIAL_BUTTON.withProperty(BlockDirectional.FACING, side.getOpposite()));
		
		/* build the tunnel to the basement. */
		BlockPos tunnelPos = pos.down(1).north(5).west(4);
		worldIn.setBlockState(tunnelPos, MATERIAL_AIR);
		for (int i=0 ; i < 5 ; ++i) {
			tunnelPos = tunnelPos.down(1);
			worldIn.setBlockState(tunnelPos, MATERIAL_AIR);
		}
		
		////bUilD BaSEmENt!!!!!
		BlockPos basementPos = tunnelPos;
		ClearPrism(worldIn, basementPos, 10, 4, 3);
		
	}
	
	
	public void SetBlockToAir(World world, BlockPos pos) {
		world.setBlockState(pos, MATERIAL_AIR);
	}
	
	public void ClearRow(World world, BlockPos pos, int length) {
		for (int i = 0; i < length ; i++ ) {
			SetBlockToAir(world, pos);
			pos = pos.south(1);	
		}
	}
	
	
	public void ClearPlain(World world, BlockPos pos, int length, int width) {
		for (int i = 0; i < width ; i++ ) {
			ClearRow(world, pos, length);
			pos = pos.west(1);	
		}
	}
	
	
	public void ClearPrism(World world, BlockPos pos, int length, int width, int height) {
		for (int i = 0; i < height ; i++ ) {
			ClearPlain(world, pos, length, width);
			pos = pos.up(1);	
		}
	}
	
	
	
	
	/*
	 * This function is automatically called by Minecraft whenever anybody right-mouse-clicks on a JediMark block.
    */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		/* Don't do anything if we are the client. 
		 * SuperDopeSquad: ignore this for now. Know that if you do any block-creation, then you need this at the beginning of your function. */
        if (worldIn.isRemote)
            return true;
        
        System.out.println("OHUMBlock: activated, remote=" + worldIn.isRemote + ", pos=" + pos);
        
        if ((side == EnumFacing.NORTH) ||
        	(side == EnumFacing.SOUTH) ||
        	(side == EnumFacing.WEST) ||
        	(side == EnumFacing.EAST)){
            buildDomeFort(worldIn, pos, side);
            return true;
        }
        
       /* Add other stuff here: like hitting a side, etc. */
        
        /* We are now done. We leave this function by calling "return", this time with "true", to tell minecraft that we actually did something. */
        return true;
    }
}