package superdopesquad.superdopejedimod.teleporter;


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

	
public class TeleporterWrench extends BaseItem {

		
	public TeleporterWrench(String unlocalizedName) {
			      
		super(unlocalizedName);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
		
		
	@Override
	public void registerRecipe() {
		
		ItemStack itemStackTeleporterParts = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts);
		ItemStack itemStackThis = new ItemStack(this);
		ItemStack itemStackTeleporterPartsMany = new ItemStack(SuperDopeJediMod.teleporterManager.teleporterParts, 3);
		
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackThis, " x ", " x ", " x ", 'x', itemStackTeleporterParts);	
    	
      	// The recipe to recycle this item and return it's ingredients.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameRecycler(this), null, itemStackTeleporterPartsMany, "x", 'x', itemStackThis);	
	}

	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, 
    		float hitX, float hitY, float hitZ) {
        
		boolean isWorldServer = (!world.isRemote);
		IBlockState blockStateClicked = world.getBlockState(blockPos);
    	Block blockClicked = blockStateClicked.getBlock();
    	boolean isTeleporterStartingKit = (blockClicked instanceof TeleporterStartingKit);
       	boolean isTeleporterFinishingKit = (blockClicked instanceof TeleporterFinishingKit);
           	
    	//System.out.println("DEBUG: inside TeleporterWrench:onItemUse: " + blockClicked.toString() + 
    	//		" : " + hand.name() + " : " + facing.getName() + " : " + (isTeleporterStarterKit) + " : " + (isTeleporterKit));
    	
    	// If we are on the server, and we are being held in the main hand, and this is actually a droidkit , ...
    	if ((isWorldServer) && (hand == EnumHand.MAIN_HAND)) {
    		
    		if (isTeleporterStartingKit) {
    		
    			System.out.println("about to create a sideA teleporter");
    			
    			// Create a starter teleporter.
    			if (TeleporterManager.createTeleporter(player, world, blockPos, facing, TeleporterSide.SIDE_A)) {
    		
    				// Return something relevant.
    				return EnumActionResult.SUCCESS;
    			}
    		}
    		
    		if (isTeleporterFinishingKit) {
   
    			System.out.println("about to create a sideB teleporter");
    			
    			// Create a starter teleporter.
    			if (TeleporterManager.createTeleporter(player, world, blockPos, facing, TeleporterSide.SIDE_B)) {
    		
    				// Return something relevant.
    				return EnumActionResult.SUCCESS;
    			}
    		}
    	}
    	
    	
   	
    	return EnumActionResult.PASS;
    }
}
