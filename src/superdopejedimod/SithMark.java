package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/*
*/
public class SithMark extends BaseBlock 
{
	/*
	*/
	public SithMark(String localName) 
	{
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#onBlockActivated(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand, net.minecraft.item.ItemStack, net.minecraft.util.EnumFacing, float, float, float)
	 */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		System.out.println("SithMark: activated, remote=" + worldIn.isRemote + ", pos=" + pos);
		
		/* Don't do anything if we are the client. */
        if (worldIn.isRemote)
            return true;
        
        /* Only by clicking on the sides do we blow up. */
        if ((side == EnumFacing.UP) || (side == EnumFacing.DOWN))
        	return false;
        
        /* Make sure the block above is empty, and the user clicked on one of the sides. */
        BlockPos triggerPos = pos.up();
        if (!worldIn.isAirBlock(triggerPos))
        	return false;
        
        /* Create our new block directly above the clicked block. */
        BlockPos startPos = pos.south().west();
        System.out.println("SithMark: creating extension, pos=" + startPos);
        BlockPos nextPos = startPos;
        nextPos = Stairs_BuildOneFloor(worldIn, nextPos);
        
        /* add the birch pillar up the middle. */
		BlockPos centerPos = pos;
		for (int i = 0 ; i < 8 ; ++i) {
			IBlockState bstate = Blocks.LOG.getDefaultState();
			//state.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH);
			worldIn.setBlockState(centerPos, bstate);
			centerPos = centerPos.up();
		}
        return true;
    }
	
	/*
	 */
	protected BlockPos Stairs_BuildOneFloor(World worldIn, BlockPos startPos) {
		/* two spirals will bring us eight blocks up. */
		BlockPos nextPos = startPos;
		for (int i = 0 ; i < 2 ; ++i) {
			nextPos = Stairs_BuildOneSpiral(worldIn, nextPos);
		}
		return nextPos;
	}
	
	/*
	 */
	protected BlockPos Stairs_BuildOneSpiral(World worldIn, BlockPos startPos) {
		
		/* one spiral up will bring up 4 blocks up. */
		BlockPos nextPos = startPos;
		for (int i = 0 ; i < 4 ; ++i) {
			{
				IBlockState state = Blocks.WOODEN_SLAB.getDefaultState();
				worldIn.setBlockState(nextPos, state);
			}
			nextPos = Stairs_IncrementPos(nextPos, i);
			
			{
				IBlockState state = Blocks.BIRCH_STAIRS.getDefaultState();
				state = state.withProperty(BlockStairs.FACING, Stairs_GetCurrentFacing(i));
				worldIn.setBlockState(nextPos, state);
			}
			nextPos = Stairs_IncrementPos(nextPos, i).up();
		}
		return nextPos;
	}
	
	/*
	 */
	protected BlockPos Stairs_IncrementPos(BlockPos pos, int state) {
		switch (state) {
		case 0:
			return pos.north();
		case 1:
			return pos.east();
		case 2:
			return pos.south();
		case 3:
			return pos.west();
		default:
			throw new IllegalStateException();
    	}
	}
	
	/*
	 */
	protected EnumFacing Stairs_GetCurrentFacing(int state) {
		switch (state) {
		case 0:
			System.out.println("SithMark: stairs are going north");
			return EnumFacing.NORTH;
		case 1:
			System.out.println("SithMark: stairs are going east");
			return EnumFacing.EAST;
		case 2:
			System.out.println("SithMark: stairs are going south");
			return EnumFacing.SOUTH;
		case 3:
			System.out.println("SithMark: stairs are going west");
			return EnumFacing.WEST;
		default:
			throw new IllegalStateException();
    	}
	}
}
