package superdopesquad.superdopejedimod.entity.ship;


import net.minecraft.world.World;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.*;


public abstract class BaseEntityShip extends BaseEntityAnimal {

	
	public BaseEntityShip(World worldIn, String name, String displayName) {
		this(worldIn, name, displayName, true);
	}
		
	
	public BaseEntityShip(World worldIn, String name, String displayName, boolean hasCreativeModeEgg) {
		super(worldIn, name, displayName, hasCreativeModeEgg);
	}
	
	
	@Override // from EntityLiving
	protected boolean canDespawn() {
		return false;
	}
}
