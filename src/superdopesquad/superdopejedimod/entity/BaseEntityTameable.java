package superdopesquad.superdopejedimod.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
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
public abstract class BaseEntityTameable extends EntityTameable implements SuperDopeEntity, SuperDopeObjectGeneratable {

	
	private String _name = "";
	private String _displayName = "";
	protected float shadowSize = 1.0F;
	
	
	public BaseEntityTameable(World worldIn, String name, String displayName) {
		
		super(worldIn);
		
		this._name = name;
		this._displayName = displayName;
				
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
	
	
	@Override
	public ITextComponent getDisplayName() {
		
        TextComponentString textcomponentstring = new TextComponentString(this._displayName);
        return textcomponentstring;
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

	    // standard attributes registered to EntityLivingBase
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
	
	
	
    public void registerItems(RegistryEvent.Register<Item> event) {
    	
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		//System.out.println("registerObject's name: " + this.getName());
		//System.out.println("resourceLocation: " + resourceLocation.toString());
	  	EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), 
	  			SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
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
		                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

		                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
		                {
		                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

		                    if (this.rand.nextFloat() < f1)
		                    {
		                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
		                        this.world.setEntityState(entityplayer, (byte)30);
		                    }
		                }
		            }

		            this.applyEnchantments(this, entityIn);
		        }

		        return flag;
		    }
		 
		 
//	// Copied from EntityMob.
//		public boolean attackEntityAsMob(Entity entityIn) {
//				
//			float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
//		    int i = 0;
//
//		        if (entityIn instanceof EntityLivingBase)
//		        {
//		            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
//		            i += EnchantmentHelper.getKnockbackModifier(this);
//		        }
//
//		        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
//
//		        if (flag)
//		        {
//		            if (i > 0 && entityIn instanceof EntityLivingBase)
//		            {
//		                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
//		                this.motionX *= 0.6D;
//		                this.motionZ *= 0.6D;
//		            }
//
//		            int j = EnchantmentHelper.getFireAspectModifier(this);
//
//		            if (j > 0)
//		            {
//		                entityIn.setFire(j * 4);
//		            }
//
//		            if (entityIn instanceof EntityPlayer)
//		            {
//		                EntityPlayer entityplayer = (EntityPlayer)entityIn;
//		                ItemStack itemstack = this.getHeldItemMainhand();
//		                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.field_190927_a;
//
//		                if (!itemstack.func_190926_b() && !itemstack1.func_190926_b() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
//		                {
//		                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
//
//		                    if (this.rand.nextFloat() < f1)
//		                    {
//		                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
//		                        this.worldObj.setEntityState(entityplayer, (byte)30);
//		                    }
//		                }
//		            }
//
//		            this.applyEnchantments(this, entityIn);
//		        }
//
//		        return flag;
//		    }
//			
			
//			// Copied from EntityMob.
//			/**
//		     * Checks to make sure the light is not too bright where the mob is spawning
//		     */
//		    protected boolean isValidLightLevel()
//		    {
//		        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
//
//		        if (this.worldObj.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
//		        {
//		            return false;
//		        }
//		        else
//		        {
//		            int i = this.worldObj.getLightFromNeighbors(blockpos);
//
//		            if (this.worldObj.isThundering())
//		            {
//		                int j = this.worldObj.getSkylightSubtracted();
//		                this.worldObj.setSkylightSubtracted(10);
//		                i = this.worldObj.getLightFromNeighbors(blockpos);
//		                this.worldObj.setSkylightSubtracted(j);
//		            }
//
//		            return i <= this.rand.nextInt(8);
//		        }
//		    }
//			
		    
			// Copied from EntityMob.
			 /**
		     * Checks if the entity's current position is a valid location to spawn this entity.
		     */
			@Override
		    public boolean getCanSpawnHere()
		    {
		        //return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
				
				return ((this.isValidLightLevel()) && (super.getCanSpawnHere()));
				//return super.getCanSpawnHere();
		    }
			
			
			// Copied from EntityMob.
			@Override
			public boolean attackEntityFrom(DamageSource source, float amount) {
			        
				return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
				//return true;
			}
			
			 
		    // Entity won't drop items or experience points if this returns false
			@Override
		    protected boolean canDropLoot() {
		        
				return true;
		    }

	
	@Override
	protected boolean canDespawn() {
		
		return true;
	}
	
	// Copied from EntityMob.
	/**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int i = this.world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering())
            {
                int j = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                i = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }
}