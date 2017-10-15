package superdopesquad.superdopejedimod.weapon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;
import superdopesquad.superdopejedimod.entity.JawaModel;
import superdopesquad.superdopejedimod.entity.JawaRender;
import superdopesquad.superdopejedimod.entity.SuperDopeRenderManager;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;

	
public abstract class PlasmaShotEntityBase extends BaseEntityProjectile {
	
	public enum PowerLevel {
	  STANDARD(2.0D, 1.6D, 12.0D),
	  RIFLE(4.0D, 1.6D, 12.0D),
	  CANNON(6.0D, 1.6D, 12.0D),
	  HEAVY(25.0D, 5.0D, 5.0D);
	  
	  private final double damage;
	  private final double velocity;
	  private final double accuracy;
	  
	  public double damage() { return damage; }
	  public double velocity() { return velocity; }
	  public double accuracy() { return accuracy; }
	  
	  PowerLevel(double damage, double velocity, double accuracy) {
		  this.damage = damage;
		  this.velocity = velocity;
		  this.accuracy = accuracy;
	  }
	}
      
	// Instance Members
	private PowerLevel _powerLevel;
	
	
	/**
	 * constructor
	 */
	public PlasmaShotEntityBase(World worldIn) {
		super(worldIn, (float) PowerLevel.STANDARD.damage());
		this._powerLevel = PowerLevel.STANDARD;
	}

	/**
	 * constructor
	 */
	public PlasmaShotEntityBase(World worldIn, EntityLivingBase throwerIn, PowerLevel pl) {
		super(worldIn, throwerIn, (float) pl.damage());
		this._powerLevel = pl;
	}
   
    @Override
	public String getName() {
		return "plasmaShotEntity";
	}

	    
    @SideOnly(Side.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
    	
    	//System.out.println("inside handleStatusUpdate: " + id);
    	
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

	 
    protected void onImpact(RayTraceResult result) {
    	 
    	// Bail on this 'impact' if it is with the thrower.
    	if ((result.entityHit != null) && (result.entityHit.isEntityEqual(this.getThrower()))) {
    		//System.out.println("Inside onImpact: bailing, entityHit == this.getThrower()");
    		return;
    	}
    	
//	    	// Debug info.
//	    	BlockPos blockPos = result.getBlockPos();
//	    	Object hitInfo = result.hitInfo;
//	    	String typeOfHit = result.typeOfHit.name();
//	    	EnumFacing sideHit = result.sideHit;
//	    	int subHit = result.subHit;
//	    	System.out.println("Inside onImpact:" + result.toString());
//	    	System.out.println("Inside onImpact: typeOfHit:" + typeOfHit.toString());
//	    	if (sideHit != null) {
//	    		System.out.println("Inside onImpact: sideHit:" + sideHit.getName());
//	    	}
//	    	if (blockPos != null) {
//	    		System.out.println("blockPos:" + blockPos.toString());
//	    	}
//	    	System.out.println("Inside onImpact: subHit:" + subHit);
//	    	if (hitInfo != null) {
//	    		System.out.println("hitInfo:" + hitInfo.toString());
//	    	}
    	
        // If we are on the server, update state information to kill this projectile.
        if (!this.world.isRemote)
        {       	
        	// if we hit an entity, let's do some damage.
	        if (result.entityHit != null) {
		    	//System.out.println("Inside onImpact: entityHit:" + result.entityHit.toString());
	            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) this.getDamage());
	        }
        	
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
	    
	    
    public void setAim(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setThrowableHeading((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround) {
        	
            this.motionY += shooter.motionY;
        }
    }
}
