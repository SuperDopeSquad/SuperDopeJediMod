package superdopesquad.superdopejedimod.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.SuperDopeEntity;

public abstract class BaseEntityProjectile extends EntityThrowable implements SuperDopeEntity {

	
	private String _name = "foo";
	protected float _damageAmount = 0F;
	
	
	public BaseEntityProjectile(World worldIn) {
		
		super(worldIn);
		
		// Insert this object into our collection of custom objects, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}
	
	
	public BaseEntityProjectile(World worldIn, EntityLivingBase throwerIn, float damageAmount) {
	       
		super(worldIn, throwerIn);
		
		this._damageAmount = damageAmount;
		
		// Insert this object into our collection of custom objects, so we 
		// can send separate events to it for lifecycle management.
		SuperDopeJediMod.customObjects.add(this);
	}

	    
	public BaseEntityProjectile(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	
	@Override
	public String getName() {
		
		return this._name;
	}
	
	
	public String getFullName() {
		
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	

	@Override
	public void registerObject() {
	
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
	}

	
	@Override
	public void registerModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRecipe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerEntityRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		
	}
}
