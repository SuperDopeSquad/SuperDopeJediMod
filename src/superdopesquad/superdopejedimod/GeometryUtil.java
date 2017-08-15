/**
 * 
 */
package superdopesquad.superdopejedimod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 *
 */
public class GeometryUtil {

	public static IBlockState MATERIAL_AIR = Blocks.AIR.getDefaultState();
	
	
	/**
	 *  Computes the distance between two three-dimensional points. 
	 *  
	 *  @param pos1 First point.
	 *  @param pos2 Second point.
	 */
	public static double distance(BlockPos pos1, BlockPos pos2) {
		double d0 = (double)(pos1.getX() - pos2.getX());
	    double d1 = (double)(pos1.getY() - pos2.getY());
	    double d2 = (double)(pos1.getZ() - pos2.getZ());
	    return Math.sqrt((d0 * d0) + (d1 * d1) + (d2 * d2));
	}
	
	
	/**
	 * Delete a block and replace it with an Air block.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 * 
	 * @param world The world to place the blocks.
	 * @param pos The position of the block to delete.
	 */
	public static void setBlockToAir(World world, BlockPos pos) {
		world.setBlockState(pos, MATERIAL_AIR);
	}
	
	public static boolean isBlockAir(World world, BlockPos pos) {
		IBlockState bstate = world.getBlockState(pos);
		return (bstate.getMaterial() == Material.AIR);
	}
	
	
	/**
	 * Builds a column (one block by 1 block column starting at startPos and going up).
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 * 
	 * @param world The world to place the blocks.
	 * @param startPos The starting position for the column.
	 * @param height The height of the column, including the startPos.
	 * @param bstate The blockstate to use for new blocks in the column.
	 */
	public static List<BlockPos> buildColumnDestructive(World world, BlockPos startPos, int height, IBlockState bstate) {
		
		 List<BlockPos> blockPositions = new ArrayList();
		
		// Sanity check the params.
		if (height < 0) {
			throw new IllegalArgumentException("buildColumnDestructive: need a positive height");
		}
		
		// Lay down the column.
		for (int h = 0 ; h < height ; ++h) {
			BlockPos newBlockPos = startPos.up(h);
			world.setBlockState(newBlockPos, bstate);
			blockPositions.add(newBlockPos);
		}
		
		return blockPositions;
	}
	
	
	/**
	 * Checks to see if a column (one block by 1 block column starting at startBlockPos and going up) would be destructive here.
	 * 
	 * @param world The world to place the blocks.
	 * @param startBlockPos The starting position for the column.
	 * @param height The height of the column, including the startPos.
	 */
	public static boolean checkIfColumnWouldBeDestructive(World world, BlockPos startBlockPos, int height) {
		
		//System.out.println("checkIfColumnWouldBeDestructive blockPos: " + startBlockPos.toString());
		
		// Sanity check the params.
		if (height < 0) {
			//System.out.println("checkIfColumnWouldBeDestructive: need a positive height. Sent in: " + height);
			return false;
		}
		
		BlockPos currentBlockPos = startBlockPos;
		
		// Check each block in the column.
		for (int h = 0 ; h < height ; ++h) {
			
			if (!(world.isAirBlock(currentBlockPos))) {
				System.out.println(("rut-roh: blockPos: " + startBlockPos.toString()));
				return true;
			}
			
			currentBlockPos = startBlockPos.up(h);
		}
		
		return false;
	}
	
	
	/**
	 * Builds a one-block thick wall starting at startPos, extending for the desired length and height.
	 *
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 * 
	 * @param world The world to place the wall.
	 * @param startPos The starting position for the wall.
	 * @param length How long the wall should be on the x (or z) axis.
	 * @param height The height of the wall on the y axis.
	 * @param side Which side to extend the wall (north, south, east, or west).
	 * @param bstate The blockstate to use for new blocks in the wall.
	 */
	public static void buildWallDestructive(World world, BlockPos startPos, int length, int height, EnumFacing side, IBlockState bstate) {
		// Sanity check the params.
		if (height < 0) {
			throw new IllegalArgumentException("buildWallDestructive: need a positive height");
		}
		if (length < 0) {
			throw new IllegalArgumentException("buildWallDestructive: need a positive length");
		}
		if ((side == EnumFacing.UP) || (side == EnumFacing.DOWN)) {
			throw new IllegalArgumentException("buildWallDestructive: need to supply east,west, north, or south");
		}
		
		// Lay down the wall.
		for (int l = 0; l < length; ++l) {
			for (int h = 0 ; h < height ; ++h) {
				world.setBlockState(startPos.offset(side, l).up(h), bstate);
			}
		}
	}
	
	
	/**
	 * Clear out a wall area with air.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way.
	 * 
	 * @param world The world to clear the blocks.
	 * @param startPos The starting position for the wall.
	 * @param length How long the wall should be on the x (or z) axis.
	 * @param height The height of the wall on the y axis.
	 * @param side Which side to extend the wall (north, south, east, or west).
	 */
	public static void clearWall(World world, BlockPos startPos, int length, int height, EnumFacing side) {
		buildWallDestructive(world, startPos, length, height, side, MATERIAL_AIR);
	}
	
	
	/**
	 * Builds a one-block high wall starting at startPos, extending for the desired length and height.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way.
	 * 
	 * @param world The world to place the plane.
	 * @param startPos The starting position for the plane.
	 * @param length The length of the plane.
	 * @param width The width of the plane.
	 * @param lengthSide The x or z axis for the length.
	 * @param widthSide The x or z axis for the width.
	 * @param bstate The blockstate to use for new blocks in the plane.
	 */
	public static void buildPlaneDestructive(World world, BlockPos startPos, int length, int width, EnumFacing lengthSide, EnumFacing widthSide, IBlockState bstate) {
		// Sanity check the params.
		if (length < 0) {
			throw new IllegalArgumentException("buildPlaneDestructive: need a positive length");
		}
		if (width < 0) {
			throw new IllegalArgumentException("buildPlaneDestructive: need a positive width");
		}
		if ((lengthSide == EnumFacing.UP) || (lengthSide == EnumFacing.DOWN)) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply east, west, north, or south for lengthSide.");
		}
		if ((widthSide == EnumFacing.UP) || (widthSide == EnumFacing.DOWN)) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply east, west, north, or south for lengthSide.");
		}
		if (widthSide == lengthSide.getOpposite()) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply different axis for length and width sides.");
		}
		
		// Lay down the plane.
		for (int l = 0; l < length; ++l) {
			for (int w = 0 ; w < width ; ++w) {
				world.setBlockState(startPos.offset(lengthSide, l).offset(widthSide, w), bstate);
			}
		}
	}

	
	public static boolean checkIfPlaneWouldBeDestructive(World world, BlockPos startPos, int length, int width, EnumFacing lengthSide, EnumFacing widthSide, IBlockState bstate, BlockPos skippablePos) {
				
		// Sanity check the params.
		if (length < 0) {
			throw new IllegalArgumentException("buildPlaneDestructive: need a positive length");
		}
		if (width < 0) {
			throw new IllegalArgumentException("buildPlaneDestructive: need a positive width");
		}
		if ((lengthSide == EnumFacing.UP) || (lengthSide == EnumFacing.DOWN)) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply east, west, north, or south for lengthSide.");
		}
		if ((widthSide == EnumFacing.UP) || (widthSide == EnumFacing.DOWN)) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply east, west, north, or south for lengthSide.");
		}
		if (widthSide == lengthSide.getOpposite()) {
			throw new IllegalArgumentException("buildPlaneDestructive: need to supply different axis for length and width sides.");
		}
		
		// Lay down the plane.
		for (int l = 0; l < length; ++l) {
			for (int w = 0 ; w < width ; ++w) {
				
				BlockPos currentPos = startPos.offset(lengthSide, l).offset(widthSide, w);
				IBlockState currentState = world.getBlockState(currentPos);
				boolean isPassableBlock = currentState.getBlock().isPassable(world, currentPos);	
				
				if (skippablePos.equals(currentPos)) {
					System.out.println("DEBUG: Skipping checking " + currentPos.toString() + ", which happens to be " + world.getBlockState(currentPos).toString());
				}
				else if (!(isPassableBlock)) {
					System.out.println("DEBUG: at location " + currentPos.toString() + " found a non-passable block: " + world.getBlockState(currentPos).toString());
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Clears a plane-like space and fills it with air.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way.
	 * 
	 * @param world The world to clear the blocks.
	 * @param startPos The starting position for the plane.
	 * @param length The length of the plane.
	 * @param width The width of the plane.
	 * @param lengthSide The x or z axis for the length.
	 * @param widthSide The x or z axis for the width.
	 */
	public static void clearPlane(World world, BlockPos pos, int length, int width, EnumFacing lengthSide, EnumFacing widthSide) {
		buildPlaneDestructive(world, pos, length, width, lengthSide, widthSide, MATERIAL_AIR);
	}
	
	
	/**
	 * Builds a rectangular prism.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way.
	 * 
	 * @param world The world to place the plane.
	 * @param pos The starting position for the plane.
	 * @param length The length of the prism.
	 * @param width The width of the prism.
	 * @param height The height of the prism.
	 * @param lengthSide The x or z axis for the length.
	 * @param widthSide The x or z axis for the width.
	 * @param bstate The blockstate to use for new blocks in the plane.
	 */
	public static void buildPrismDestructive(World world, BlockPos startPos, int length, int width, int height, EnumFacing lengthSide, EnumFacing widthSide, IBlockState bstate) {
		for (int h = 0 ; h < height ; ++h ) {
			buildPlaneDestructive(world, startPos.up(h), length, width, lengthSide, widthSide, bstate);
		}
	}
	
	
	/**
	 * Clears a space for a rectangular prism, replacing it with air.
	 *
	 * Note that this routine is destructive, and will permanently delete any block in its way.
	 * 
	 * @param world The world to clear the prism.
	 * @param pos The starting position for the plane.
	 * @param length The length of the prism.
	 * @param width The width of the prism.
	 * @param height The height of the prism.
	 * @param lengthSide The x or z axis for the length.
	 * @param widthSide The x or z axis for the width.
	 */
	public static void clearPrism(World world, BlockPos startPos, int length, int width, int height, EnumFacing lengthSide, EnumFacing widthSide) {
		buildPrismDestructive(world, startPos, length, width, height, lengthSide, widthSide, MATERIAL_AIR);
	}
	
	
	/*
	 * This is based on the equation:
	 *  x^2 + y^2 = r^2
	 * NOTE: hardcoded to go along the x-axis.
	 */
	public static void buildArc(World world, BlockPos centerPos, int radius, boolean axis_shift, IBlockState bstate) {
		
		/* first quarter. */
		int radius_squared = radius * radius;
		int last_y = 0;
		int last_x = -radius;
		for (int x = -radius ; x <= 0 ; ++x) {
			double y_d = Math.sqrt(radius_squared - (x * x));
			int y = (int) Math.round(y_d);
			for (int py = last_y + 1 ; py < y ; ++py) {
				world.setBlockState(centerPos.add(axis_shift ? 0 : last_x, py, axis_shift ? last_x : 0), bstate);
			}
			world.setBlockState(centerPos.add(axis_shift ? 0 : x, y, axis_shift ? x : 0), bstate);
			last_x = x;
			last_y = y;
		}
		
		/* second quarter. */
		for (int x = 1 ; x <= radius ; ++x) {
			double y_d = Math.sqrt(radius_squared - (x * x));
			int y = (int) Math.round(y_d);
			for (int py = last_y - 1 ; py > y ; --py) {
				world.setBlockState(centerPos.add(axis_shift ? 0 : x, py, axis_shift ? x : 0), bstate);
			}
			world.setBlockState(centerPos.add(axis_shift ? 0 : x, y, axis_shift ? x : 0), bstate);
			last_x = x;
			last_y = y;
		}	
	}
	
	
	/**
	 * Creates a dome. This is not the most efficient algorithm, but it works. I iterate over every block in the 
	 * [radius x radius x (radius / 2) ] cube, and then calculate the distance from the center of that block
	 * to pos. If it equals the radius (after rounding), then that block is on the border of the sphere,
	 * and should be turned "on".
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 * 
	 * @param world The world to place the new blocks in.
	 * @param centerPos The position of the center of the sphere. 
	 * @param radius The number of blocks on each side of the center; for a radius of 5, the sphere will be 11 blocks wide (5 on
	 * 				each side, plus the center block).
	 * @param bstate The blockstate to use for the surface of the sphere.
	 */
	public static void buildDomeDestructive(World world, BlockPos centerPos, int radius, IBlockState bstate) {
		for (int y = 0 ; y <= radius ; ++y) {
			for (int x = -radius ; x <= radius ; ++x) {
				for (int z = -radius ; z <= radius ; ++z) {
					BlockPos currPos = centerPos.add(x, y, z);
					int distanceFromCenter = (int) Math.round(distance(currPos, centerPos));
					if (distanceFromCenter == radius) {
						world.setBlockState(currPos, bstate);
					}
				}
			}
		}
	}
	

	/**
	 * Creates a flat circle, optionally filled-in.
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 * 
	 * @param world The world to place the new blocks in.
	 * @param centerPos The position of the center of the sphere. 
	 * @param radius The number of blocks on each side of the center; for a radius of 5, the sphere will be 11 blocks wide (5 on
	 * 				each side, plus the center block).
	 * @param bstate The blockstate to use for the surface of the sphere.
	 */
	public static void buildCircleDestructive(World world, BlockPos centerPos, int radius, IBlockState bstate, boolean filledIn) {
		for (int x = -radius ; x <= radius ; ++x) {
			for (int z = -radius ; z <= radius ; ++z) {
				BlockPos currPos = centerPos.add(x, 0, z);
				int distanceFromCenter = (int) Math.round(distance(currPos, centerPos));
				if ((distanceFromCenter == radius) || (filledIn && (distanceFromCenter < radius))) {
					world.setBlockState(currPos, bstate);
				}
			}
		}
	}

	
	/**
	 * Creates a sphere. This is not the most efficient algorithm, but it works. I iterate over every block in the 
	 * [radius x radius x radius] cube centered around centerPos, and then calculate the distance from the center of that block
	 * to centerPos. If it equals the radius (after rounding), then that block is on the border of the sphere,
	 * and should be turned "on".
	 * 
	 * Note that this routine is destructive, and will permanently delete any block in its way. 
	 *  
	 * @param world The world to place the new blocks in.
	 * @param centerPos The position of the center of the sphere. 
	 * @param radius The number of blocks on each side of the center; for a radius of 5, the sphere will be 11 blocks wide (5 on
	 * 				each side, plus the center block).
	 * @param bstate The blockstate to use for the surface of the sphere.
	 */
	public static void buildSphereDestructive(World world, BlockPos centerPos, int radius, IBlockState bstate) {
		for (int y = -radius ; y <= radius ; ++y) {
			for (int x = -radius ; x <= radius ; ++x) {
				for (int z = -radius ; z <= radius ; ++z) {
					BlockPos currPos = centerPos.add(x, y, z);
					int distanceFromCenter = (int) Math.round(distance(currPos, centerPos));
					if (distanceFromCenter == radius) {
						world.setBlockState(currPos, bstate);
					}
				}
			}
		}
	}
	
	
	/**
	 * This is not the most efficient algorithm, but it works. I iterate over every block in the 
	 *  [radius x radius x (radius / 2) ] cube, and then calculate the distance from the center of that block
	 *  to pos. If it equals the radius (after rounding), then that block is on the border of the sphere,
	 *  and should be turned "on".
	 *  
	 *  This algorithm starts at the top, and builds each plan, working down. It also keeps track of every column, 
	 *  and whether or not it has hit ground yet. One it does, we put no other blocks on that column underneath it. This allows for the sphere 
	 *  to wrap around rocks.
	 *  
	 * @param world The world to place the new blocks in.
	 * @param centerPos The position of the center of the sphere. 
	 * @param radius The number of blocks on each side of the center; for a radius of 5, the sphere will be 11 blocks wide (5 on
	 * 				each side, plus the center block).
	 * @param bstate The blockstate to use for the surface of the sphere.
	 */
	public static void buildSphereNonDestructive(World world, BlockPos centerPos, int radius, IBlockState bstate) {
		
		// Init the collision grid.
		int gridDimension = radius * 2 + 1;
		boolean[][] collisionGrid = new boolean[gridDimension][gridDimension];
		Set<String> blockageTypes = new HashSet<String>();
		
		for (int y = radius ; y >= -radius ; --y) {
			for (int z = -radius ; z <= radius ; ++z) {
				for (int x = -radius ; x <= radius ; ++x) {
					BlockPos currPos = centerPos.add(x, y, z);
					
					int x_grid = x + radius;
					int z_grid = z + radius;
					if (!collisionGrid[x_grid][z_grid]) {
						
						int distanceFromCenter = (int) Math.round(distance(currPos, centerPos));
						if (distanceFromCenter == radius) {
						
							boolean placeIt = isBlockAir(world, currPos);
							if (!placeIt) { 
								IBlockState currState = world.getBlockState(currPos);
								String blockName =  currState.getBlock().getUnlocalizedName();
								placeIt = (!currState.isFullBlock() && 
										   (blockName.equals("tile.snow") ||
										    blockName.equals("tile.flower") ||
										    blockName.equals("tile.flower1") ||
										    blockName.equals("tile.flower2") ||
										    blockName.equals("tile.water") ||
										    blockName.equals("tile.tallgrass"))); // TODO(coolguybri): any other block types that are OK to destroy?
								placeIt |= (currState.isFullBlock() && 
											(blockName.equals("tile.leaves")));
								if (!placeIt) {
									Material m = currState.getMaterial();
									blockageTypes.add(currState.getBlock().getUnlocalizedName() + ":" + (currState.isFullBlock() ? "full" : "partial"));
								}
							}
							
							if (placeIt) {
								world.setBlockState(currPos, bstate);
							} else {
								collisionGrid[x_grid][z_grid] = true;
							}
						}
					}
				}
			}
		}
		
		// While I am still testing this, print out the block-types that are not being destroyed.
		for (String blockage : blockageTypes) {
			System.out.println("JediMark: full sphere blocked by block-type: " + blockage);
		}
	}
}
