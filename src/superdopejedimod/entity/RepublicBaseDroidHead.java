package superdopesquad.superdopejedimod.entity;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.BaseItem;


public abstract class RepublicBaseDroidHead extends BaseItem {

	
	protected Class _classToMake = null;
	protected Class _classWeClick = DroidKit.class;
	
	
	public RepublicBaseDroidHead(String unlocalizedName, Class classToMake) {
		
        super(unlocalizedName);
        this.setCreativeTab(CreativeTabs.MATERIALS);
        
        this._classToMake = classToMake;
	}
	
	
	@Override
	public void registerRecipe() {}
	
	
	 /**
     * Called when a Block is right-clicked with this Item
     */
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isDroidKit = (blockClicked instanceof DroidKit);
    	
    	System.out.println("DEBUG: inside RepublicUtilityDroidHeadBase:onItemUse: " + blockClicked.toString() + 
    			" : " + hand.name() + " : " + facing.getName() + " : " + (isDroidKit));
    	
    	// If we are on the server, and we are being held in the main hand, and this is actually a droid torso, ...
    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND) && (isDroidKit)) {
        	
    		// Destroy the existing block.
    		System.out.println("DEBUG: attempting to break block: " + blockClicked.toString() + " : ");
    		world.setBlockToAir(blockPos);
    		
    		// Create the new entity.
    		RepublicBaseDroidEntity entity = (RepublicBaseDroidEntity) EntityManager.createEntity(this._classToMake, world, blockPos);

    		// Return something relevant.
    		return EnumActionResult.SUCCESS;
    	}
    	
    	return EnumActionResult.PASS;
    }
}
