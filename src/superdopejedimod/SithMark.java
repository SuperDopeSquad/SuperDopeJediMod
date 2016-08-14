package superdopesquad.superdopejedimod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
        
        /* Make sure the block above is empty, and the user clicked on one of the sides. */
        BlockPos targetPos = pos.up();
        if (!worldIn.isAirBlock(targetPos) || (side == EnumFacing.UP) || (side == EnumFacing.DOWN)) {
        	return false;
        }
        
        /* Create our new block directly above the clicked block. */
        System.out.println("SithMark: creating extension, pos=" + targetPos);
        worldIn.setBlockState(targetPos, Blocks.TORCH.getDefaultState());
        return true;
    }
}
