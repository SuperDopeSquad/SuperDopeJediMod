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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;


public abstract class BaseMeleeWeapon extends ItemSword implements SuperDopeObject, ClassAwareInterface {

	private String _name = "";
	
	
	public BaseMeleeWeapon(String nameIn, ToolMaterial material) {
		
		// Call our super class constructor, "Block".
		super(material);
		
		// Stash our internal name that we'll use for this block.
		this._name = nameIn;
		this.setUnlocalizedName(this.getName());
						
		// By default, we'll put all new blocks in the combat tab.
		this.setCreativeTab(CreativeTabs.COMBAT);
						
		// Insert this object into our collection of custom blocks, so we 
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
		return;
	}
	
	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    //renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(SuperDopeJediMod.MODID + ":" + ((BaseMeleeWeapon) this).getName(), "inventory"));
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
