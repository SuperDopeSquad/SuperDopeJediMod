package superdopesquad.superdopejedimod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public abstract class BaseItem extends Item implements SuperDopeObject, ClassAwareInterface {

	protected String name = "";
	
	
	public BaseItem(String unlocalizedName) {
		
		super();
	
		this.name = unlocalizedName;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MISC);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	@Override
	public void registerObject() {
		
		// Register the item with the game.
		//GameRegistry.registerItem(this, name);
		GameRegistry.register(this.setRegistryName(this.name));
	}
	
	
	@Override
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
	
	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + this.getName(), "inventory"));
	}
	
	
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
	    	
		SuperDopeJediMod.classManager.onUpdateHandlerClassAware(stack, world, entity, itemSlot, isSelected);
	}
	
	
	@Override
	public List<ClassInfo> GetFriendlyClasses() {

		return new ArrayList<ClassInfo>();
	}


	@Override
	public List<ClassInfo> GetUnfriendlyClasses() {
		
		return new ArrayList<ClassInfo>();
	}


	@Override
	public boolean IsUseFriendlyOnly() {
		
		return false;
	}


	@Override
	public boolean IsUseUnfriendlyBanned() {
		
		return false;
	}	
}
