package superdopesquad.superdopejedimod.weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public abstract class BaseMeleeWeapon extends ItemSword implements SuperDopeObject, ClassAwareInterface {

	protected String name = "";
	
	
	public BaseMeleeWeapon(String nameIn, ToolMaterial material) {
		
		// Call our super class constructor, "Block".
		super(material);
		
		// Stash our internal name that we'll use for this block.
		this.name = nameIn;
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
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseMeleeWeapon) this).getName(), "inventory"));
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
