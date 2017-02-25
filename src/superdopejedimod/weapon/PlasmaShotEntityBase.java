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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;
import superdopesquad.superdopejedimod.entity.JawaModel;
import superdopesquad.superdopejedimod.entity.JawaRender;
import superdopesquad.superdopejedimod.entity.SuperDopeRenderManager;

	
public abstract class PlasmaShotEntityBase extends BaseEntityProjectile {
	
	
	    public PlasmaShotEntityBase(World worldIn)
	    {
	        super(worldIn);
	    }

	    
	    public PlasmaShotEntityBase(World worldIn, EntityLivingBase throwerIn, float damageAmount)
	    {
	        super(worldIn, throwerIn, damageAmount);
	    }

	    
	    public PlasmaShotEntityBase(World worldIn, double x, double y, double z)
	    {
	        super(worldIn, x, y, z);
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
	                this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
	            }
	        }
	    }

	 
	    protected void onImpact(RayTraceResult result) {
	    	
	    	// if we hit an entity, let's do some damage.
	        if (result.entityHit != null) {
	        	
	            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)this._damageAmount);
	        }

	        // If we are on the server, update state information to kill this projectile.
	        if (!this.worldObj.isRemote)
	        {
	            this.worldObj.setEntityState(this, (byte)3);
	            this.setDead();
	        }
	    }
	}
	
