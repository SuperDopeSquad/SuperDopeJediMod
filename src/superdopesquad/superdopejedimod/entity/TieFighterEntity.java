package superdopesquad.superdopejedimod.entity;


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
//import net.minecraft.stats.AchievementList;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.faction.ClassManager;



public class TieFighterEntity extends BaseEntityAnimal implements IRangedAttackMob {
				
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(TieFighterEntity.class, DataSerializers.BOOLEAN);
	
	
	/**
	 * 
	 * @param worldIn
	 */
	public TieFighterEntity(World worldIn) {
		
		super(worldIn, "tieFighterEntity", "Tie Fighter");
		
		// This sets the bounding box size, not the actual model that you see rendered.
		this.setSize(0.6F, 2.0F);
		
		// how much experience do you get it you kill it?
		this.experienceValue = 1;
		
		// Properties that we need to have later.
		this.shadowSize = 0.5F;
		
		// this.setSize(4.0F, 4.0F);
	    //    this.isImmuneToFire = true;
	    //    this.experienceValue = 5;
	    this.moveHelper = new TieFighterEntity.MoveHelper(this);
	}
	
	@Override
	protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SADDLED, Boolean.valueOf(false));
    }
	
	@Override
	public void registerEntityRender() {
		Class renderBaseClass = TieFighterRender.class;
		Class modelBaseClass = TieFighterModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, this.shadowSize);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	@Override
	public void registerRecipe() {
	}
	
    
	 /**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
	
	    if (!this.world.isRemote) {
	        /* if (this.getSaddled())
	        {
	            this.dropItem(Items.SADDLE, 1);
	        } */
	    }
	}
	
	
	@Override
	public void onLivingUpdate() {
		//System.out.println("ImperialProbe::onLivingUpdate this=" + this + ", entityLivingToAttack=" + this.getAITarget() + ", revengeTimer=" + this.getRevengeTimer() +
		//		", attackTarget=" + this.getAttackTarget());
	    super.onLivingUpdate();
	}
	
	
	 /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Saddle", this.getSaddled());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSaddled(compound.getBoolean("Saddle"));
    }
	
    /**
     * Attack the specified entity using a ranged attack.
     *  
     * @param distanceFactor How far the target is, normalized and clamped between 0.1 and 1.0
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        float damageAmount = 1;
    	SuperDopeJediMod.weaponManager.ThrowPlasmaShotRed(world, this, target, distanceFactor, damageAmount);
    }
    

	/**
     * Returns the Y offset from the entity's position for any entity riding this one.
     * should return the yoffset of the cockpit area.
     */
	@Override
    public double getMountedYOffset() {
        return (double) this.height * 0.67D;
    }
	
	@Override
	public double getYOffset() {
		return 0.0D;
	}
	
	// Copied from EntityFlying
	@Override
	public boolean isOnLadder() {
		return false;
	}
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		/* We do not auto-spawn anywhere. */
	}
	
	
	@Override
	protected void applyEntityAttributes() {
	   super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);	
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);

	   // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}
	
	// After it dies, what equipment should it drop?
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		//this.entityDropItem(new ItemStack(Items.REDSTONE), 0);
	}
	
	// Don't despawn if no players are anywhere near us.
	/*@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public double getYOffset() {
		return 0.0D;
	}
	
	// Copied from EntityFlying
	@Override
	public boolean isOnLadder() {
		return false;
	} */
	
	
	/*
	 * PASSENGER STUFF
	 */
	
	/**
	 * Update the position/rotation of any passengers. Base class probably does all we need,
	 *  but other derived classes also tilt the neck and stuff here.
	 */
	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
	}
	
    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
	@Override
    @Nullable
    public Entity getControllingPassenger() {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity) list.get(0);
    }
	
	// Do we need to override this? TODO: only empire.
	@Override
    public boolean canPassengerSteer() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof EntityPlayer ? ((EntityPlayer)entity).isUser() : !this.world.isRemote;
    }
	
    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
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
	@Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }
	
	// TODO: change when we determine passenger-list-size.
	@Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }
	
    /**
     * If a rider of this entity can interact with this entity. Should return true on the
     * ridden entity if so.
     *
     * @return if the entity can be interacted with from a rider
     */
	@Override // TODO: this has something to do wih the renderer.
    public boolean canRiderInteract() {
        return false;
    }
	
	
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (super.processInteract(player, hand)) {
        	return true;
        }
        
        if (player.isSneaking()) {
            return false;
        }
        
        ItemStack itemstack = player.getHeldItem(hand);
        /* if (itemstack.getItem() == Items.NAME_TAG) {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        } */
        
        if (/*this.getSaddled() && */ !this.isBeingRidden()) {
            /*if (!this.worldObj.isRemote) */{
                player.startRiding(this);
                System.out.println("RIDING");
            }
            return true;
        }
        
       /* if (itemstack.getItem() == Items.SADDLE) {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        } */
  
        return false;
    }
   


	
	/**
     * Returns true if the pig is saddled.
     */
    public boolean getSaddled() {
        return ((Boolean)this.dataManager.get(SADDLED)).booleanValue();
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean saddled) {
        if (saddled) {
            this.dataManager.set(SADDLED, Boolean.valueOf(true));
        } else {
            this.dataManager.set(SADDLED, Boolean.valueOf(false));
        }
    }
 
	
	
    /**
     * Moves the entity based on the specified heading.
     
	@Override
    public void moveEntityWithHeading(float strafe, float forward) {
    	
    	if (this.isInWater())
        {
            this.moveRelative(strafe, forward, 0.02F);
            this.moveEntity(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveRelative(strafe, forward, 0.02F);
            this.moveEntity(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else if (this.isBeingRidden() && this.canBeSteered()) {
        	// Find the captain of the ship.
            Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
            
        	// Copy all the angles from the captain.
            this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.rotationYaw;
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (this.canPassengerSteer()) {
                float f = (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.225F;
                this.setAIMoveSpeed(f);
                super.moveEntityWithHeading(0.0F, 1.0F);
            } else {
            	// No pilot, stop the train!
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }
        }
    } */
	
	
	// Copied from EntityFlying
	@Override
	public void fall(float distance, float damageMultiplier)
    {
    }

	// Copied from EntityFlying
	@Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }
	
    
       /* {
            float f = 0.91F;

            if (this.onGround)
            {
                f = this.worldObj.getBlockState(new BlockPos(
                		MathHelper.floor_double(this.posX), 
                		MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, 
                		MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }


//    public void moveEntityWithHeading(float strafe, float forward)
//    {
//        if (this.isInWater())
//        {
//            //this.moveRelative(strafe, forward, 0.02F);
//            this.moveRelative(strafe, 0, forward, 0.02F);
//            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
//            this.motionX *= 0.800000011920929D;
//            this.motionY *= 0.800000011920929D;
//            this.motionZ *= 0.800000011920929D;
//        }
//        else if (this.isInLava())
//        {
//            //this.moveRelative(strafe, forward, 0.02F);
//            this.moveRelative(strafe, 0, forward, 0.02F);
//            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
//            this.motionX *= 0.5D;
//            this.motionY *= 0.5D;
//            this.motionZ *= 0.5D;
//        }
//        else
//        {
//            float f = 0.91F;
//
//            if (this.onGround)
//            {
//                f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
//            }
//
//            float f1 = 0.16277136F / (f * f * f);
//            
//            //public void moveRelative(float strafe, float up, float forward, float friction)  
//            //this.moveRelative(strafe, forward, this.onGround ? 0.1F * f1 : 0.02F);
//            this.moveRelative(strafe, 0, forward, this.onGround ? 0.1F * f1 : 0.02F);
//            f = 0.91F;
//
//            if (this.onGround)
//            {
//                f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
//            }
//
//            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
//            this.motionX *= (double)f;
//            this.motionY *= (double)f;
//            this.motionZ *= (double)f;
//        }
//
//        this.prevLimbSwingAmount = this.limbSwingAmount;
//        double d1 = this.posX - this.prevPosX;
//        double d0 = this.posZ - this.prevPosZ;
//        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
//
//        if (f2 > 1.0F)
//        {
//            f2 = 1.0F;
//        }
//
//        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
//        this.limbSwing += this.limbSwingAmount;
//    }
	 public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_)
	    {
	        if (this.isInWater())
	        {
	            this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, 0.02F);
	            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	            this.motionX *= 0.800000011920929D;
	            this.motionY *= 0.800000011920929D;
	            this.motionZ *= 0.800000011920929D;
	        }
	        else if (this.isInLava())
	        {
	            this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, 0.02F);
	            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	            this.motionX *= 0.5D;
	            this.motionY *= 0.5D;
	            this.motionZ *= 0.5D;
	        }
	        else
	        {
	            float f = 0.91F;

	            if (this.onGround)
	            {
	                f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
	            }

	            float f1 = 0.16277136F / (f * f * f);
	            this.moveRelative(p_191986_1_, p_191986_2_, p_191986_3_, this.onGround ? 0.1F * f1 : 0.02F);
	            f = 0.91F;

            if (this.onGround)
            {
                f = this.worldObj.getBlockState(new BlockPos(
                		MathHelper.floor_double(this.posX), 
                		MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, 
                		MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }

	            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	            this.motionX *= (double)f;
	            this.motionY *= (double)f;
	            this.motionZ *= (double)f;
	        }

	        this.prevLimbSwingAmount = this.limbSwingAmount;
	        double d1 = this.posX - this.prevPosX;
	        double d0 = this.posZ - this.prevPosZ;
	        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

	        if (f2 > 1.0F)
	        {
	            f2 = 1.0F;
	        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
        */
	   // }
	

	

	/* AI */
	
	@Override
	protected void initEntityAI() {
		// Clear any tasks assigned in super classes
		clearAITasks(); 
		
		// From Ghast
		//this.tasks.addTask(5, new AIRandomFly(this));
	    //    this.tasks.addTask(7, new EntityGhast.AILookAround(this));
	    //    this.tasks.addTask(7, new EntityGhast.AIFireballAttack(this));
	    //    this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	
	// This is a timer tick called each update cycle
	@Override
    protected void updateAITasks() {
		//System.out.println("ImperialProbe::updateAITasks entityLivingToAttack=" + this.getAITarget() + ", revengeTimer=" + this.getRevengeTimer() +
		//		", attackTarget=" + this.getAttackTarget());
		super.updateAITasks();
    }
	

	// Copied from Ghast
	static class AIRandomFly extends EntityAIBase
    {
        private final TieFighterEntity parentEntity;

        
        public AIRandomFly(TieFighterEntity tieFighter)
        {
            this.parentEntity = tieFighter;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
        	// If we are not currently moving somewhere, feel free to jump in.
            EntityMoveHelper mh = this.parentEntity.getMoveHelper();
            if (!mh.isUpdating()) {
                return true;
            }
           
			// Otherwise, if we are very close to the destination, or very far away, then feel free to kick in.
			double d0 = mh.getX() - this.parentEntity.posX;
			double d1 = mh.getY() - this.parentEntity.posY;
			double d2 = mh.getZ() - this.parentEntity.posZ;
			double dist = d0 * d0 + d1 * d1 + d2 * d2;
			return dist < 1.0D || dist > 3600.0D;
        }

        // Find a random spot from 1-16 blocks away on each axis, and start moving there.
        @Override
        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
        
        
         //We always execute for one tick to set the move instruction, then we can disengage.
         @Override
         public boolean shouldContinueExecuting() {
        	return false;
         }
    }

	static class MoveHelper extends EntityMoveHelper
    {
        private final TieFighterEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelper(TieFighterEntity tieFighter)
        {
            super(tieFighter);
            this.parentEntity = tieFighter;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - this.parentEntity.posX;
                double d1 = this.posY - this.parentEntity.posY;
                double d2 = this.posZ - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double)MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                    {
                        this.parentEntity.motionX += d0 / d3 * 0.1D;
                        this.parentEntity.motionY += d1 / d3 * 0.1D;
                        this.parentEntity.motionZ += d2 / d3 * 0.1D;
                    }
                    else
                    {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
        }

        /**
         * Checks if entity bounding box is not colliding with terrain
         */
        private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
}