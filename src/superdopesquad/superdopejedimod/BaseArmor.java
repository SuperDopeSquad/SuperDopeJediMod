package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseArmor extends ItemArmor implements SuperDopeObject {
	
	
	private String _name = "";


	public BaseArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	
	public BaseArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn, String nameIn) {
		
		// Note that the documentation is very light on what "renderIndex" should do.  I submit -1, nothing bad happens.
		super(materialIn, -1, equipmentSlotIn);
			
		// Set the name.
		this._name = nameIn;
		this.setUnlocalizedName(this.getName());
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}

	
	public String getName() { 
		//return this._name.toLowerCase(); 
		return this._name;
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
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(this.getFullName(), "inventory"));
	}

	
	@Override
	public void registerRecipe() {
	}
}
