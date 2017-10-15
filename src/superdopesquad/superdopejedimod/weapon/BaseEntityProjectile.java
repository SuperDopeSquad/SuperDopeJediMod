package superdopesquad.superdopejedimod.weapon;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.SuperDopeEntity;

public abstract class BaseEntityProjectile extends EntityThrowable implements SuperDopeEntity {

	// Instance Members
	private String _name = "foo";
	protected float _damageAmount = 0F;
	
	
	public BaseEntityProjectile(World worldIn, float damageAmount) {
		super(worldIn);
		this._damageAmount = damageAmount;
		
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
	
	@Override
	public String getName() {
		return this._name;
	}
	
	@Override
	public String getFullName() {
		return SuperDopeJediMod.MODID + ":" + this.getName();
	}
	
	@Override
    public void registerItems(RegistryEvent.Register<Item> event) {
		ResourceLocation resourceLocation = new ResourceLocation(this.getFullName());
		EntityRegistry.registerModEntity(resourceLocation, this.getClass(), this.getName(), 
				SuperDopeJediMod.entityManager.getUniqueEntityId(), SuperDopeJediMod.instance, 80, 3, true, 0xfffffff, 0x000000);
	}
   
	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
	}
	
	public void setDamage(double damageIn) {   	
		this._damageAmount = (float) damageIn;
	}

	public double getDamage() {
		return this._damageAmount;
	}
}
