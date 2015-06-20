package superdopejedimod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;


public abstract class BaseItem extends Item {

	protected String name = "";
	
	
	public BaseItem(String unlocalizedName) {
		
		super();
	
		this.name = unlocalizedName;
		this.setMaxStackSize(64);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customItems.add(this);
	}
	
	
	public String getName() { 
		return name; 
	}
	
	
	public void registerItem() {
		// Register the item with the game.
		GameRegistry.registerItem(this, name);
	}
	
	
	public void registerRecipe() {
		return;
	}
	
	
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseItem) this).getName(), "inventory"));
	}
}
