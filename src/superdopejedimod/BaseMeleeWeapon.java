package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseMeleeWeapon extends ItemSword implements SuperDopeObject {

	protected String name = "";
	
	public BaseMeleeWeapon(String name, ToolMaterial material) {
		
		// Call our super class constructor, "Block".
		super(material);
		
		// Stash our internal name that we'll use for this block.
		this.name = name;
		
		// I don't know what happens if you don't call this, but it is in every tutorial :-)
		this.setUnlocalizedName(this.name);
						
		// By default, we'll put all new blocks in the combat tab.
		this.setCreativeTab(CreativeTabs.tabCombat);
						
		// Insert this object into our collection of custom blocks, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	public void registerObject() {
		
		// Register the item with the game.
		GameRegistry.registerItem(this, this.name);
	}
	
	
	public void registerRecipe() {
		return;
	}
	
	
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseMeleeWeapon) this).getName(), "inventory"));
	}
}
