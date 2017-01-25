package superdopesquad.superdopejedimod.entity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.SuperDopeObjectGeneratable;


// Note:
// EntityCreature is an EntityLiving that has AI (it moves with path-finding and attacks). 
// EntityAgeable is an EntityCreature that can come in various sizes/ages. 
// EntityAnimal is an EntityAgeable that enables breeding by implementing IAnimal.
// EntityTameable is EntityAnimal that implement IEntityOwnable 
public abstract class BaseEntityAnimal extends EntityAnimal implements SuperDopeEntity, SuperDopeObjectGeneratable {

	
	private String _name = "";
	protected float shadowSize = 1.0F;
	
	
	public BaseEntityAnimal(World worldIn, String name) {
		
		super(worldIn);
		
		this._name = name;
				
		// Insert this object into our collection of custom objects, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	@Override
	public String getName() {
		
		return this._name;
	}
	
	
	public String getFullName() {
		
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	
	protected void clearAITasks()
	{
	   tasks.taskEntries.clear();
	   targetTasks.taskEntries.clear();
	}


	// you don't have to call this as it is called automatically during EntityLiving subclass creation
	@Override
	protected void applyEntityAttributes()
	{
	    super.applyEntityAttributes(); 

//	    // standard attributes registered to EntityLivingBase
//	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
//	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
//	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
//	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
//	   // MC-TODO: add interesting values for the other attributes.
//	   
//	    // need to register any additional attributes
//	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
//	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}
	
	
//	@Override
//	protected boolean isAIEnabled()
//	{
//	   return true;
//	}
	
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
	
	
	@Override
	public void registerObject() {
		
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		//System.out.println("registerObject's name: " + this.getName());
		//System.out.println("resourceLocation: " + resourceLocation.toString());
	  	EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
	};
	
	
	@Override
	public void registerRecipe() {
		return;
	}
	
	
	@Override
	public void registerModel() {
	    return;
	}
	
	
	@Override
	public void registerEntityRender() {
		return;
	}
	
	
	@Override
	public void generateEnd(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		return;
	}
	
	
	@Override
	public void generateNether(World world, Random random, int i, int j) {
		return;
	}
	
	
	// Copied from EntityMob.
		public boolean attackEntityAsMob(Entity entityIn) {
			
	        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
	        int i = 0;

	        if (entityIn instanceof EntityLivingBase)
	        {
	            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
	            i += EnchantmentHelper.getKnockbackModifier(this);
	        }

	        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

	        if (flag)
	        {
	            if (i > 0 && entityIn instanceof EntityLivingBase)
	            {
	                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
	                this.motionX *= 0.6D;
	                this.motionZ *= 0.6D;
	            }

	            int j = EnchantmentHelper.getFireAspectModifier(this);

	            if (j > 0)
	            {
	                entityIn.setFire(j * 4);
	            }

	            if (entityIn instanceof EntityPlayer)
	            {
	                EntityPlayer entityplayer = (EntityPlayer)entityIn;
	                ItemStack itemstack = this.getHeldItemMainhand();
	                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.field_190927_a;

	                if (!itemstack.func_190926_b() && !itemstack1.func_190926_b() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
	                {
	                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

	                    if (this.rand.nextFloat() < f1)
	                    {
	                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
	                        this.worldObj.setEntityState(entityplayer, (byte)30);
	                    }
	                }
	            }

	            this.applyEnchantments(this, entityIn);
	        }

	        return flag;
	    }
		
		
		// Copied from EntityMob.
		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
		        
			return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
		}
		
		 
	    // Entity won't drop items or experience points if this returns false
		@Override
	    protected boolean canDropLoot() {
	        
			return true;
	    }
}