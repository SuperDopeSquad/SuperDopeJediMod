package superdopesquad.superdopejedimod.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public abstract class BaseTool extends ItemPickaxe implements SuperDopeObject, ClassAwareInterface {

	
	private String _name = "";


	
	public BaseTool(String unlocalizedName, ToolMaterial material) {
		
		super(material);
	
		this._name = unlocalizedName;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MISC);
		
		// We'll need to keep this value around.
		
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return _name.toLowerCase(); 
	}
	
	
	public String getFullName() {
		
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {

	}
	
	
	@Override
    public void registerItems(RegistryEvent.Register<Item> event) {
		
		// System.out.println("Inside BaseItem:registerItems: this.getName(): " + (this.getName() == null ? "null" : this.getName()));
		// System.out.println("Inside BaseItem:registerItems: this.getFullName(): " + this.getFullName());
		// System.out.println("Inside BaseItem:registerItems: this.getRegistryName(): " + (this.getRegistryName() == null ? "null" : this.getRegistryName().toString()));
	
		event.getRegistry().register(this.setRegistryName(this.getName()));
		
		// System.out.println("Inside BaseItem:registerItems: this.getRegistryName(): " + (this.getRegistryName() == null ? "null" : this.getRegistryName().toString()));
	}
	
//	@Override
//	public void registerObject() {
//		
//		 GameRegistry.register(this.setRegistryName(this.name));
//	}
	
	
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
	
	
//	@Override
//	public Item getItemDropped(int metadata, Random random, int fortune) {
//        		
//		return super.getItemDropped(metadata, random, fortune); //SuperDopeJediMod.teleporterManager.teleporterFinishingKitItem;
//	}
	
	
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
