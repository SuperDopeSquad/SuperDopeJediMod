package superdopesquad.superdopejedimod.entity;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class GenericEntityEgg extends EntityEgg {

	
	private Class _classToMake;
	
	
	public GenericEntityEgg(World worldIn) {
		super(worldIn);
	}
	
	
	public GenericEntityEgg(World worldIn, EntityLivingBase throwerIn) {
	    super(worldIn, throwerIn);
	}

	
	public GenericEntityEgg(World worldIn, double x, double y, double z) {
	    super(worldIn, x, y, z);
	}

	
	public void setClassToMake(Class classToMake) {
		this._classToMake = classToMake;
	}
	
	 /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote)
        {

        	BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
        	EntityAnimal entity = (EntityAnimal) EntityManager.createEntity(_classToMake, world, blockPos);
        	//entity.setGrowingAge(-24000); // this is used to make a baby version.
            //entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            //this.worldObj.spawnEntityInWorld(entity);
        
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
