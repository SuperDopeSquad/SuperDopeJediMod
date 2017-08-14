package superdopesquad.superdopejedimod.weapon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.weapon.GaffiStick;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.droid.DroidParts;
import superdopesquad.superdopejedimod.faction.ClassInfo;
import superdopesquad.superdopejedimod.faction.ClassManager;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class WeaponManager {
	
	// BlasterParts is a basic build block for blasters and other weapons.
	public static BlasterParts blasterParts = new BlasterParts("blasterParts");

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
    // public static Blaster blaster = new Blaster("blaster");
    // public static BossBlaster bossBlaster = new BossBlaster("bossBlaster");
 
    // Miscellaneous hand-held weapons.
    public static GaffiStick gaffiStick = new GaffiStick("gaffiStick");  
  

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

    
    public void ThrowPlasmaShot(World world, EntityLivingBase thrower, FactionInfo throwerFactionInfo, EntityLivingBase target, float distanceFactor, float damageAmount) {

    	EntityThrowable entityThrowable = this.createPlasmaShotEntity(world, thrower, throwerFactionInfo, damageAmount);
    	this.ThrowSomething(entityThrowable, world, thrower, target, distanceFactor, damageAmount);
    }
    
    
    public void ThrowPlasmaShotBlue(World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {

    	this.ThrowPlasmaShot(world, thrower, SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_REPUBLIC), target, distanceFactor, damageAmount);
    }
    
	
    public void ThrowPlasmaShotRed(World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {

       	this.ThrowPlasmaShot(world, thrower, SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_EMPIRE), target, distanceFactor, damageAmount);
    }
    
    
    public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }
    
    
    private PlasmaShotEntityBase createPlasmaShotEntity(World world, EntityLivingBase thrower, FactionInfo throwerFactionInfo, float damageAmount) {
     
 	   // Create an entity based on factioninfo.
 	   if ((throwerFactionInfo == null) || (throwerFactionInfo.getId() == SuperDopeJediMod.classManager.FACTION_REPUBLIC)) {
 		   return new PlasmaShotEntityBlue(world, thrower, damageAmount);
 	   }
 	   else {
 		  return new PlasmaShotEntityRed(world, thrower, damageAmount);
 	   }
    }
 
    
    private PlasmaShotEntityBase createPlasmaShotEntity(World world, EntityPlayer thrower, float damageAmount) {
    	
    	// What color should we be using?
 	   ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(thrower);
 	   FactionInfo factionInfo = classInfo.getFaction();
 	   
 	   return this.createPlasmaShotEntity(world, thrower, factionInfo, damageAmount);
    }
    
    
    public void ThrowPlasmaShotAtDirection(World world, EntityPlayer thrower, float damageAmount, int timeLeft) {
   
	    //int i = this.getMaxItemUseDuration(null) - timeLeft;
    	int i = 72000 - timeLeft;
    	float f = getArrowVelocity(i);
	    
       PlasmaShotEntityBase plasmaShotEntity =  this.createPlasmaShotEntity(world, thrower, damageAmount); 
       plasmaShotEntity.setAim(thrower, thrower.rotationPitch, thrower.rotationYaw, 0.0F, f * 3.0F, 1.0F);

       //if (f == 1.0F)
       //{
       //    entityarrow.setIsCritical(true);
       //}

       int j = 1; // EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

       if (j > 0) {
    	   plasmaShotEntity.setDamage(plasmaShotEntity.getDamage() + (double)j * 0.5D + 0.5D);
       }

       //System.out.println("damageAmount: " + plasmaShotEntity.getDamage());
       
      // int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

//       if (k > 0)
//       {
//           entityarrow.setKnockbackStrength(k);
//       }

//       if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
//       {
//           entityarrow.setFire(100);
//       }

      // stack.damageItem(1, entityplayer);

//       if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
//       {
//           entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
//       }

       //world.spawnEntityInWorld(plasmaShotEntity);
       world.spawnEntity(plasmaShotEntity);
    }
    
    
	private void ThrowSomething(EntityThrowable entityThrowable, World world, EntityLivingBase thrower, EntityLivingBase target, float distanceFactor, float damageAmount) {
		    	
		//System.out.println("DEBUG: inside ThrowPlasmaShot");
		         	
		// Some complicated math to figure out what direction to throw.   
		double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;      
		double d1 = target.posX - thrower.posX;      
		double d2 = d0 - entityThrowable.posY;     
		double d3 = target.posZ - thrower.posZ;      
		float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
		entityThrowable.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
		        
		//this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		       
		// Actually throw!
//		boolean success = world.spawnEntityInWorld(entityThrowable);
		boolean success = world.spawnEntity(entityThrowable);

		if (!success) {
			System.out.println("Failed to spawn an EntityThrowable!");
		}
	}
}
