package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseItem extends Item implements SuperDopeObject {

	protected String name = "";
	
	
	public BaseItem(String unlocalizedName) {
		
		super();
	
		this.name = unlocalizedName;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	public void registerObject() {
		
		// Register the item with the game.
		GameRegistry.registerItem(this, name);
	}
	
	
	public void registerRecipe() {
		
		// Example of registering a crafttable recipe.
		// ***
		// ItemStack stickStack = new ItemStack(Items.stick);
    	// ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    	//  GameRegistry.addRecipe(new ItemStack(this), "xx", " y", " x", 'x', ironIngotStack, 'y', stickStack);

		// Example of registering a smelting/furnace recipe.
		// GameRegistry.addSmelting(Items.diamond, new ItemStack(Items.apple), 1.0F);		
		// Translation: smelt one diamond and you get one apple, and the player gets 1 experience point.

		return;
	}
	
	
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseItem) this).getName(), "inventory"));
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
