package superdopesquad.superdopejedimod.entity.ship;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;
import superdopesquad.superdopejedimod.faction.ClassManager;
import java.util.Optional;


/**
 * No AI: only moves when there is a passenger.
 */
public class ImperialShuttleEntity extends BaseEntityShip {	
	
	 private static final float DEGREES2RADIANS = 0.017453292F;
	 
	/**
	 * constructor
	 */
	public ImperialShuttleEntity(World worldIn) {
		super(worldIn, "imperialShuttleEntity", "imperialShuttle");
		
		// This sets the bounding box size, not the actual model that you see rendered.
		this.setSize(0.6F, 2.0F);
		
		// how much experience do you get it you kill it?
		this.experienceValue = 1;
		
		// Properties that we need to have later.
		this.shadowSize = 0.5F;
		
		// prevent motiony degrade
		this.setNoGravity(true);
	}
	
	
	/**
	 * Called during startup, so the engine knows about our custom rendering (drawing) code.
	 */
	@Override // from SuperDopeEntity
	public void registerEntityRender() {
		Class renderBaseClass = ImperialShuttleRender.class;
		Class modelBaseClass = ImperialShuttleModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, this.shadowSize);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	/**
	 * Called every couple ticks to update animation. 
	 * Here, we use this callback to transfer forward-signals from the driver to the ship. The player entity will automatically have
	 * its moveForward and moveStrafe properties set when they press w/a/s/z. We only respond to the w key for now 
	 * (tie fighters only go forward).
	 */
	@Override // from EntityLivingBase
	public void onLivingUpdate() {
		EntityLivingBase driver = getControllingLivingPassenger();
		if (driver != null) {
			// Copy over our forward movement, dropping moveStrafing and moveVertical. 
			if (driver.moveForward >= 0.0) {
				this.setMoveForward(driver.moveForward);
			}
			
			// Record the direction we are facing. pitch=up and down angle, yaw=left and right
			this.prevRotationYaw = this.rotationYaw;
		    this.rotationYaw = driver.rotationYaw;
		    this.prevRotationPitch = this.rotationPitch;
		    this.rotationPitch = driver.rotationPitch /** 0.5F */;
		    this.setRotation(this.rotationYaw, this.rotationPitch);
		    
		    // Do I need this?
		    this.rotationYawHead = this.rotationYaw;
		    this.renderYawOffset = this.rotationYaw;
		    
		    // Hitting the "a" key shoots the primary weapon.
		    if (driver.moveStrafing > 0.0f) {
		    	float offset_vertical = 6.0f; // middle of cockpit
				float offset_forward = 3.0f; // directly forward of cockpit
		    	float pitch = this.rotationPitch;
				float yaw = this.rotationYaw;
		    	float xvector = -MathHelper.sin(yaw * DEGREES2RADIANS) * MathHelper.cos(pitch * DEGREES2RADIANS);
			    float yvector = -MathHelper.sin(pitch * DEGREES2RADIANS);
			    float zvector = MathHelper.cos(yaw * DEGREES2RADIANS) * MathHelper.cos(pitch * DEGREES2RADIANS);
			    float deltax = (xvector * offset_forward);
			    float deltay = (yvector * offset_forward);
			    float deltaz = (zvector * offset_forward);
			    
			    //System.out.println("TF: shooting vehicle=" +this.posX + ", " + this.posY + ", " + this.posZ);
		    	Vec3d startPos = new Vec3d(this.posX + deltax, this.posY + deltay + offset_vertical, this.posZ + deltaz);
		    	SuperDopeJediMod.weaponManager.ThrowForwardPlasmaShotRed(world, this, PowerLevel.HEAVY, Optional.of(startPos));
		    }
	    }
		
		// Continue to do all the updates in the parent class.
	    super.onLivingUpdate();
	}
	
	
	/**
	 * Called when the mob's health reaches 0. Not currently doing anything, but perhaps in the future 
	 * this ship should explode in a firey explosion.
	 */
	@Override // from EntityLivingBase
	public void onDeath(DamageSource cause) {
		 // TODO: Explode!
		super.onDeath(cause);
	}
	
	
	/**
	 * Called by onLivingUpdate() to translate the direction controls (moveForward, moveStrafing, moveVertical) 
	 * to actual movement (motionX, motionY, motionZ), and then a call to the system move() routine.
	 */
	@Override // from EntityLivingBase
	public void travel(float inMoveStrafing, float inMoveVertical, float inMoveForward) {
	   
	    if (this.isBeingRidden() && this.canBeSteered() && this.canPassengerSteer() && (inMoveForward != 0.0)) {
	    	
        	// Calculate velocity.
            float v = (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
            v *= 10.0f;
	        
            // Map the forward velocity to its individual x, y, and z components according to the direction we are looking
            // (using a little trigonometry).
            Vec3d vec3d = this.getLookVec();
            this.motionX = v * vec3d.x;
            this.motionY = v * vec3d.y;
            this.motionZ = v * vec3d.z;
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	    } else {
	    	
	    	// We have no body on us. TODO: we need to fall to the ground.
	    	//this.onGround = true;
	    	this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
	        super.travel(inMoveStrafing, inMoveVertical, inMoveForward);
	    }
	}
	

	/**
     * Returns the Y offset from the entity's position for any entity riding this one.
     * should return the yoffset of the cockpit area.
     */	
	@Override // from Entity
    public double getMountedYOffset() {
	  // TODO: this is hard coded to sit on top of the cockpit, so you can see, Lower once i figure out how to have 
	  // transparent pixels in the cockpit window.
	  return 9.0;
    }
	
	/**
	 * EntityAnimal overrides this, so back to default of 0.
	 */
	@Override // from Entity
	public double getYOffset() {
		return 0.0D;
	}
	
	/**
	 *  Copied from EntityFlying; do we need this?
	 */
	@Override // from EntityLivingBase
	public boolean isOnLadder() {
		return false;
	}
	
	@Override // EntityLivingBase
	protected void applyEntityAttributes() {
	   super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);	
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);

	   // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}
	
	
	/*
	 * PASSENGER STUFF: some of this code could go to a generic vehicle class.
	 */
	
	/**
	 * NOTE: To dismount, we can:
	 * 	(a) manually call dismountRidingEntity() here in this class.
	 *  (b) If the user hits left-shift, there is code in EntityPlayer::updateRidden() that detects this (isSneaking)
	 *      and calls dismountRidingEntity() for us.
	 */
	@Override // from EntityLiving
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
	   
		if (!this.isBeingRidden()) {
			player.startRiding(this);
			this.onGround = false;
			return true;
		}
		
		/* otherwise, let the system handle the interaction. */
	    return super.processInteract(player, hand);
	}
	
	
    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
	@Override // from Entity
    @Nullable
    public Entity getControllingPassenger() {
        List<Entity> list = this.getPassengers();
        if (list.isEmpty()) {
        	return null;
        }
        
        return (Entity) list.get(0);
    }
	
	@Nullable
    public EntityLivingBase getControllingLivingPassenger() {
	    Entity driver = getControllingPassenger();   
		return (driver instanceof EntityLivingBase) ? (EntityLivingBase) driver : null;
    }
	
	
	/**
	 * TODO: only empire can drive this thing.
	 */
	@Override // for Entity
    public boolean canPassengerSteer() {
		EntityLivingBase entity = this.getControllingLivingPassenger();
        return entity instanceof EntityPlayer ? ((EntityPlayer)entity).isUser() : !this.world.isRemote;
    }
	
    /**
     * Returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
	@Override // EntityLiving
    public boolean canBeSteered() {
        return canPassengerSteer();
    }
	
	/**
     * If the rider should be dismounted from the entity when the entity goes under water
     *
     * @param rider The entity that is riding
     * @return if the entity should be dismounted when under water
     */
	@Override // for Entity
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }
	
	@Override // for Entity
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }
	
    /**
     * If a rider of this entity can interact with this entity. Should return true on the
     * ridden entity if so.
     *
     * @return if the entity can be interacted with from a rider
     */
	@Override
    public boolean canRiderInteract() {
        return false;
    }

	/**
	 *  do we need this?
	 */
	@Override // from Entity
	public void fall(float distance, float damageMultiplier) {
    }

	/**
	 * do we need this?
	 */
	@Override // from Entity
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }
}