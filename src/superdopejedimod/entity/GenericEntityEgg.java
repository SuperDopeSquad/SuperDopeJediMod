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

        if (!this.worldObj.isRemote)
        {

        	EntityAnimal entity = (EntityAnimal) this.createEntity();
            entity.setGrowingAge(-24000);
            entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.spawnEntityInWorld(entity);
        
            this.worldObj.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
    
    
    private Object createEntity() {
    	
    	// Now, on to the Render object;
    	// Create an array that defines the type for each parameter.  That is how we
    	// query for the constructor (we get back the one that matches the parameters we request). 
    	Class parameterTypes[] = new Class[1];
    	parameterTypes[0] = World.class;
    	//parameterTypes[1] = ModelBase.class;
    	//parameterTypes[2] = float.class;
    	
    	// OK, let's query for that render constructor.
    	Constructor constructor = this.getConstructor(this._classToMake, parameterTypes);
    	if (constructor == null) {
    		return null;
    	}
    	
    	// OK, now we create an array that stores the actual parameter values for the render constructor.
    	Object parameterValues[] = new Object[1];
    	parameterValues[0] = this.worldObj;
    	//parameterValues[1] = modelInstance;
    	//parameterValues[2] = this._shadowSize;
    	
    	// Last step, let's call the constructor with the array of parameter values.
    	Object instance = this.newInstance(constructor, parameterValues);
    	if (instance == null) {
    		return null;
    	}
		
		// OK, let's return what we got.
    	return instance;
    }
    
    
   private Constructor getConstructor(Class classToMake, Class parameterTypes[]) {
    	
    	// Let's query for that constructor.
    	Constructor constructor = null;
		try {
			constructor = classToMake.getConstructor(parameterTypes);
		} catch (NoSuchMethodException e) {
			System.out.println("EntityRenderFactory: sent in a bogus renderClass: " + classToMake.getName());
			return null;
		} catch (SecurityException e) {
			System.out.println("EntityRenderFactory: weird security issue.  Bailing.");
			return null;
		}
		
		return constructor;
    }
    
    
    private Object newInstance(Constructor constructor, Object parameterValues[]) {
    
    	// Let's call the constructor with the array of parameter values.
    	Object newObject = null;
    	
		try {
			newObject = constructor.newInstance(parameterValues);
		}
		catch (InstantiationException e1) {
				System.out.println("EntityRenderFactory: weird instantiation issue.  Bailing.");
				return null;
		} catch (IllegalAccessException e1) {
				System.out.println("EntityRenderFactory: weird access issue.  Bailing.");
				return null;
		} catch (IllegalArgumentException e1) {
				System.out.println("EntityRenderFactory: weird argument issue.  Bailing.");
				return null;
		} catch (InvocationTargetException e1) {
				System.out.println("EntityRenderFactory: weird target issue.  Bailing.");
				return null;
		}
    	
		return newObject;
    }
}
