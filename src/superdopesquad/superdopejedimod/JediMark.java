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
import net.minecraft.init.Items;
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
import net.minecraftforge.fml.common.registry.GameRegistry;


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
	
	
	/*
	 *
	 */
	@Override
	public void registerRecipe() {
		ItemStack swordStack = new ItemStack(Items.STONE_SWORD);
		ItemStack arrowStack = new ItemStack(Items.ARROW);
		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this), "x x", " y ", "x x", 'x', swordStack, 'y', arrowStack);
	}
	
	
	/*
	 * This function is automatically called by Minecraft whenever anybody right-mouse-clicks on a JediMark block.
    */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, 
									EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		/* Don't do anything if we are the client. 
		 * SuperDopeSquad: ignore this for now. Know that if you do any block-creation, then you need this at the beginning of your function. */
        if (worldIn.isRemote)
            return true;
        
        System.out.println("JediMark: activated, remote=" + worldIn.isRemote + ", pos=" + pos);
       
        /* default material. */
        IBlockState blockState = DEFAULT_BLOCK;

        /* If there is a brick in the player's hand, then use that material instead of the default material. */
        ItemStack heldItem = player.getHeldItem(hand);
        Item gotItem = (heldItem != null) ? heldItem.getItem() : null;
        if ((gotItem != null) && (gotItem instanceof ItemBlock)) {
        	ItemBlock gotItemBlock = (ItemBlock) gotItem;
        	Block heldBlock = gotItemBlock.getBlock();
        	int meta = heldItem.getMetadata();
        	blockState = heldBlock.getStateFromMeta(meta);
        }
        
        /* lets see what side they touched, and build something different based on that. */
        if ((side == EnumFacing.NORTH) || 
        	(side == EnumFacing.SOUTH) ||
        	(side == EnumFacing.WEST) || 
        	(side == EnumFacing.EAST)) {
        	
        	/* Build an arc on the same axis that it was touched. */
        	boolean axis_shift = (side == EnumFacing.NORTH) ||  (side == EnumFacing.SOUTH);
        	GeometryUtil.buildArc(worldIn, pos, 30, axis_shift, blockState);
        	return true;
        }
        
        if (side == EnumFacing.UP) {
        	/* Now lets try a dome. */
        	GeometryUtil.buildSphereNonDestructive(worldIn, pos, 15, blockState);
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
