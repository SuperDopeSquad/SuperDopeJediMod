package superdopesquad.superdopejedimod.entity.droid;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;


public abstract class BaseDroidEntity extends BaseEntityAnimal {


	public BaseDroidEntity(World worldIn, String name, String displayName) {
		
		super(worldIn, name, displayName);
		
		this.setupAI();
		
		// I am not sure if this is important or not, but experimenting with ways
		// to keep entity from despawning.
		this.enablePersistence();
	}


	
	// set up AI tasks
	protected void setupAI() {}
	
	
	@Override
	public void registerEntityRender() {}


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		
		return null;
	}
	
	
	@Override
	protected void applyEntityAttributes() {
		
	    super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);

	    // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	
	
	// After it dies, what equipment should it drop?
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		
		System.out.println(this.toString() + " DROPPING EQUIPMENT for some reason!");
	}

    
	@Override
	protected boolean canDespawn() {
		
		return false;
	}
	
	
//	@Override
//    protected void despawnEntity()
//    {
//		System.out.println(this.toString() + " DESPAWNING for some reason!");
//		
//		super.despawnEntity();
//    }
}
