package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockChest;
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
 * NOTE:
 * 		This JediMark java class depends at run-time on the following other files:
 * 
 * 			The blockstate JSON file, which lists all the model variants (the different forms the block can take)
 * 			superdopejedimod/src/resources/assets/superdopejedimod/blockstates/jediMark.json
 * 		
 * 			The model JSON file that describes the 3D geometry for when the block is displayed in the world.
 * 			superdopejedimod/src/resources/assets/superdopejedimod/models/block/jediMark.json
 * 
 * 			The model JSON file that describes the 3D geometry for when the block is displayed in your hand.
 * 			superdopejedimod/src/resources/assets/superdopejedimod/models/item/jediMark.json
 * 
 * 			The PNG graphics file that will be displayed on each side of the block (note that we could change it
 *          to have different image son each side, but for now we have the same image repeated on all sides.
 * 			superdopejedimod/src/resources/assets/superdopejedimod/textures/blocks/jediMark.json
 * 
 * 		PNG files should be made with Photoshop or Gimp.
 * 
 * 		JSON files can be edited in any text editor, like Notepad, or Eclipse. But remember that JSON files have a 
 * 			specific and strict syntax (rules for how the text needs to be formatted), so each ({[ character is 
 * 			actually important!
 * 
 * 		SuperDopeSquad: Use this class as a template for creating other blocks. Duplicate this file, rename "JediMark" everywhere you
 * 			see it to a new name, and then duplicate all the above files, making sure to name the files the same name as
 * 			your new class.
 * 
 * 		READ all the comments below to see what each section of code is doing.
*/
public class JediMark extends BaseBlock 
{
	/* "Blocks" is the database of all blocks that are in the default Minecraft block collection. Here we pick out the one we want to use for
	 * our column of blocks. */
	protected static IBlockState DEFAULT_BLOCK = Blocks.STONEBRICK.getDefaultState()
			.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
	
	/*
	 * This is the special constructor function that is called on game startup when the first "JediMark" object is created.
	*/
	public JediMark(String localName) 
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
	 * This function will build a column of bricks starting at the coordinates in "pos". You can change how big the column goes by changing the
	 *  number in the "for" loop below.
	 */
	protected void PlaceColumn(World worldIn, BlockPos pos, int height, IBlockState blockState) {
		/* Here we loop. We create a new block, and then move up one level, then repeat. */
		for (int level = 0 ; level < height ; ++level) {
			worldIn.setBlockState(pos, blockState);
			pos = pos.up();
		}
	}
	
	/*
	 * This is based on the equation:
	 *  x^2 + y^2 = r^2
	 * NOTE: hardcoded to go along the x-axis.
	 */
	protected void PlaceArc(World worldIn, BlockPos pos, int radius, boolean axis_shift, IBlockState blockState) {
		
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
	 * This function is automatically called by Minecraft whenever anybody right-mouse-clicks on a JediMark block.
    */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		/* Don't do anything if we are the client. 
		 * SuperDopeSquad: ignore this for now. Know that if you do any block-creation, then you need this at the beginning of your function. */
        if (worldIn.isRemote)
            return true;
        
        System.out.println("JediMark: activated, remote=" + worldIn.isRemote + ", pos=" + pos);
        
        /* Make sure the block above is empty. If it not empty, then quit out right now, and do nothing.. */
        // BlockPos startPos = pos.up();
        //if (!worldIn.isAirBlock(startPos))
        //	return false;
       
        /* default material. */
        IBlockState blockState = DEFAULT_BLOCK;

        /* If there is a brick in the player's hand, then use that material. */
        Item gotItem = heldItem.getItem();
        if ((gotItem != null) && (gotItem instanceof ItemBlock)) {
        	ItemBlock gotItemBlock = (ItemBlock) gotItem;
        	Block heldBlock = gotItemBlock.getBlock();
        	/* TODO: we don't want default state, we want exact state. */
        	blockState = heldBlock.getDefaultState();
        	
        	int blockId = Block.getIdFromBlock(heldBlock);
        	blockState = Block.getStateById(blockId);
        }
        
        /* lets see what side they touched, and build something different based on that. */
        if ((side == EnumFacing.NORTH) || 
        	(side == EnumFacing.SOUTH) ||
        	(side == EnumFacing.WEST) || 
        	(side == EnumFacing.EAST)) {
        	
        	/* Build an arc on the same axis that it was touched. */
        	boolean axis_shift = (side == EnumFacing.NORTH) ||  (side == EnumFacing.SOUTH);
        	PlaceArc(worldIn, pos, 30, axis_shift, blockState);
        	return true;
        }
        
        if (side == EnumFacing.UP) {
        	 //PlaceColumn(worldIn, startPos);
        	
        	/* Now lets try a dome. */
            PlaceDome(worldIn, pos, 15, blockState);
            return true;
        }
        
        /* This is a cool exploding arc. TODO(coolguybri): sneak this back in somehow.
        BlockPos arcPos = startPos.add(0, 0, 3);
        for (int radius = 1 ; radius < 50 ; radius++) {
        	PlaceArc(worldIn, arcPos.down(), radius, columnBlock);
        	arcPos = arcPos.add(0, 0, 1);
        } */
        
        /* We are now done. We leave this function by calling "return", this time with "true", to tell minecraft that we actually did something. */
        return true;
    }
}
