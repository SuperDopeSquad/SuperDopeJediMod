package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseItemFood extends ItemFood implements SuperDopeObject {

	protected String name = "";
	
	
	public BaseItemFood(String name, int amount, 
			float saturation, boolean isWolfFood) {
		
		super(5, 5, false);
	
		this.name = name;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.FOOD);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	public void registerObject() {
		
		// Register the item with the game.
		//GameRegistry.registerItem(this, name);
		GameRegistry.register(this.setRegistryName(this.name));
	}
	
	
	public void registerRecipe() {
		
		// Example of registering a crafttable recipe.
		// ***
		// ItemStack stickStack = new ItemStack(Items.stick);
    	// ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    	//  GameRegistry.addRecipe(new ItemStack(this), "xx", " y", " x", 'x', ironIngotStack, 'y', stickStack);

		// Example of registering a smelting/furnace recipe.
		//GameRegistry.addSmelting(new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11), 0.1F);

		return;
	}
	
	
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseItemFood) this).getName(), "inventory"));
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
