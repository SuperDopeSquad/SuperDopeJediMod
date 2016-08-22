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
	/*
	 * This is the special constructor function that is called on game startup when the first "JediMark" object is created.
	*/
	public JediMark(String localName) 
	{
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	
	/*
	 * This function will build a column of bricks starting at the coordinates in "pos". You can change how big the column goes by changing the
	 *  number in the "for" loop below.
	 */
	protected void PlaceColumn(World worldIn, BlockPos pos) {
		/* "Blocks" is the database of all blocks that are in the default Minecraft block collection. Here we pick out the one we want to use for
		 * our column of blocks. */
		IBlockState columnBlock = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
		
		/* Here we loop. We create a new block, and then move up one level, then repeat. */
		for (int level = 0 ; level < 100 ; ++level) {
			worldIn.setBlockState(pos, columnBlock);
			pos = pos.up();
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
        
        /* Only by clicking on the up-side do we build a column. Therefore, if they hit any other side besides the up-side, we can quit 
         * out of this function right now, by using the "return" statement, and returning "false", which tells minecraft that we did nothing with this 
         * mouse-click, and they can do something else with it (like build a brick). */
        if (side != EnumFacing.UP)
        	return false;
        
        /* Make sure the block above is empty. If it not empty, then quit out right now, and do nothing.. */
        BlockPos startPos = pos.up();
        if (!worldIn.isAirBlock(startPos))
        	return false;
        
        /* Create our new column of blocks directly above the clicked block. */
        System.out.println("JediMark: creating extension, pos=" + startPos);
        PlaceColumn(worldIn, startPos);
		
        /* We are now done. We leave this function by calling "return", this time with "true", to tell minecraft that we actually did something. */
        return true;
    }
}
