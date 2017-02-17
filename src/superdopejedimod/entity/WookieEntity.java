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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import superdopesquad.superdopejedimod.faction.FactionInfo;
import superdopesquad.superdopejedimod.faction.FactionManager;


public class WookieEntity extends BaseEntityTameable {
		
		
	public WookieEntity(World worldIn) {
		
		super(worldIn, "wookieEntity", "Wookie");

		this.setupAI();
		
		this.setSize(1.0F, 0.5F);
		
		// how much experience do you get it you kill it?
		this.experienceValue = 5;
				
		// Properties that we need to have later.
		this.shadowSize = 1.0F;
				
		// Put a iron axe in his mainhand slot.
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
	}
	
	
	@Override
	public void registerEntityRender() {
				
		Class renderBaseClass = WookieRender.class;
		Class modelBaseClass = WookieModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, 1.0F);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	@Override
	public void registerRecipe() {
		
		// Recipe for creating a Wookie Egg.
		ItemStack arrowStack = new ItemStack(Items.ARROW);	
		ItemStack eggStack = new ItemStack(Items.EGG);

		GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.entityManager.wookieEgg, 1), 
						"A", 
						"B", 
						'A', arrowStack, 
						'B', eggStack);
	}
	
	
	// set up AI tasks
	protected void setupAI()
	{
	   clearAITasks(); // clear any tasks assigned in super classes
	   
	   // Set up the FactionInfo array that defines who Wookies attack.
	   FactionInfo[] factions = new FactionInfo[2];
	   factions[0] = SuperDopeJediMod.factionManager.getFactionInfo(SuperDopeJediMod.factionManager.SITH);
	   factions[1] = SuperDopeJediMod.factionManager.getFactionInfo(SuperDopeJediMod.factionManager.BOUNTYHUNTER);
	   
	   // Main AI task list.
	   this.tasks.addTask(1, new EntityAIAttackMeleeFactionAware(this, 1.0, false, factions));
	   // tasks.addTask(5, new EntityAIMate(this, 1.0D)); We don't need these guys mating.
	   //this.tasks.addTask(7, new EntityAIFollowParent(this, 1.25D));
	   this.tasks.addTask(8, new EntityAIWander(this, 1.0D));

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
		
		Class entityClass = WookieEntity.class;
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
		
		this.entityDropItem(new ItemStack(Items.IRON_AXE), 0);
    }
}