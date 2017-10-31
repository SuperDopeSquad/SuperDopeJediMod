package superdopesquad.superdopejedimod.entity.droid;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
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
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;
import superdopesquad.superdopejedimod.entity.EntityRenderFactory;
import superdopesquad.superdopejedimod.entity.ai.EntityAIEnemyFactionDetector;
import superdopesquad.superdopejedimod.entity.ai.EntityAIFollowPathMarkers;
import superdopesquad.superdopejedimod.entity.ai.EntityAIHoldAndShoot;
import superdopesquad.superdopejedimod.entity.ai.EntityAIQuicklyOffended;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;
import superdopesquad.superdopejedimod.faction.ClassManager;



public class ImperialProbeDroidEntity extends BaseEntityAnimal implements IRangedAttackMob {
				
	
	public ImperialProbeDroidEntity(World worldIn) {
		
		super(worldIn, "imperialProbeDroidEntity", "Imperial Probe Droid");

		this.setupAI();
		
		// This sets the bounding box size, not the actual model that you see rendered.
		this.setSize(0.6F, 2.0F);
		
		// how much experience do you get it you kill it?
		this.experienceValue = 1;
		
		// Properties that we need to have later.
		this.shadowSize = 0.5F;
	}
	
	
	@Override
	public void registerEntityRender() {
		
		Class renderBaseClass = ImperialProbeDroidRender.class;
		Class modelBaseClass = ImperialProbeDroidModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, this.shadowSize);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	@Override
	public void registerRecipe() {
		ItemStack stack1 = new ItemStack(Items.IRON_INGOT);	
		ItemStack stack2 = new ItemStack(Items.REDSTONE);
		GameRegistry.addShapedRecipe(new ResourceLocation(this.getFullName()), null, new ItemStack(SuperDopeJediMod.entityManager.imperialProbeDroidEgg, 1), 
			"xx ", " y ", " xx", 
			'x', stack1, 'y', stack2);
	}
	
	
	// set up AI tasks
	protected void setupAI()
	{
		// Clear any tasks assigned in super classes
		clearAITasks(); 
	   
		// targetTasks sets the "target" member of "this", which other tasks then use. 
		// Currently, the probe should only attack people that hurt it. Note that it will also alert other probes that are nearby to help attack.
		this.targetTasks.addTask(1, new EntityAIQuicklyOffended(this, true, false, 
				SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_EMPIRE)));  
		this.targetTasks.addTask(2, new EntityAIEnemyFactionDetector(this, true, false, 16.0F, 
				SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_REPUBLIC)));  
		
		 // Main AI task list.
		this.tasks.addTask(0, new EntityAISwimming(this)); // I think this prevents drowning.
		this.tasks.addTask(0, new EntityAIHoldAndShoot(this, 10, 16.0F));	// Once enemy is identified, hold position and shoot until it goes out of range.
		this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 0.02F)); // Turn the head to look at nearest entity.
		
		// Our main job is to follow the markers around.
		List<Block> tokenMarker = Arrays.asList(Blocks.REDSTONE_TORCH, Blocks.OBSIDIAN, Blocks.STONE);
		this.tasks.addTask(8, new EntityAIFollowPathMarkers(this, 1.0D, 20, tokenMarker));
	}
	
	
	// This is a timer tick called each update cycle
	@Override
    protected void updateAITasks()
    {
		//System.out.println("ImperialProbe::updateAITasks entityLivingToAttack=" + this.getAITarget() + ", revengeTimer=" + this.getRevengeTimer() +
		//		", attackTarget=" + this.getAttackTarget());
		super.updateAITasks();
    }
	
	@Override
    public void onLivingUpdate()
    {
		//System.out.println("ImperialProbe::onLivingUpdate this=" + this + ", entityLivingToAttack=" + this.getAITarget() + ", revengeTimer=" + this.getRevengeTimer() +
		//		", attackTarget=" + this.getAttackTarget());
        super.onLivingUpdate();
    }
	
    /**
     * Attack the specified entity using a ranged attack.
     *  
     * @param distanceFactor How far the target is, normalized and clamped between 0.1 and 1.0
     */
	@Override // for IRangedAttackMob
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
    	//System.out.println("ImperialProbe: attacking with ranged!");
    	SuperDopeJediMod.weaponManager.ThrowPlasmaShotRed(world, this, target, PowerLevel.STANDARD);
    }
    

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
	
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
		/* We do not auto-spawn anywhere. */
	}
	
	
	@Override
	protected void applyEntityAttributes() {
	   super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);	
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);

	   // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}
	
	// After it dies, what equipment should it drop?
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		this.entityDropItem(new ItemStack(Items.REDSTONE), 0);
	}
	
	// Don't despawn if no players are anywhere near us.
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	
	@Override // for IRangedAttackMob
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}
}