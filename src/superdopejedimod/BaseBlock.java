package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseBlock extends Block implements SuperDopeObject {
	
	protected String name = "";
	
	
	public BaseBlock(Material materialIn, String nameInput) {
		
		// Call our super class constructor, "Block".
		super(materialIn);
		
		// Stash our internal name that we'll use for this block.
		this.name = nameInput;
		
		// I don't know what happens if you don't call this, but it is in every tutorial :-)
		this.setUnlocalizedName(name);
		
		// By default, we'll put all new blocks in the blocks tab.
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		// Insert this object into our collection of custom blocks, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	public void registerObject() {
		
		// Register the block with the game.
		GameRegistry.registerBlock(this, name);
	}
	
	
	public void registerRecipe() {
		return;
	}
	
	
	public void registerModel() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		String location = SuperDopeJediMod.MODID + ":" + ((BaseBlock) this).getName();
		System.out.println("BaseBlock: registering model: " + location);
	    renderItem.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(location, "inventory"));
	}
    
	public void generateEnd(World world, Random random, int i, int j) {
		return;
	}
	
	
	public void generateSurface(World world, Random random, int i, int j) {
		return;
	}
	
	
	public void generateNether(World world, Random random, int i, int j) {
		return;
	}

}
