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
public class XWingFighterEntity extends BaseEntityShip {	
	
	 private static final float DEGREES2RADIANS = 0.017453292F;
	 
	/**
	 * constructor
	 */
	public XWingFighterEntity(World worldIn) {
		super(worldIn, "xWingFighterEntity", "X-Wing Fighter", XWingFighterRender.class, XWingFighterModel.class);
		
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
}