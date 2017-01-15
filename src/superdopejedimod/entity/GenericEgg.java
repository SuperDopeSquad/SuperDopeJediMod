package superdopesquad.superdopejedimod.entity;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;


public class GenericEgg extends ItemEgg implements SuperDopeObject {

	
	private String _name = "";
	private Class _classToMake = null;

	
	public GenericEgg(String name, Class classToMake) {
		
		super();
		
		this._name = name;
		this._classToMake = classToMake;
		
		this.setMaxStackSize(64);
		this.setUnlocalizedName(this.getName());
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
		// Insert this item into our collection of custom items, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public String getName() { 
		return this._name; 
	}
	
	
	public String getFullName() { 
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	@Override
	public void registerObject() {
		
		// Register the item with the game.
		GameRegistry.register(this.setRegistryName(this.getName()));
	}
	
	
	@Override
	public void registerRecipe() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void registerModel() {
	    
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	    renderItem.getItemModelMesher().register(this, 0, new ModelResourceLocation(this.getFullName(), "inventory"));
	}
	
	
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn) {
	        
		ItemStack itemstack = worldIn.getHeldItem(playerIn);

	    if (!worldIn.capabilities.isCreativeMode) {
	    	itemstack.func_190918_g(1);
	    }
	        
	    itemStackIn.playSound((EntityPlayer)null, worldIn.posX, worldIn.posY, worldIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

	    if (!itemStackIn.isRemote) {
	            
	    	GenericEntityEgg entityegg = new GenericEntityEgg(itemStackIn, worldIn);
	        entityegg.setClassToMake(this._classToMake);
	        entityegg.setHeadingFromThrower(worldIn, worldIn.rotationPitch, worldIn.rotationYaw, 0.0F, 1.5F, 1.0F);
	        itemStackIn.spawnEntityInWorld(entityegg);
	    }

	    worldIn.addStat(StatList.getObjectUseStats(this));
	    return new ActionResult(EnumActionResult.SUCCESS, itemstack);
	}
}
