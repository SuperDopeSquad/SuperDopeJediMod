package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import superdopesquad.superdopejedimod.gui.GuiHandler;
import superdopesquad.superdopejedimod.gui.TileEntityGenerator;


public class Generator extends BaseBlock implements ITileEntityProvider {
	
	public Generator(String unlocalizedName){
	
		super(Material.IRON, unlocalizedName);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
	}

	public Item getItemDropped(int metadata, Random random, int fortune) {
    
		return Item.getItemFromBlock(SuperDopeJediMod.generator);
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

	
	@Override
	public void blockBreakEvent(BreakEvent e)
	{
		System.out.println("Inside Generator:blockBreakEvent");
		return;
	}
	
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		
		System.out.println("Inside Generator:breakBlock");
		
		TileEntityGenerator te = (TileEntityGenerator) world.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int slot = 0; slot < handler.getSlots(); slot++) {
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		super.breakBlock(world, pos, state);
	}
	
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new TileEntityGenerator();
	
	}

	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileEntityGenerator();

	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
		if(!worldIn.isRemote) {
			playerIn.openGui(SuperDopeJediMod.instance, GuiHandler.GENERATOR_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	
	
}
