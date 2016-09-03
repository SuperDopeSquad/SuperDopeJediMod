package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
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
	static IBlockState MATERIAL_STONE = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
	static IBlockState MATERIAL_CLAY = Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
	static IBlockState MATERIAL_CORNERS = Blocks.RED_SANDSTONE.getDefaultState();
	static IBlockState MATERIAL_AIR = Blocks.AIR.getDefaultState();
	static IBlockState MATERIAL_DOOR = Blocks.DARK_OAK_DOOR.getDefaultState();
	static IBlockState MATERIAL_CARPET = Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.RED);
	static IBlockState MATERIAL_CHEST = Blocks.CHEST.getDefaultState();
	static IBlockState MATERIAL_FENCE = Blocks.NETHER_BRICK_FENCE.getDefaultState();
	static IBlockState MATERIAL_STAIRS = Blocks.NETHER_BRICK_STAIRS.getDefaultState();
	static IBlockState MATERIAL_STAIRLANDING = Blocks.WOODEN_SLAB.getDefaultState();
	    
	/*
	 * 
	 */
	public SithMark(String localName) 
	{
		super(Material.iron, localName);
		this.setLightLevel(1.0F);
	}
	
	
	/*
	 * 
	 */
	static protected IBlockState GetWallBrickState(int level, int i, int maxLevel) {
		
		IBlockState bstate;
		if (level == 0) {
			// base level
			bstate = MATERIAL_STONE;
		} else if (level == (maxLevel - 1)) {
			bstate = MATERIAL_CLAY;
		} else if (i == 0) {
			// corners
			bstate = MATERIAL_CORNERS;
		} else {
			// normal surface
			bstate = MATERIAL_CLAY;
		}
		return bstate;
	}
	
	
	/*
	 * 
	 */
	protected void PlaceColumn(World worldIn, BlockPos pos, IBlockState bstate1, IBlockState bstate2) {
		for (int level = 0 ; level < 5 ; ++level) {
			worldIn.setBlockState(pos, (level == 0) ? bstate1 : bstate2);
			pos = pos.up();
		}
		worldIn.setBlockState(pos, Blocks.TORCH.getDefaultState());
	}
	
	
	/*
	 *  Minecraft Event Handler, when someone clicks on the block.
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
        
        /* add the pillar up the middle. */
		BlockPos centerPos = pos;
		for (int i = 0 ; i < 12 ; ++i) {
			worldIn.setBlockState(centerPos, MATERIAL_STONE);
			centerPos = centerPos.up();
		}
		
		/* build the wall around it. */
		int maxLevel = 12;
		BlockPos wallPos = pos.south(2).west(2);
		for (int level = 0; level < maxLevel; ++level) {
			for (int i = 0; i < 4; ++i) {
				IBlockState bstate = GetWallBrickState(level, i, maxLevel);
				worldIn.setBlockState(wallPos, bstate);
				wallPos = wallPos.north();
			}
			for (int i = 0; i < 4; ++i) {
				IBlockState bstate = GetWallBrickState(level, i, maxLevel);
				worldIn.setBlockState(wallPos, bstate);
				wallPos = wallPos.east();
			}
			for (int i = 0; i < 4; ++i) {
				IBlockState bstate = GetWallBrickState(level, i, maxLevel);
				worldIn.setBlockState(wallPos, bstate);
				wallPos = wallPos.south();
			}
			for (int i = 0; i < 4; ++i) {
				IBlockState bstate = GetWallBrickState(level, i, maxLevel);
				worldIn.setBlockState(wallPos, bstate);
				wallPos = wallPos.west();
			}
			wallPos = wallPos.up();
		}
		
		/* place the torches up the pillar, so you can see where u are going up the stairs. */
		{
			BlockPos torchPos = pos.up(3).west();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
			torchPos = pos.up(4).north();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
			torchPos = pos.up(5).east();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
			torchPos = pos.up(6).south();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
			torchPos = pos.up(7).west();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
			torchPos = pos.up(8).north();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
			torchPos = pos.up(9).east();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
			torchPos = pos.up(10).south();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
			torchPos = pos.up(11).west();
			worldIn.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
		}
		
		/* Now build the ledge on the top of the tower. */
		BlockPos ledgePos = wallPos.down().north().east();
		{
			/* special case: extra tile by stairs. */
			worldIn.setBlockState(ledgePos, MATERIAL_CLAY);
					
			ledgePos = ledgePos.south().west(2);
			for (int j = 0; j < 5; ++j) {
				worldIn.setBlockState(ledgePos, MATERIAL_CLAY);
				worldIn.setBlockState(ledgePos.west(), MATERIAL_CLAY);
				ledgePos = ledgePos.north();
			}
			ledgePos = ledgePos.east();
			for (int j = 0; j < 5; ++j) {
				worldIn.setBlockState(ledgePos, MATERIAL_CLAY);
				worldIn.setBlockState(ledgePos.north(), MATERIAL_CLAY);
				ledgePos = ledgePos.east();
			}
			ledgePos = ledgePos.south();
			for (int j = 0; j < 5; ++j) {
				worldIn.setBlockState(ledgePos, MATERIAL_CLAY);
				worldIn.setBlockState(ledgePos.east(), MATERIAL_CLAY);
				ledgePos = ledgePos.south();
			}
			ledgePos = ledgePos.west();
			for (int j = 0; j < 5; ++j) {
				worldIn.setBlockState(ledgePos, MATERIAL_CLAY);
				worldIn.setBlockState(ledgePos.south(), MATERIAL_CLAY);
				ledgePos = ledgePos.west();
			}
		}
		
		/* put in the rafters. */
		{
			BlockPos raftPos = ledgePos.down();
			raftPos = raftPos.north(3);
			worldIn.setBlockState(raftPos, MATERIAL_STONE);
			worldIn.setBlockState(raftPos.west(), MATERIAL_STONE);
			raftPos = raftPos.north(3).east(3);
			worldIn.setBlockState(raftPos, MATERIAL_STONE);
			worldIn.setBlockState(raftPos.north(), MATERIAL_STONE);
			raftPos = raftPos.east(3).south(3);
			worldIn.setBlockState(raftPos, MATERIAL_STONE);
			worldIn.setBlockState(raftPos.east(), MATERIAL_STONE);
			raftPos = raftPos.south(3).west(3);
			worldIn.setBlockState(raftPos, MATERIAL_STONE);
			worldIn.setBlockState(raftPos.south(), MATERIAL_STONE);
		}
		
		/* put in the poles on the roof. */
		{
			BlockPos polePos = ledgePos.down();
			PlaceColumn(worldIn, polePos, MATERIAL_STONE, MATERIAL_CORNERS);
			polePos = polePos.north(6);
			PlaceColumn(worldIn, polePos, MATERIAL_STONE, MATERIAL_CORNERS);
			polePos = polePos.east(6);
			PlaceColumn(worldIn, polePos, MATERIAL_STONE, MATERIAL_CORNERS);
			polePos = polePos.south(6);
			PlaceColumn(worldIn, polePos, MATERIAL_STONE, MATERIAL_CORNERS);
			polePos = polePos.west(6);
		}
		
		/* put in the fences. */
		{		
			BlockPos fencePos = ledgePos.up().north();
			worldIn.setBlockState(fencePos, MATERIAL_FENCE);
			fencePos = fencePos.west();
			for (int i = 0 ; i < 5; ++i) {
				worldIn.setBlockState(fencePos, MATERIAL_FENCE);
				fencePos = fencePos.north();
			}
			worldIn.setBlockState(fencePos.south().east(), MATERIAL_FENCE);
			
			fencePos = fencePos.east(2);
			worldIn.setBlockState(fencePos, MATERIAL_FENCE);
			fencePos = fencePos.north();
			for (int i = 0 ; i < 5; ++i) {
				worldIn.setBlockState(fencePos, MATERIAL_FENCE);
				fencePos = fencePos.east();
			}
			worldIn.setBlockState(fencePos.west().south(), MATERIAL_FENCE);
			
			fencePos = fencePos.south(2);
			worldIn.setBlockState(fencePos, MATERIAL_FENCE);
			fencePos = fencePos.east();
			for (int i = 0 ; i < 5; ++i) {
				worldIn.setBlockState(fencePos, MATERIAL_FENCE);
				fencePos = fencePos.south();
			}
			worldIn.setBlockState(fencePos.north().west(), MATERIAL_FENCE);
			
			fencePos = fencePos.west(2);
			worldIn.setBlockState(fencePos, MATERIAL_FENCE);
			fencePos = fencePos.south();
			for (int i = 0 ; i < 5; ++i) {
				worldIn.setBlockState(fencePos, MATERIAL_FENCE);
				fencePos = fencePos.west();
			}
			worldIn.setBlockState(fencePos.north().east(), MATERIAL_FENCE);
		}
		
		
		/* carve out the door. */
		BlockPos doorPos = pos.east(2).south();
		{
			/* border around door. */
			BlockPos borderPos = doorPos.south();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.up();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.up();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.north();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.north();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.down();
			worldIn.setBlockState(borderPos, MATERIAL_STONE);
			
			/* Now the door itself. */
			worldIn.setBlockState(doorPos, MATERIAL_AIR);
			worldIn.setBlockState(doorPos.up(), MATERIAL_AIR);
			worldIn.setBlockState(doorPos, MATERIAL_DOOR
					.withProperty(BlockDoor.FACING, EnumFacing.EAST)
					.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
					.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER));
			worldIn.setBlockState(doorPos.up(), MATERIAL_DOOR
					.withProperty(BlockDoor.FACING, EnumFacing.EAST)
					.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
					.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));
			
			/* carpet on inside of door. */
			worldIn.setBlockState(doorPos.west(), MATERIAL_CARPET);
			worldIn.setBlockState(doorPos.west(2), MATERIAL_CARPET);
			worldIn.setBlockState(doorPos.west().north(), MATERIAL_CARPET);
			
			/* chest in closet. */
			worldIn.setBlockState(doorPos.west().north(2), MATERIAL_CHEST.withProperty(BlockChest.FACING, EnumFacing.SOUTH));
		}
		
        return true;
    }
	
	/*
	 * 
	 */
	protected BlockPos Stairs_BuildOneFloor(World worldIn, BlockPos startPos) {
		/* two spirals will bring us eight blocks up. */
		BlockPos nextPos = startPos;
		for (int i = 0 ; i < 3 ; ++i) {
			nextPos = Stairs_BuildOneSpiral(worldIn, nextPos);
		}
		return nextPos;
	}
	
	/*
	 * 
	 */
	protected BlockPos Stairs_BuildOneSpiral(World worldIn, BlockPos startPos) {
		
		/* one spiral up will bring up 4 blocks up. */
		BlockPos nextPos = startPos;
		for (int i = 0 ; i < 4 ; ++i) {
			{
				worldIn.setBlockState(nextPos, MATERIAL_STAIRLANDING);
			}
			nextPos = Stairs_IncrementPos(nextPos, i);
			
			{
				IBlockState state = MATERIAL_STAIRS.withProperty(BlockStairs.FACING, Stairs_GetCurrentFacing(i));
				worldIn.setBlockState(nextPos, state);
			}
			nextPos = Stairs_IncrementPos(nextPos, i).up();
		}
		return nextPos;
	}
	
	
	/*
	 * 
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
	 * 
	 */
	protected EnumFacing Stairs_GetCurrentFacing(int state) {
		switch (state) {
		case 0:
			return EnumFacing.NORTH;
		case 1:
			return EnumFacing.EAST;
		case 2:
			return EnumFacing.SOUTH;
		case 3:
			return EnumFacing.WEST;
		default:
			throw new IllegalStateException();
    	}
	}
}
