package superdopesquad.superdopejedimod.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WeaponManager {

	 // Projectile entities.
    public static PlasmaShotItem plasmaShotItemBlue = new PlasmaShotItem("plasmaShotItemBlue");
    public static PlasmaShotItem plasmaShotItemRed = new PlasmaShotItem("plasmaShotItemRed");
    public static PlasmaShotEntity plasmaFireEntity = new PlasmaShotEntity(null);
	
	
    public WeaponManager() {}
	
	
	
	public void ThrowPlasmaShot(World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {
		    	
		//System.out.println("DEBUG: inside ThrowPlasmaShot");
		         	
		EntityThrowable entityToThrow  = new PlasmaShotEntity(world, thrower, damageAmount);
		        
		double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;      
		double d1 = target.posX - thrower.posX;      
		double d2 = d0 - entityToThrow.posY;     
		double d3 = target.posZ - thrower.posZ;      
		float f = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
      
		entityToThrow.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
		        
		//this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		       
		boolean success = world.spawnEntityInWorld(entityToThrow);
		if (!success) {
			System.out.println("Failed to spawn a PlasmaShotEntity!");
		}
	}
}
