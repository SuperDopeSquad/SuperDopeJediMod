package superdopesquad.superdopejedimod.weapon;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;


public abstract class BaseRangedWeapon extends ItemBow implements SuperDopeObject {

	protected String name = "";
	
	public BaseRangedWeapon(String name) {
		
		// Call our super class constructor, "Block".
		super();
				
		// Stash our internal name that we'll use for this block.
		this.name = name;
				
		// I don't know what happens if you don't call this, but it is in every tutorial :-)
		this.setUnlocalizedName(this.name);
				
		// By default, we'll put all new blocks in the combat tab.
		this.setCreativeTab(CreativeTabs.COMBAT);
				
		// Insert this object into our collection of custom blocks, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	@Override
	public void registerObject() {
		
		// Register the item with the game.
		//GameRegistry.registerItem(this, this.name);
		GameRegistry.register(this.setRegistryName(this.name));
	}
	
	
	@Override
	public void registerRecipe() {
		return;
	}
	
	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseRangedWeapon) this).getName(), "inventory"));
	}
}
