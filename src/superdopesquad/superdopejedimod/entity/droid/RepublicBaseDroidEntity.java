package superdopesquad.superdopejedimod.entity.droid;


import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;
import superdopesquad.superdopejedimod.entity.ai.EntityAIAttackRangedFactionAware;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;


public abstract class RepublicBaseDroidEntity extends BaseDroidEntity implements IRangedAttackMob {
		
	
	protected double movementSpeed;
	
	
	public RepublicBaseDroidEntity(World worldIn, String name, String displayName) {
		
		super(worldIn, name, displayName);
		
		this.setupAI();
		
		// how much experience do you get it you kill it?
		this.experienceValue = 5;
		
		// Properties that we need to have later.
		this.shadowSize = 1.0F;
		
		// Customize this properties in daughter classes to get different behaviors.
		this.movementSpeed = 1.0;
	}
	
	
//	@Override
//	public void registerEntityRender() {}
	
	
	// set up AI tasks
	@Override
	protected void setupAI() {
		
		// clear any tasks assigned in super classes.
	   clearAITasks(); 
	   
	   // All Republic droids have the following tasks:
	   
	   // Priority 1: If you see someone from the Empire, shoot to kill.
	   this.tasks.addTask(1, new EntityAIAttackRangedFactionAware(this, this.movementSpeed, 1, 3, 20,
			   SuperDopeJediMod.classManager.getFactionInfo(SuperDopeJediMod.classManager.FACTION_EMPIRE)));
	
	   // Priority 2: When not attacking the Empire, stare at the closest person.
	   this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 0.02F));

	   // All Republic droids have the following targetTasks:
	   
	   // Priority 0: target anything that attacked me.
	   this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));  
	   
	   // Priority 1: target the nearest attackable player I can find.
	   this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}


//	@Override
//	public EntityAgeable createChild(EntityAgeable ageable) {
//		
//		return null;
//	}
	
	
	@Override
	protected void applyEntityAttributes() {
		
	    super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);

	    // need to register any additional attributes
	   //getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	
//	
//	// After it dies, what equipment should it drop?
//	@Override
//	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}
//
	
    // Attack the specified entity using a ranged attack.
    // @param distanceFactor How far the target is, normalized and clamped between 0.1 and 1.
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
    	SuperDopeJediMod.weaponManager.ThrowPlasmaShotBlue(world, this, target, PowerLevel.STANDARD);
    }

    
//	@Override
//	protected boolean canDespawn() {
//		
//		return false;
//	}
}