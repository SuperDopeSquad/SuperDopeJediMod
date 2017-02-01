package superdopesquad.superdopejedimod.entity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.SuperDopeObjectGeneratable;


// Note:
// EntityCreature is an EntityLiving that has AI (it moves with path-finding and attacks). 
// EntityAgeable is an EntityCreature that can come in various sizes/ages. 
// EntityAnimal is an EntityAgeable that enables breeding by implementing IAnimal.
// EntityTameable is EntityAnimal that implement IEntityOwnable 


public abstract class BaseEntityTameable extends EntityTameable implements SuperDopeEntity, SuperDopeObjectGeneratable {

	
	private String _name = "";
	private String _displayName = "";
	protected float shadowSize = 1.0F;
	
	
	public BaseEntityTameable(World worldIn, String name, String displayName) {
		
		super(worldIn);
		
		this._name = name;
		this._displayName = displayName;
				
		// Insert this object into our collection of custom objects, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	@Override
	public String getName() {
		
		return this._name;
	}
	
	
	public String getFullName() {
		
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	@Override
	public ITextComponent getDisplayName() {
		
        TextComponentString textcomponentstring = new TextComponentString(this._displayName);
        return textcomponentstring;
	}

	
	// you don't have to call this as it is called automatically during EntityLiving subclass creation
	@Override
	protected void applyEntityAttributes()
	{
	    super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
//	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
//	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
//	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
//	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
//	   // MC-TODO: add interesting values for the other attributes.
//	   
//	    // need to register any additional attributes
//	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
//	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}
	
	
//	@Override
//	protected boolean isAIEnabled()
//	{
//	   return true;
//	}
	
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
	
	
	@Override
	public void registerObject() {
		
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		//System.out.println("registerObject's name: " + this.getName());
		//System.out.println("resourceLocation: " + resourceLocation.toString());
	  	EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
	};
	
	
	@Override
	public void registerRecipe() {
		return;
	}
	
	
	@Override
	public void registerModel() {
	    return;
	}
	
	
	@Override
	public void registerEntityRender() {
		return;
	}
	
	
	@Override
	public void generateEnd(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateNether(World world, Random random, int i, int j) {
		return;
	}
}