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
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


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
	
	protected static int HEIGHT_SPIRALS = 6;
	protected static int HEIGHT_BLOCKS = HEIGHT_SPIRALS * 4;
	
	    
	/*
	 * 
	 */
	public SithMark(String localName) {
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	
	/*
	 *
	 */
	@Override
	public void registerRecipe() {
		ItemStack swordStack = new ItemStack(Items.IRON_SWORD);
    	ItemStack goldStack = new ItemStack(Items.GOLD_INGOT);
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "x x", " y ", "x x", 'x', swordStack, 'y', goldStack);
	}
	
	
	/*
	 *  Minecraft Event Handler, when someone clicks on the block.
	 */
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, 
									EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		System.out.println("SithMark: activated, remote=" + world.isRemote + ", pos=" + pos);
		
		/* Don't do anything if we are the client. */
        if (world.isRemote)
            return true;
        
        /* Only by clicking on the sides do we blow up. */
        if ((side == EnumFacing.UP) || (side == EnumFacing.DOWN))
        	return false;
        
        /* Make sure the block above is empty, and the user clicked on one of the sides. */
        BlockPos triggerPos = pos.up();
        if (!world.isAirBlock(triggerPos))
        	return false;
        
        int wallLength = -1;
        
        /* If the block underneath is is a stone we may be building an entire castle. */
        IBlockState underState = world.getBlockState(pos.down());
        if (underState.getBlock() instanceof net.minecraft.block.BlockStone) {
        	System.out.println("SithMark: FOUND STONE");
        	
        	/* OK, now scan in one direction, looking for the boundary marker. */
        	int MAX_SCAN = 300;
        	for (int i = 9 ; i < MAX_SCAN ; ++i) {
        		BlockPos scanPos = pos.offset(side.rotateYCCW(), i);
        		if (world.getBlockState(scanPos).getBlock() instanceof SithMark) {
        			wallLength = i - 9;
        			System.out.println("SithMark: FOUND STONE-END, len=" + wallLength);	
        		}
        	}
        }
       
        /* default: just build a single tower. */
        if (wallLength >= 0) {
        	buildCastle(world, pos, side, wallLength);
        } else {
        	buildTower(world, pos, side);
        }
        return true;
    }
	
	
	/*
	 * Everything is oriented from the side passed in; the door will be facing that side.
	 */
	protected void buildCastle(World world, BlockPos pos, EnumFacing side, int wallLength) {
		
		for (int i = 0 ; i < 4; ++i) {
			/* Build the tower-corner. */
			buildTower(world, pos, side);
			buildTower(world, pos.offset(side, 5).offset(side.rotateY(), 5), side.rotateYCCW());
			
			/* Build the wall that connects them. */
			if (wallLength > 0) {
				buildWall(world, pos.offset(side.rotateYCCW(), 5), wallLength, (HEIGHT_BLOCKS - 4), side.rotateYCCW(), MATERIAL_CORNERS, false);
			}
			
			pos = pos.offset(side.rotateYCCW(), wallLength + 14).offset(side, 5);
			side = side.rotateY();
		}
	}
	
	
	/*
	 * Everything is oriented from the side passed in; the door will be facing that side.
	 */
	protected void buildTower(World world, BlockPos pos, EnumFacing side) {
        
		System.out.println("SithMark: creating tower, pos=" + pos + ", side=" + side);
		 
		 /* Build the pillar up the middle. We go one extra to make it look cool. */
        GeometryUtil.buildColumnDestructive(world, pos, HEIGHT_BLOCKS + 1, MATERIAL_CLAY);
        
        /* Build spiral staircase. */
        BlockPos stairPos = pos.offset(side.getOpposite(), 1).offset(side.rotateY(), 1);
        buildSpiralStaircase(world, stairPos, HEIGHT_SPIRALS, side.rotateYCCW());
        
		/* Build the wall around it. */
        BlockPos wallPos = pos.south(2).west(2);
        EnumFacing wallSide = EnumFacing.NORTH;
        for (int i = 0 ; i < 4 ; ++i) {
	        buildWall(world, wallPos, 1, HEIGHT_BLOCKS, wallSide, MATERIAL_CORNERS, false);
	        buildWall(world, wallPos.offset(wallSide, 1), 3, HEIGHT_BLOCKS, wallSide, MATERIAL_CLAY, false);
	        buildWall(world, wallPos.offset(wallSide, 1).offset(wallSide.rotateYCCW(), 1), 3, HEIGHT_BLOCKS, wallSide, MATERIAL_CLAY, false);
	        buildWall(world, wallPos.offset(wallSide, 2).offset(wallSide.rotateYCCW(), 2), 1, HEIGHT_BLOCKS, wallSide, MATERIAL_CORNERS, false);
	        
	        wallPos = wallPos.offset(wallSide, 4);
	        wallSide = wallSide.rotateY();
        }
        
        /* carve out the door. */
		BlockPos doorPos = pos.offset(side, 2).offset(side.rotateY(), 1);
		{
			/* Erase some of the wall that is in our way. */
			buildWall(world, doorPos.offset(side, 1), 2, 3, side.rotateYCCW(), MATERIAL_AIR, true);
			buildWall(world, doorPos.offset(side, 2).offset(side.rotateYCCW(), 1), 1, 4, side.rotateYCCW(), MATERIAL_AIR, true);
			
			/* border around door. */
			BlockPos borderPos = doorPos.offset(side.rotateY(), 1);
			world.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.up();
			world.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.up();
			world.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.offset(side.rotateYCCW(), 1);
			world.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.offset(side.rotateYCCW(), 1);
			world.setBlockState(borderPos, MATERIAL_STONE);
			borderPos = borderPos.down();
			world.setBlockState(borderPos, MATERIAL_STONE);
			
			/* Now the door itself. */
			world.setBlockState(doorPos, MATERIAL_AIR);
			world.setBlockState(doorPos.up(), MATERIAL_AIR);
			world.setBlockState(doorPos, MATERIAL_DOOR
					.withProperty(BlockDoor.FACING, side)
					.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
					.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER));
			world.setBlockState(doorPos.up(), MATERIAL_DOOR
					.withProperty(BlockDoor.FACING, side)
					.withProperty(BlockDoor.OPEN, Boolean.valueOf(false))
					.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));
			
			/* carpet on inside of door. */
			world.setBlockState(doorPos.offset(side.getOpposite()), MATERIAL_CARPET);
			world.setBlockState(doorPos.offset(side.getOpposite(), 2), MATERIAL_CARPET);
			world.setBlockState(doorPos.offset(side.getOpposite()).offset(side.rotateYCCW(), 1), MATERIAL_CARPET);
			
			/* chest in closet. */
			world.setBlockState(doorPos.offset(side.getOpposite()).offset(side.rotateYCCW(), 2), 
					MATERIAL_CHEST.withProperty(BlockChest.FACING, side.rotateY()));
		}
		
		/* place the torches up the pillar, so you can see where u are going up the stairs. */
		{
			EnumFacing torchSide = side.rotateY();
			for (int h = 2 ; h < HEIGHT_BLOCKS ; ++h) {
				BlockPos torchPos = pos.up(h).offset(torchSide, 1);
				world.setBlockState(torchPos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, torchSide));
				torchSide = torchSide.rotateY();
			}
		}
		
		/* Now build the ledge on the top of the tower. */
		{
			EnumFacing ledgeSide = side.rotateY();
			BlockPos ledgePos = pos.up(HEIGHT_BLOCKS - 1).offset(side, 2).offset(side.rotateY(), 2);
			for (int i = 0 ; i < 4 ; ++i) {
				EnumFacing ledgeCurrSide = ledgeSide;
				BlockPos ledgeCurrPos = ledgePos;
				
				for (int j = 0 ; j < 2 ; ++j) {
					ledgeCurrPos = ledgeCurrPos.offset(ledgeCurrSide, 1);
					world.setBlockState(ledgeCurrPos, MATERIAL_CORNERS);
				}
				ledgeCurrSide = ledgeCurrSide.rotateY();
				for (int j = 0 ; j < 4 ; ++j) {
					ledgeCurrPos = ledgeCurrPos.offset(ledgeCurrSide, 1);
					world.setBlockState(ledgeCurrPos, MATERIAL_CORNERS);
				}
				ledgeCurrSide = ledgeCurrSide.rotateY();
				for (int j = 0 ; j < 2 ; ++j) {
					ledgeCurrPos = ledgeCurrPos.offset(ledgeCurrSide, 1);
					/* special case: mark that last corner as clay, as it looks cooler in ledge. */
					world.setBlockState(ledgeCurrPos, (j == 0) ? MATERIAL_CORNERS : MATERIAL_CLAY);
				}
				
				ledgeSide = ledgeSide.rotateY();
				ledgePos = ledgePos.offset(ledgeSide, 4);
			}
			
			/* special case: one extra tile at end of steps. */
			ledgePos = pos.up(HEIGHT_BLOCKS - 1).offset(side.getOpposite(), 1).offset(side.rotateY(), 1);
			world.setBlockState(ledgePos, MATERIAL_CLAY);
		}
				
		/* put in the poles on the roof. */
		EnumFacing poleSide = side.rotateY();
		for (int i = 0 ; i < 4 ; ++i) {
			BlockPos polePos = pos.up(HEIGHT_BLOCKS - 1).offset(poleSide, 3).offset(poleSide.rotateY(), 3);
			GeometryUtil.buildColumnDestructive(world, polePos, 3, MATERIAL_CORNERS);
			world.setBlockState(polePos.up(3), Blocks.TORCH.getDefaultState());
			poleSide = poleSide.rotateY();
		}
		
		/* Now build the fence on the top of the tower. */
		EnumFacing fenceSide = side.rotateY();
		BlockPos fencePos = pos.up(HEIGHT_BLOCKS).offset(side, 2).offset(side.rotateY(), 2);
		for (int i = 0 ; i < 4 ; ++i) {
			EnumFacing fenceCurrSide = fenceSide;
			BlockPos fenceCurrPos = fencePos;
			
			for (int j = 0 ; j < 2 ; ++j) {
				fenceCurrPos = fenceCurrPos.offset(fenceCurrSide, 1);
				world.setBlockState(fenceCurrPos, MATERIAL_FENCE);
			}
			fenceCurrSide = fenceCurrSide.rotateY();
			for (int j = 0 ; j < 4 ; ++j) {
				fenceCurrPos = fenceCurrPos.offset(fenceCurrSide, 1);
				world.setBlockState(fenceCurrPos, MATERIAL_FENCE);
			}
			fenceCurrSide = fenceCurrSide.rotateY();
			for (int j = 0 ; j < 1 ; ++j) {
				fenceCurrPos = fenceCurrPos.offset(fenceCurrSide, 1);
				world.setBlockState(fenceCurrPos, MATERIAL_FENCE);
			}
			
			fenceSide = fenceSide.rotateY();
			fencePos = fencePos.offset(fenceSide, 4);
		}
    }
	
	/**
	 * 
	 * @param world
	 * @param pos
	 * @param length
	 * @param height
	 * @param side
	 * @param state
	 * @param force
	 */
	protected static void buildWall(World world, BlockPos pos, int length, int height, EnumFacing side, IBlockState state, boolean force) {
		for (int l = 0; l < length; ++l) {
			for (int h = 0 ; h < height ; ++h) {
				IBlockState currState = ((h == 0) && !force) ? MATERIAL_STONE : state;
				world.setBlockState(pos.offset(side, l).up(h), currState);
			}
		}
	}
	
	
	
	/*
	 * 
	 */
	protected BlockPos buildSpiralStaircase(World world, BlockPos pos, int numSpirals, EnumFacing side) {
		for (int i = 0 ; i < numSpirals ; ++i) {
			pos = buildSpiralStaircase_One(world, pos, side);
		}
		return pos;
	}
	
	
	/*
	 * 
	 */
	protected BlockPos buildSpiralStaircase_One(World world, BlockPos pos, EnumFacing side) {
		/* one spiral up will bring up 4 blocks up. */
		for (int i = 0 ; i < 4 ; ++i) {
			
			world.setBlockState(pos, MATERIAL_STAIRLANDING);
			pos = pos.offset(side, 1);
			
			world.setBlockState(pos, MATERIAL_STAIRS.withProperty(BlockStairs.FACING, side));
			pos = pos.offset(side, 1).up();
			
			side = side.rotateY();
		}
		return pos;
	}
}
