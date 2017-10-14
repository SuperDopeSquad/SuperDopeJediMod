package superdopesquad.superdopejedimod.entity.droid;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.entity.EntityManager;


public abstract class RepublicBaseDroidHead extends BaseItem {

	
	protected Class _classToMake = null;
	protected Class _classWeClick = DroidKit.class;
	
	
	public RepublicBaseDroidHead(String unlocalizedName, Class classToMake) {
		
        super(unlocalizedName);
        this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(1);
        
        this._classToMake = classToMake;
	}
	
	
	@Override
	public void registerRecipe() {}
	
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isDroidKit = (blockClicked instanceof DroidKit);
    	
    	//System.out.println("DEBUG: inside RepublicUtilityDroidHeadBase:onItemUse: " + blockClicked.toString() + 
    	//		" : " + hand.name() + " : " + facing.getName() + " : " + (isDroidKit));
    	
    	// If we are on the server, and we are being held in the main hand, and this is actually a droidkit , ...
    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND) && (isDroidKit)) {
        	
    		// Destroy the existing block.
    		world.setBlockToAir(blockPos);
    		
    		// Destroy the item in hand, which is THIS.  This is somewhat dangerous, so i do a double-check
    		// that the current item actually equals THIS before i do the delete.
    		ItemStack itemStackCurrentItem = player.inventory.getCurrentItem();
    		Item itemCurrentItem = itemStackCurrentItem.getItem();
    		if (itemCurrentItem.equals(this)) {
    			player.inventory.deleteStack(itemStackCurrentItem);
    		}
    		else {
    			System.out.println("ERROR, current item wasn't what we expected.  Not deleting current item.");
    		}
    		
    		// Create the new entity.
    		RepublicBaseDroidEntity entity = (RepublicBaseDroidEntity) EntityManager.createEntity(this._classToMake, world, blockPos);

    		// Return something relevant.
    		return EnumActionResult.SUCCESS;
    	}
    	
    	return EnumActionResult.PASS;
    }
}
