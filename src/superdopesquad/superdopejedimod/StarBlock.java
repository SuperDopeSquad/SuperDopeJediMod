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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


/*
*/
public class StarBlock extends BaseBlock 
{
	/* 
	 * The bounding box constructor takes 6 args:
	 * 		(begin-x, begin-y, begin-z, end-x, end-y, end-z)
	 *  For a full cube, one corner is 0.0, and the opposite corner is 1.0. 
	 *  Our JSON files configure the box as a 16x16 pixel, with 2 pixels shaved off each side.
	 *  That's 2/16 = 0.125 when converting from a 16 to 100 scale. 
	 */
	protected static final AxisAlignedBB BOUNDING_BOX 
		= new AxisAlignedBB(0.125D, 0.125D, 0.125D, 0.875D, 0.875D, 0.875D);

	
	/*
	 * This is the special "constructor" function that is called on game startup 
	 * when each instance is created.
	 */
	public StarBlock(String localName) {
		super(Material.IRON, localName);
		this.setLightLevel(1.0F);
	}
	
	
	/*
	 * This method tells minecraft what the bounding box of our block is. Note that a bounding box
	 *  is the smallest three dimensional rectangle (technically a rectangular prism) that fully 
	 *  encloses our shape.
	 *  The JSON file tells minecraft the dimensions of our block, but is only used by the 
	 *  drawing code. Unfortunately we need to give them the same information here, as the
	 *  bounding box code doesnt read the json file. 
	 * @see net.minecraft.block.Block#getBoundingBox(net.minecraft.block.state.IBlockState, net.minecraft.world.IBlockAccess, net.minecraft.util.math.BlockPos)
	 */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
	   return BOUNDING_BOX;
	}
	
	
	/*
	 * This function is automatically called by Minecraft whenever anybody right-mouse-clicks on this block.
     */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, 
								EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
    }
	
	
	/*
	 *
	 */
	@Override
	public void registerRecipe() {
		ItemStack stack1 = new ItemStack(Items.SUGAR);
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "x x", "   ", "x x", 'x', stack1);
	}
	
    
	/*
	 * This method is used to determine if blocks underneath us should be rendered. Fully opaque
	 * cubes completely hide the blocks underneath them, so they are not redrawn. We are not
	 * fully opaque (we are slightly smaller), so we need to return
	 * false here so the minecraft engine draws the blocks underneath correctly.
	 * @see net.minecraft.block.Block#isOpaqueCube(net.minecraft.block.state.IBlockState)
	 */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
