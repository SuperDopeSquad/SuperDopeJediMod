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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseBlock extends Block implements SuperDopeObjectGeneratable {
	
	// Instance Members
	private String _name = "";
	private boolean _showUpInCreativeTab = true;
	
	
	public BaseBlock(Material material, String name) {
		this(material, name, true);
	}
	
	
	public BaseBlock(Material material, String name, boolean showUpInCreativeTab) {
		// Call our super class constructor, "Block".
		super(material);
		
		// Stash our internal name that we'll use for this block.
		this._name = name;
		
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
	

	@Override // from SuperDopeObject
	public String getName() { 
		return this._name.toLowerCase(); 
	}
	

	@Override // from SuperDopeObject
	public String getFullName() {
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	@Override // from SuperDopeObject
	public void registerBlocks(RegistryEvent.Register<Block> event) {
    
		// System.out.println("Inside BaseBlock:registerBlocks: this.getName(): " + (this.getName() == null ? "null" : this.getName()));
		// System.out.println("Inside BaseBlock:registerBlocks: this.getFullName(): " + this.getFullName());
		// System. out.println("Inside BaseBlock:registerBlocks: this.getRegistryName(): " + (this.getRegistryName() == null ? "null" : this.getRegistryName().toString()));
		
		this.setRegistryName(this.getName());
		event.getRegistry().register(this);
		
		// System.out.println("Inside BaseBlock:registerBlocks: this.getRegistryName(): " + (this.getRegistryName() == null ? "null" : this.getRegistryName().toString()));
	}
	
	
	@Override // from SuperDopeObject
    public void registerItems(RegistryEvent.Register<Item> event) {

		// System.out.println("Inside BaseBlock:registerItems: this.getName(): " + (this.getName() == null ? "null" : this.getName()));
		// System.out.println("Inside BaseBlock:registerItems: this.getFullName(): " + this.getFullName());
		// System.out.println("Inside BaseBlock:registerItems: this.getRegistryName(): " + (this.getRegistryName() == null ? "null" : this.getRegistryName().toString()));
		
		event.getRegistry().register(new ItemBlock(this).setRegistryName(this.getName()));
	}

	
	@Override // from SuperDopeObject
	public void blockBreakEvent(BreakEvent e)
	{
		//System.out.println("Inside BaseBlock:blockBreakEvent");
		return;
	}
	
	
	@Override
	public void registerRecipe() {
		return;
	}
	
	
	@Override
	public void registerModel() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		String location = SuperDopeJediMod.MODID + ":" + ((BaseBlock) this).getName();
	    renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(location));
	}
}
