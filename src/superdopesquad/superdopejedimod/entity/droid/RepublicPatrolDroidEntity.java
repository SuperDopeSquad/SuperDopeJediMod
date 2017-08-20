package superdopesquad.superdopejedimod.entity.droid;


import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;


public class RepublicPatrolDroidEntity extends RepublicBaseDroidEntity {
		
	
	public RepublicPatrolDroidEntity(World worldIn) {
		
		super(worldIn, "republicPatrolDroidEntity", "Republic Patrol Droid");
				
		// This sets the bounding box size, not the actual model that you see rendered.
		this.setSize(1.0F, 2.0F);
				
		this.movementSpeed = 1F;
	}
	
	
	@Override
	public void registerEntityRender() {
			
		Class renderBaseClass = RepublicPatrolDroidRender.class;
		Class modelBaseClass = RepublicPatrolDroidModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, this.shadowSize);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	// set up AI tasks
	@Override
	protected void setupAI() {
		
		super.setupAI();
		
		this.tasks.addTask(8, new EntityAIWander(this, this.movementSpeed));
	}

	
	// After it dies, what equipment should it drop?
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		
		super.dropEquipment(wasRecentlyHit, lootingModifier);
		
		this.entityDropItem(new ItemStack(SuperDopeJediMod.entityManager.droidKit), 0);
		this.entityDropItem(new ItemStack(SuperDopeJediMod.entityManager.republicPatrolDroidHead), 0);
    }


	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
}