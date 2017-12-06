package superdopesquad.superdopejedimod.hangar;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseItem;
import superdopesquad.superdopejedimod.GeometryUtil;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.Utilities;

	
public class HangarWrench extends BaseItem {

		
	public HangarWrench(String unlocalizedName) {
			      
		super(unlocalizedName);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
		
		
	@Override
	public void registerRecipe() {
				
		ItemStack itemStackHangerParts = new ItemStack(SuperDopeJediMod.hangarManager.hangarParts);
		ItemStack itemStackThis = new ItemStack(this);
		ItemStack itemStackHangerPartsMany = new ItemStack(SuperDopeJediMod.hangarManager.hangarParts, 3);
		
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackThis, " x ", " x ", " x ", 'x', itemStackHangerParts);	
    	
    	// The recipe to recycle this item and return it's ingredients.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameRecycler(this), null, itemStackHangerPartsMany, "x", 'x', itemStackThis);	
	}

	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isHangerPadKit = (blockClicked instanceof HangarPadKit);
       	boolean isShipKit = (blockClicked instanceof ShipKit);
           	
    	//System.out.println("DEBUG: inside HangerWrench:onItemUse: " + blockClicked.toString() + 
    	//		" : " + hand.name() + " : " + facing.getName() + " : " + (isHangerPadKit) + " : " + (isShipKit));
    	
    	// If we are on the server, and we are being held in the main hand ...
    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND)) {
    		
    		if (isHangerPadKit) {	  			
     			if (HangarManager.createHangerPad(player, world, blockPos, facing, (HangarPadKit) blockClicked)) {
    				return EnumActionResult.SUCCESS;
    			}
     			
     			return EnumActionResult.FAIL;
    		}
    		
    		if (isShipKit) {  			
    			if (HangarManager.createShip(player, world, blockPos, facing)) {	
    				return EnumActionResult.SUCCESS;
    			}
    			
    			return EnumActionResult.FAIL;
    		}
    	}
   	   	
    	return EnumActionResult.PASS;
    }
}
