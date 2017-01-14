package superdopesquad.superdopejedimod.entity;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class JawaEntityEgg extends EntityEgg {

	
	public JawaEntityEgg(World worldIn) {
		super(worldIn);
	}
	
	
	public JawaEntityEgg(World worldIn, EntityLivingBase throwerIn) {
	    super(worldIn, throwerIn);
	}

	
	public JawaEntityEgg(World worldIn, double x, double y, double z) {
	    super(worldIn, x, y, z);
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

        if (!this.worldObj.isRemote)
        {
//            if (this.rand.nextInt(8) == 0)
//            {
//                int i = 1;
//
//                if (this.rand.nextInt(32) == 0)
//                {
//                    i = 4;
//                }
//
//                for (int j = 0; j < i; ++j)
//                {
//                    EntityChicken entitychicken = new EntityChicken(this.worldObj);
//                    entitychicken.setGrowingAge(-24000);
//                    entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
//                    this.worldObj.spawnEntityInWorld(entitychicken);
//                }
//            }
        	
//        	if (this.rand.nextInt(8) == 0)
//            {
//                int i = 1;
//
//                if (this.rand.nextInt(32) == 0)
//                {
//                    i = 4;
//                }
//
//                for (int j = 0; j < i; ++j)
//                {
                    JawaEntity jawaentity = new JawaEntity(this.worldObj);
                    jawaentity.setGrowingAge(-24000);
                    jawaentity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    this.worldObj.spawnEntityInWorld(jawaentity);
          //      }
          //  }

            this.worldObj.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
