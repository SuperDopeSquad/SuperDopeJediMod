package superdopesquad.superdopejedimod;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BaseArmor extends ItemArmor implements SuperDopeObject {
	
	protected String name = "";

	
	public BaseArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn, String nameIn) {
		
		// Note that the documentation is very light on what "renderIndex" should do.  I submit -1, nothing bad happens.
		super(materialIn, -1, equipmentSlotIn);
			
		// Set the name.
		this.name = nameIn;
		this.setUnlocalizedName(name);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}

	
	public String getName() { 
		return this.name; 
	}
	
	
	@Override
	public void registerObject() {
		
		GameRegistry.registerItem(this, this.name);
	}

	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + this.getName(), "inventory"));
	}

	
	@Override
	public void registerRecipe() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void generateEnd(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void generateNether(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}
}
