package superdopesquad.superdopejedimod.tinkertable;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import superdopesquad.superdopejedimod.BaseBlock;
import superdopesquad.superdopejedimod.BaseBlockContainer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.gui.GuiHandler;


//public class Generator extends BaseBlock implements ITileEntityProvider {
public class TinkerTable extends BaseBlockContainer {

	
	public TinkerTable(String unlocalizedName){
	
		super(Material.IRON, unlocalizedName);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		// MC: I added this.
		this.isBlockContainer = true;
	    GameRegistry.registerTileEntity(TinkerTableTileEntity.class, "tinkerTableTileEntity");
	}

	public Item getItemDropped(int metadata, Random random, int fortune) {
    
		return Item.getItemFromBlock(SuperDopeJediMod.tinkerTable);
	}



	public void registerRecipe() {
	
	// Recipe for creating an Generator.
		ItemStack chromateIngotStack = new ItemStack(SuperDopeJediMod.chromateIngot);
		ItemStack electricFluxIngotStack = new ItemStack(SuperDopeJediMod.electricFluxIngot);
		ItemStack compressedMetalPlateStack = new ItemStack(SuperDopeJediMod.compressedMetalPlate);
		ItemStack chromateShardStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);
	
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
				"ADA", 
				"CBC", 
				"ADA", 
				'A', compressedMetalPlateStack, 
				'B', electricFluxIngotStack, 
				'C', chromateShardStack,
				'D', chromateIngotStack);
			
		}

	
//	@Override
//	public void blockBreakEvent(BreakEvent e)
//	{
//		System.out.println("Inside Generator:blockBreakEvent");
//		return;
//	}
	
	
//	@Override
//	public void breakBlock(World world, BlockPos pos, IBlockState state) {
//		
//		System.out.println("Inside Generator:breakBlock");
//		
//		GeneratorTileEntity te = (GeneratorTileEntity) world.getTileEntity(pos);
//		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//		for(int slot = 0; slot < handler.getSlots(); slot++) {
//			ItemStack stack = handler.getStackInSlot(slot);
//			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
//		}
//		super.breakBlock(world, pos, state);
//	}
	
	
//	@Override
//	public TileEntity createNewTileEntity(World worldIn, int meta) {
//
//		System.out.println("Inside Generator:createNewTileEntity");
//		
//		return new GeneratorTileEntity();
//	
//	}
//
//	
//	@Override
//	public TileEntity createTileEntity(World world, IBlockState state) {
//		
//		return new GeneratorTileEntity();
//
//	}
	
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
//	
//		ItemStack itemStack = worldIn.getHeldItem(playerIn);
//		SuperDopeJediMod.superDopeCommonProxy.displayCreditGui(itemStack.getMaxStackSize());
//
//		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
//	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
		
		// System.out.println("Inside Generator:onBlockActivated");
		
		if(!(worldIn.isRemote)) {
			// System.out.println("Inside Generator:onBlockActivated: Client Only");
			
			String playerName = "null";
			if (playerIn != null) {
				playerName = playerIn.getDisplayNameString();
			}
			
			String posName = "null";
			if (pos != null) {
				posName = pos.toString();
			}
			
			String worldName = "null";
			if (worldIn != null) {
				worldName = worldIn.toString();
			}
			
			// System.out.println("player: " + playerName + ", world: " + worldName + ", pos: " + posName);	
			playerIn.openGui(SuperDopeJediMod.instance, GuiHandler.TINKERTABLE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
			
			//Minecraft.getMinecraft().displayGuiScreen(new GeneratorGUI());
			//SuperDopeJediMod.superDopeCommonProxy.displayCreditGui(itemStack.getMaxStackSize());
			//playerIn.openGui(SuperDopeJediMod.instance, GuiEnum.GENERATOR.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
			//playerIn.openGui(SuperDopeJediMod.instance, GuiHandler.GENERATOR_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TinkerTableTileEntity();
	}
	
	
	
}
