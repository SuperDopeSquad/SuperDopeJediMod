package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseBlock extends Block implements SuperDopeObjectGeneratable {
	
	
	protected String name = "";
	private boolean _showUpInCreativeTab = true;
	
	
	public BaseBlock(Material material, String name) {
		
		this(material, name, true);
	}
	
	
	public BaseBlock(Material material, String name, boolean showUpInCreativeTab) {
		
		// Call our super class constructor, "Block".
		super(material);
		
		// Stash our internal name that we'll use for this block.
		this.name = name;
		
		// I don't know what happens if you don't call this, but it is in every tutorial :-)
		this.setUnlocalizedName(name);
		
		// We'll need to keep this value around.
		this._showUpInCreativeTab = showUpInCreativeTab;
		
		// By default, we'll put all new blocks in the blocks tab.
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		// Insert this object into our collection of custom blocks, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return this.name; 
	}
	
	
	@Override
	public void registerObject() {
		
		// Mandatory: This registers the block, whether or not you want it to show up in the creative tab or not.
		GameRegistry.register(this.setRegistryName(this.name));
			
		// Optional: This creates a corresponding Item for the block, which shows up in places like the creative tab.
		if (this._showUpInCreativeTab) {
			GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		}
	}
	
	
	@Override
	public void registerRecipe() {
		return;
	}
	
	
	@Override
	public void registerModel() {
		
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		String location = SuperDopeJediMod.MODID + ":" + ((BaseBlock) this).getName();
		//System.out.println("SuperDopeSquad: registering block: " + location);
	    //renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(location, "inventory"));
	    renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(location));
	}
    
	
	@Override
	public void generateEnd(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateNether(World world, Random random, int i, int j) {
		return;
	}
}
