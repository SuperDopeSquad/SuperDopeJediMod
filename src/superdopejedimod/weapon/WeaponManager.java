package superdopesquad.superdopejedimod.weapon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class WeaponManager {

	 // Projectile items that are rendered later by the projectile entities..
    public static PlasmaShotItem plasmaShotItemBlue = new PlasmaShotItem("plasmaShotItemBlue");
    public static PlasmaShotItem plasmaShotItemRed = new PlasmaShotItem("plasmaShotItemRed");
    
    // Projectile entities should be created here, to trigger registration properly.
    public static PlasmaShotEntityBlue plasmaShotEntityBlue = new PlasmaShotEntityBlue(null);
    public static PlasmaShotEntityRed plasmaShotEntityRed = new PlasmaShotEntityRed(null);
	
    // Blaster weapons.
    public static BlasterCarbine blasterCarbine = new BlasterCarbine("blasterCarbine");
    public static BlasterRifle blasterRifle = new BlasterRifle("blasterRifle");
    public static BlasterPistol blasterPistol = new BlasterPistol ("blasterPistol");
    public static BlasterSniper blasterSniper = new BlasterSniper("blasterSniper");
    public static BlasterCannon blasterCannon = new BlasterCannon("blasterCannon");
    public static RocketLauncher rocketLauncher = new RocketLauncher("rocketLauncher");
	
    // earlier stuff.
    public static Blaster blaster = new Blaster("blaster");
    public static BossBlaster bossBlaster = new BossBlaster("bossBlaster");
 
    public WeaponManager() {}
	
    
    public void onInit() {
    	    	
    	// Handles to some render objects we need.
    	RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
    	RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    	
    	// Register red plasma shot.
    	PlasmaShotRender plasmaShotRenderRed = new PlasmaShotRender(renderManager, SuperDopeJediMod.weaponManager.plasmaShotItemRed, renderItem);
    	Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(PlasmaShotEntityRed.class, plasmaShotRenderRed);
    
    	// Register red plasma shot.
    	PlasmaShotRender plasmaShotRenderBlue = new PlasmaShotRender(renderManager, SuperDopeJediMod.weaponManager.plasmaShotItemBlue, renderItem);
    	Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(PlasmaShotEntityBlue.class, plasmaShotRenderBlue);
    	
    	// TEST CODE.
     	//System.out.println((Minecraft.getMinecraft() == null));
    	//System.out.println((Minecraft.getMinecraft().getRenderManager() == null));
    	//System.out.println((Minecraft.getMinecraft().getRenderItem() == null));
    	//System.out.println((Minecraft.getMinecraft().getRenderManager().entityRenderMap == null));
		//Object o = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(PlasmaShotEntity.class);
		//System.out.println("DEBUG: " + o.getClass().getName());
    }

    
    public void ThrowPlasmaShotBlue(World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {

    	EntityThrowable entityThrowable  = new PlasmaShotEntityBlue(world, thrower, damageAmount);
    	this.ThrowSomething(entityThrowable, world, thrower, target, distanceFactor, damageAmount);
    }
    
	
    public void ThrowPlasmaShotRed(World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {

    	EntityThrowable entityThrowable  = new PlasmaShotEntityRed(world, thrower, damageAmount);
    	this.ThrowSomething(entityThrowable, world, thrower, target, distanceFactor, damageAmount);
    }
    
    
	private void ThrowSomething(EntityThrowable entityThrowable, World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {
		    	
		//System.out.println("DEBUG: inside ThrowPlasmaShot");
		         	
		// Some complicated math to figure out what direction to throw.   
		double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;      
		double d1 = target.posX - thrower.posX;      
		double d2 = d0 - entityThrowable.posY;     
		double d3 = target.posZ - thrower.posZ;      
		float f = MathHelper.sqrt_double(d1 * d1 + d3 * d3) * 0.2F;
		entityThrowable.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
		        
		//this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		       
		// Actually throw!
		boolean success = world.spawnEntityInWorld(entityThrowable);
		if (!success) {
			System.out.println("Failed to spawn an EntityThrowable!");
		}
	}
}
