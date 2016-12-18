/**
 * 
 */
package superdopesquad.superdopejedimod;

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
	 */
	public static double distance(BlockPos pos1, BlockPos pos2) {
		double d0 = (double)(pos1.getX() - pos2.getX());
	    double d1 = (double)(pos1.getY() - pos2.getY());
	    double d2 = (double)(pos1.getZ() - pos2.getZ());
	    return Math.sqrt((d0 * d0) + (d1 * d1) + (d2 * d2));
	}
	
	
	/**
	 * 
	 * @param world
	 * @param pos
	 */
	public static void setBlockToAir(World world, BlockPos pos) {
		world.setBlockState(pos, MATERIAL_AIR);
	}
	
	
	/**
	 * 
	 * @param world
	 * @param pos
	 * @param height
	 * @param bstate
	 */
	public static void buildColumn(World world, BlockPos pos, int height, IBlockState bstate) {
		for (int h = 0 ; h < height ; ++h) {
			world.setBlockState(pos.up(h), bstate);
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
	public static void buildWall(World world, BlockPos pos, int length, int height, EnumFacing side, IBlockState state) {
		for (int l = 0; l < length; ++l) {
			for (int h = 0 ; h < height ; ++h) {
				world.setBlockState(pos.offset(side, l).up(h), state);
			}
		}
	}
	
	
	/**
	 * 
	 */
	public static void clearWall(World world, BlockPos pos, int length, int height, EnumFacing side) {
		buildWall(world, pos, length, height, side, MATERIAL_AIR);
	}
	
	
	/**
	 * 
	 * @param world
	 * @param pos
	 * @param length
	 * @param width
	 * @param lengthSide
	 * @param widthSide
	 * @param state
	 */
	public static void buildPlane(World world, BlockPos pos, int length, int width, EnumFacing lengthSide, EnumFacing widthSide, IBlockState state) {
		for (int l = 0; l < length; ++l) {
			for (int w = 0 ; w < width ; ++w) {
				world.setBlockState(pos.offset(lengthSide, l).offset(widthSide, w), state);
			}
		}
	}

	
	/**
	 * defaulted to length = south, width=west
	 * @param world
	 * @param pos
	 * @param length
	 * @param width
	 * @param lengthSide
	 * @param widthSide
	 * @param state
	 */
	public static void clearPlane(World world, BlockPos pos, int length, int width, EnumFacing lengthSide, EnumFacing widthSide) {
		buildPlane(world, pos, length, width, lengthSide, widthSide, MATERIAL_AIR);
	}
	
	
	/**
	 * 
	 * @param world
	 * @param pos
	 * @param length
	 * @param width
	 * @param height
	 * @param lengthSide
	 * @param widthSide
	 */
	public static void buildBlock(World world, BlockPos pos, int length, int width, int height, EnumFacing lengthSide, EnumFacing widthSide, IBlockState state) {
		for (int h = 0 ; h < height ; ++h ) {
			buildPlane(world, pos.up(h), length, width, lengthSide, widthSide, state);
		}
	}
	
	
	/**
	 * 
	 * @param world
	 * @param pos
	 * @param length
	 * @param width
	 * @param height
	 * @param lengthSide
	 * @param widthSide
	 */
	public static void clearBlock(World world, BlockPos pos, int length, int width, int height, EnumFacing lengthSide, EnumFacing widthSide) {
		buildBlock(world, pos, length, width, height, lengthSide, widthSide, MATERIAL_AIR);
	}
	
	
	/*
	 * This is based on the equation:
	 *  x^2 + y^2 = r^2
	 * NOTE: hardcoded to go along the x-axis.
	 */
	public static void buildArc(World worldIn, BlockPos pos, int radius, boolean axis_shift, IBlockState blockState) {
		
		/* first quarter. */
		int radius_squared = radius * radius;
		int last_y = 0;
		int last_x = -radius;
		for (int x = -radius ; x <= 0 ; ++x) {
			double y_d = Math.sqrt(radius_squared - (x * x));
			int y = (int) Math.round(y_d);
			for (int py = last_y + 1 ; py < y ; ++py) {
				worldIn.setBlockState(pos.add(axis_shift ? 0 : last_x, py, axis_shift ? last_x : 0), blockState);
			}
			worldIn.setBlockState(pos.add(axis_shift ? 0 : x, y, axis_shift ? x : 0), blockState);
			last_x = x;
			last_y = y;
		}
		
		/* second quarter. */
		for (int x = 1 ; x <= radius ; ++x) {
			double y_d = Math.sqrt(radius_squared - (x * x));
			int y = (int) Math.round(y_d);
			for (int py = last_y - 1 ; py > y ; --py) {
				worldIn.setBlockState(pos.add(axis_shift ? 0 : x, py, axis_shift ? x : 0), blockState);
			}
			worldIn.setBlockState(pos.add(axis_shift ? 0 : x, y, axis_shift ? x : 0), blockState);
			last_x = x;
			last_y = y;
		}	
	}
	
	/**
	 * This is not the most efficient algorithm, but it works. I iterate over every block in the 
	 *  [radius x radius x (radius / 2) ] cube, and then calculate the distance from the center of that block
	 *  to pos. If it equals the radius (after rounding), then that block is on the border of the sphere,
	 *  and should be turned "on".
	 */
	public static void buildDome(World worldIn, BlockPos pos, int radius, IBlockState block) {
		for (int x = -radius ; x <= radius ; ++x) {
			for (int z = -radius ; z <= radius ; ++z) {
				for (int y = 0 ; y <= radius ; ++y) {
					BlockPos currpos = pos.add(x, y, z);
					int d = (int) Math.round(distance(currpos, pos));
					if (d == radius) {
						worldIn.setBlockState(currpos, block);
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
	 */
	public static void buildSphere(World worldIn, BlockPos pos, int radius, IBlockState block, boolean destructive) {
		int numhit = 0;
		
		for (int x = -radius ; x <= radius ; ++x) {
			for (int z = -radius ; z <= radius ; ++z) {
				for (int y = -radius ; y <= radius ; ++y) {
					BlockPos currpos = pos.add(x, y, z);
					int distance_fromCenter = (int) Math.round(distance(currpos, pos));
					if (distance_fromCenter == radius) {
						/* If we are destructive, then we always place it. Otherwise, we need to make sure the path
						 * to the center is unubstructed. */
						boolean placeIt = destructive;
						if (!placeIt) {
							double x_delta = pos.getX() - currpos.getX();
							double y_delta = pos.getY() - currpos.getY();
							double z_delta = pos.getZ() - currpos.getZ();
							
							double z_angle = Math.atan2(y_delta, x_delta);
							double y_angle = Math.atan2(z_delta, x_delta);
							
							
							//Anyway, Assuming direction is initially z aligned, then rotated around x axis followed by rotation around y axis.
							// x=x0 + distance * cos (angleZ) * sin (angleY)
							// Y=y0 + distance * sin (Anglez)
							// Z=z0 + distance * cos (angleZ) * cos (angleY)
							
							for (int d = 0 ; d < 10 ; ++d) {
								double new_x = pos.getX() + d * Math.cos(z_angle) * Math.sin(y_angle);
								double new_y = pos.getY() + d * Math.sin(z_angle);
								double new_z = pos.getZ() + d * Math.cos(z_angle) * Math.cos(y_angle);
								BlockPos spikeypos = new BlockPos(new_x, new_y, new_z);
								worldIn.setBlockState(spikeypos, block);
							}
							
						}
						
						if (placeIt) {
							worldIn.setBlockState(currpos, block);
						}
					}
				}
			}
		}
	}
}
