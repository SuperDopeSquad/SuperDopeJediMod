package superdopesquad.superdopejedimod.entity;


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.entity.RenderChicken;
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



public class JawaEntity extends BaseEntityAnimal {
				
	
	public JawaEntity(World worldIn) {
		
		super(worldIn, "jawaEntity");

		this.setupAI();
		
		this.setSize(1.0F, 0.5F);
	}
	
	
	@Override
	public void registerEntityRender() {
		
		Class renderBaseClass = JawaRender.class;
		Class modelBaseClass = JawaModel.class;
		EntityRenderFactory factory = new EntityRenderFactory(renderBaseClass, modelBaseClass, 1.0F);
		RenderingRegistry.registerEntityRenderingHandler(this.getClass(), factory);
	}
	
	
	@Override
	public void registerRecipe() {
		
		// Recipe for creating a Jawa Egg.
		ItemStack carrotStack = new ItemStack(Items.CARROT);	
		ItemStack eggStack = new ItemStack(Items.EGG);

		GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.entityManager.jawaEgg, 1), 
						"A", 
						"B", 
						'A', carrotStack, 
						'B', eggStack);
	}
	
	
	// set up AI tasks
	protected void setupAI()
	{
	   clearAITasks(); // clear any tasks assigned in super classes
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
}