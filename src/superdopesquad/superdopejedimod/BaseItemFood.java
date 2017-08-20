package superdopesquad.superdopejedimod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public abstract class BaseItemFood extends ItemFood implements SuperDopeObject, ClassAwareInterface {

	private String _name = "";
	
	
	public BaseItemFood(String name, int amount, 
			float saturation, boolean isWolfFood) {
		
		super(amount, saturation, isWolfFood);
	
		this._name = name;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(this.getName());
		this.setCreativeTab(CreativeTabs.FOOD);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return this._name.toLowerCase(); 
	}
	
	
	public String getFullName() {	
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
	
	}
	
	
	@Override
    public void registerItems(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().register(this.setRegistryName(this.getName()));
	}
	
	
	@Override
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
	
	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    //renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseItemFood) this).getName(), "inventory"));
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(this.getFullName(), "inventory"));
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
