package superdopesquad.superdopejedimod.entity;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;


public class SnakeEntity extends BaseEntityAnimal {
				
	
	public SnakeEntity(World worldIn) {
		
		super(worldIn, "snakeEntity", "Snake");

		this.setupAI();
		
		// Helpful documentation: 
		// http://jabelarminecraft.blogspot.com/p/minecraft-forge-172-finding-block.html
		// http://jabelarminecraft.blogspot.com/p/creating-custom-entities.html
		// http://greyminecraftcoder.blogspot.com/2015/07/entity-rotations-and-animation.html
		// Note: I do not yet know how to move the Y axis of his head, which would affect what he can see.  Current one is OK.
		this.setSize(1.0F, 0.5F);
		//this.setRotationYawHead(0.5F);
		//this.setRenderYawOffset(0.5F);
	}

	
	@Override
	public void registerEntityRender() {
			
		Class renderBaseClass = SnakeRender.class;
		Class modelBaseClass = SnakeModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, 1.0F);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
//	@Override
//	public void registerRecipe() {
//		
//		// Recipe for creating a Snake Egg.
//		ItemStack appleStack = new ItemStack(Items.APPLE);	
//		ItemStack eggStack = new ItemStack(Items.EGG);
//
//		GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.entityManager.snakeEgg, 1), 
//						"A", 
//						"B", 
//						'A', appleStack, 
//						'B', eggStack);
//	}
	
	
	// set up AI tasks
	protected void setupAI()
	{
	   //getNavigator().setAvoidsWater(true);
	   //getNavigator().
	   
	   clearAITasks(); // clear any tasks assigned in super classes
	   
	   //tasks.addTask(5, new net.minecraft.entity.ai.);
	   //tasks.addTask(0, new EntityAISwimming(this));
	   //tasks.addTask(1, new EntityAIPanicHerdAnimal(this));
	   // the leap and the collide together form an actual attack
	   //tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
	   //tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
	  // tasks.addTask(5, new EntityAIMate(this, 1.0D));
	   //tasks.addTask(6, new EntityAITempt(this, 1.25D, Items.wheat, false));
	   //tasks.addTask(7, new EntityAIFollowParent(this, 1.25D));
	  // tasks.addTask(8, new EntityAIWander(this, interpTargetPitch));
	   //tasks.addTask(8, new net.minecraft.entity.ai.EntityAIWander(this, 2.0F));
	   //tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F));
	   //tasks.addTask(10, new EntityAILookIdle(this));
	   //targetTasks.addTask(0, new EntityAIHurtByTargetHerdAnimal(this, true));      
	}

	
	protected void clearAITasks()
	{
	   tasks.taskEntries.clear();
	   targetTasks.taskEntries.clear();
	}


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	@Override
//	public boolean attackEntityAsMob(Entity entityTarget)
//	{
//		
//		System.out.println("DEBUGGING: Made it into EntityTuskanRaider:attackEntityAsMob(..)");
//		
//	    float attackDamage = (float)getEntityAttribute(SharedMonsterAttributes
//	          .ATTACK_DAMAGE).getAttributeValue();
//	    int knockbackModifier = 0;
//
////	    if (entityTarget instanceof EntityLivingBase)
////	    {
////	        attackDamage += EnchantmentHelper.getEnchantmentModifierLiving(this, 
////	              (EntityLivingBase)entityTarget);
////	        knockbackModifier  += EnchantmentHelper.getKnockbackModifier(this, 
////	              (EntityLivingBase)entityTarget);
////	    }
//
//	    boolean isTargetHurt = entityTarget.attackEntityFrom(DamageSource
//	          .causeMobDamage(this), attackDamage);
//
//	    //if (wasDamageDone)
//	    if (true) {
//	   // {
//	        if (knockbackModifier  > 0)
//	        {
//	            entityTarget.addVelocity((double)(-MathHelper.sin(rotationYaw * 
//	                  (float)Math.PI / 180.0F) * (float)knockbackModifier  * 0.5F), 
//	                   0.1D, (double)(MathHelper.cos(rotationYaw * 
//	                  (float)Math.PI / 180.0F) * (float)knockbackModifier  * 0.5F));
//	            motionX *= 0.6D;
//	            motionZ *= 0.6D;
//	        }
//
//	        int fireModifier = EnchantmentHelper.getFireAspectModifier(this);
//
//	        if (fireModifier > 0)
//	        {
//	            entityTarget.setFire(fireModifier * 4);
//	        }
//
////	        // I copied these enchantments from EntityMob, not sure what they do
////	        if (entityTarget instanceof EntityLivingBase)
////	        {
////	            EnchantmentHelper.func_151384_a((EntityLivingBase)entityTarget, this);
////	        }
////
////	        EnchantmentHelper.func_151385_b(this, entityTarget);
//	    }
//
//	    return isTargetHurt ;
//	}
	
	
//	@Override
//	public boolean attackEntityFrom(DamageSource damageSource, float amount)
//	{
//	
//		System.out.println("DEBUGGING: damageType:" + damageSource.damageType);
////		System.out.println("DEBUGGING: damageSource:" + damageSource.getSourceOfDamage().getName());
////	
////		//System.out.println("DEBUGGING: Made it into EntityTuskanRaider:attackEntityFrom(..): damageType:" + damageSource.damageType + ", damageSource:" + damageSource.getSourceOfDamage().getName());
////		
////		
////	    if (isEntityInvulnerable(damageSource))
////	    {
////	        return false;
////	    }
////	   // else
////	   // {
//	    	
//	        //aisit.setSitting(false);
//	        return super.attackEntityFrom(damageSource, amount);
//	   // }
//	}
	
	
//	@Override
//	public boolean isEntityInvulnerable(DamageSource damageSource) {
//		
//		if (damageSource.damageType == "inWall") {
//			return true;
//		}
//			
//		return super.isEntityInvulnerable(damageSource);
//	}	
}