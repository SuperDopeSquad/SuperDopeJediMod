package superdopesquad.superdopejedimod.teleporter;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
//import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;
import superdopesquad.superdopejedimod.entity.SuperDopeEntity;


public abstract class BaseEntity extends EntityLivingBase implements SuperDopeEntity {

	
	private String _name = "";
	private String _displayName = "";
	protected float shadowSize = 1.0F;


	public BaseEntity(World worldIn, String  name, String displayName) {
		
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
	
	
	
	// set up AI tasks
	protected void setupAI() {}
	
	
	
	@Override
	public void registerObject() {
		
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		//System.out.println("registerObject's name: " + this.getName());
		//System.out.println("resourceLocation: " + resourceLocation.toString());
	  	EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
	};
	
	
	@Override
	public void registerEntityRender() {}


//	@Override
//	public EntityAgeable createChild(EntityAgeable ageable) {
//		
//		return null;
//	}
//	
	
//	@Override
//	protected void applyEntityAttributes() {
//		
//	    super.applyEntityAttributes(); 
//
////	    // standard attributes registered to EntityLivingBase
////	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
////	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
////	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
////	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
////
////	    // need to register any additional attributes
////	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
////	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
//	}
	
	
//	// After it dies, what equipment should it drop?
//	@Override
//	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
//		
//		System.out.println(this.toString() + " DROPPING EQUIPMENT for some reason!");
//	}

    
//	@Override
//	protected boolean canDespawn() {
//		
//		return false;
//	}
	
	
//	@Override
//    protected void despawnEntity()
//    {
//		System.out.println(this.toString() + " DESPAWNING for some reason!");
//		
//		super.despawnEntity();
//    }
}
