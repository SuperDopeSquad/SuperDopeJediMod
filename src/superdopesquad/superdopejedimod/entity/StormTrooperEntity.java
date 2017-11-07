package superdopesquad.superdopejedimod.entity;

import java.util.Random;
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
import net.minecraft.entity.ai.EntityAIBase;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
import superdopesquad.superdopejedimod.entity.ai.EntityAIEnemyFactionDetector;
import superdopesquad.superdopejedimod.entity.ai.EntityAIHoldAndShoot;
import superdopesquad.superdopejedimod.entity.ai.EntityAIQuicklyOffended;
import superdopesquad.superdopejedimod.faction.ClassInfo;
import superdopesquad.superdopejedimod.faction.ClassManager;
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;


public class StormTrooperEntity extends BaseEntityTameable implements IRangedAttackMob {
		
	protected double movementSpeed;
		
	public StormTrooperEntity(World worldIn) {
		
		super(worldIn, "stormTrooperEntity", "Storm Trooper");

		this.setupAI();
		
		this.setSize(1.0F, 0.5F);
		
		// how much experience do you get it you kill it?
		this.experienceValue = 5;
				
		// Properties that we need to have later.
		this.shadowSize = 1.0F;
		
		this.movementSpeed = 1.0;
				
		// Put a iron axe in his mainhand slot.
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(SuperDopeJediMod.weaponManager.blasterRifle));
	}
	
	
	@Override
	public void registerEntityRender() {
				
		Class renderBaseClass = StormTrooperRender.class;
		Class modelBaseClass = StormTrooperModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, 1.0F);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	@Override
	public void registerRecipe() {
		
		// Recipe for creating a Storm Trooper Egg.
		ItemStack quartzStack = new ItemStack(Items.QUARTZ);	
		ItemStack eggStack = new ItemStack(Items.EGG);

		GameRegistry.addShapedRecipe(new ResourceLocation(this.getFullName()), null, new ItemStack(SuperDopeJediMod.entityManager.stormTrooperEgg, 1), 
						"A", 
						"B", 
						'A', quartzStack, 
						'B', eggStack);
	}
	
	
	// set up AI tasks
	protected void setupAI()
	{
	   clearAITasks(); // clear any tasks assigned in super classes
	   
	   // Set up the ClassInfo array that defines who Wookies attack.
//<<<<<<< HEAD
//	   ClassInfo[] classes = new ClassInfo[3];
//	   classes[0] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.JEDI);
//	   classes[1] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.SMUGGLER);
//	   classes[2] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.REPUBLICPILOT);
//=======
	   //ClassInfo[] classes = new ClassInfo[3];
	   //classes[0] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.JEDI);
	   //classes[1] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.SMUGGLER);
	   //classes[2] = SuperDopeJediMod.classManager.getClassInfo(SuperDopeJediMod.classManager.REPUBLICPILOT);
	   FactionInfo factionInfo = SuperDopeJediMod.classManager.getFactionInfo(SuperDopeJediMod.classManager.FACTION_REPUBLIC);
//>>>>>>> origin/master
	   
	   this.targetTasks.addTask(1, new EntityAIQuicklyOffended(this, true, false, 
				SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_EMPIRE)));  
		this.targetTasks.addTask(2, new EntityAIEnemyFactionDetector(this, true, false, 16.0F, 
				SuperDopeJediMod.classManager.getFactionInfo(ClassManager.FACTION_REPUBLIC))); 
	   
	   // Priority 1: If you see someone from the Empire, shoot to kill.
	   this.tasks.addTask(0, new EntityAIHoldAndShoot(this, 10, 16.0F));	// Once enemy is identified, hold position and shoot until it goes out of range.
	   this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 0.02F));
	   this.tasks.addTask(0, new EntityAISwimming(this)); 
	   // Main AI task list.
	   //this.tasks.addTask(1, new EntityAIAttackMeleeClassAware(this, 1.0, false, classes));
	   //this.tasks.addTask(1, new EntityAIAttackMeleeFactionAware(this, 1.0, false, factionInfo));
	   // tasks.addTask(5, new EntityAIMate(this, 1.0D)); We don't need these guys mating.
	   //this.tasks.addTask(7, new EntityAIFollowParent(this, 1.25D));
//	   this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
	  
	   // Set up the targetTasks list, which defines who the entity focuses his actions on.
	   // Priority 0: attack anything that attacked me.
	   this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));   
	   // Priority 1: attack the nearest player I can find.
	   this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void generateSurface(World world, Random random, int i, int j) {
				
		// Because BaseEntityAnimal's will only spawn where there is no light, it means they will only spawn 
		// at night, and away from player homes where they have torches.  The weightedProbability below is 
		// the percentage chance after taking that into account, so it should be a high number.
		
		//System.out.println("Inside generateSurface for Wookies");
		
		Class entityClass = StormTrooperEntity.class;
		int weightedProbability = 10;
		int minimumSpawnCount = 4;
		int maximumSpawnCount = 8;
		EnumCreatureType creatureType = EnumCreatureType.MONSTER;
		
		// add the spawn information to EntityRegistry through the addSpawn call.
		EntityRegistry.addSpawn(entityClass, weightedProbability, minimumSpawnCount, maximumSpawnCount, creatureType,
				Biomes.BEACH,
				Biomes.BIRCH_FOREST,
				Biomes.BIRCH_FOREST_HILLS,
				Biomes.COLD_BEACH,
				Biomes.COLD_TAIGA,
				Biomes.COLD_TAIGA_HILLS,
				Biomes.DESERT,
				Biomes.DESERT_HILLS,
				Biomes.EXTREME_HILLS,
				Biomes.EXTREME_HILLS_EDGE,
				Biomes.EXTREME_HILLS_WITH_TREES,
				Biomes.FOREST,
				Biomes.FOREST_HILLS,
				Biomes.ICE_MOUNTAINS,
				Biomes.ICE_PLAINS,
				Biomes.JUNGLE,
				Biomes.JUNGLE_EDGE,
				Biomes.JUNGLE_HILLS,
				Biomes.MESA,
				Biomes.MESA_CLEAR_ROCK,
				Biomes.MESA_ROCK,
				Biomes.MUSHROOM_ISLAND,
				Biomes.MUSHROOM_ISLAND_SHORE,
				Biomes.PLAINS,
				Biomes.REDWOOD_TAIGA,
				Biomes.REDWOOD_TAIGA_HILLS,
				Biomes.ROOFED_FOREST,
				Biomes.SAVANNA,
				Biomes.SAVANNA_PLATEAU,
				Biomes.STONE_BEACH,
				Biomes.SWAMPLAND,
				Biomes.TAIGA,
				Biomes.TAIGA_HILLS);
	}
	
	
	@Override
	protected void applyEntityAttributes() {
		
	    super.applyEntityAttributes(); 

	    // standard attributes registered to EntityLivingBase
	   getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	   getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	   getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
	   getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);

	    // need to register any additional attributes
	   getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	   getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
	
	
	// After it dies, what equipment should it drop?
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		this.entityDropItem(new ItemStack(Items.QUARTZ), 0);
    }


	@Override // for IRangedAttackMob
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
    	System.out.println("Storm Trooper: attacking with ranged!");
    	SuperDopeJediMod.weaponManager.ThrowPlasmaShotRed(world, this, target, PowerLevel.STANDARD);
    }


	@Override // for IRangedAttackMob
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
	}
}