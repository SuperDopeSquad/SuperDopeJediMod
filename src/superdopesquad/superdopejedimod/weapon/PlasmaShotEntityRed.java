package superdopesquad.superdopejedimod.weapon;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;


public class PlasmaShotEntityRed extends PlasmaShotEntityBase {

	
	public PlasmaShotEntityRed(World worldIn) {
		 super(worldIn);
	}
	 
	 
	public PlasmaShotEntityRed(World worldIn, EntityLivingBase throwerIn, PowerLevel pl) {
		super(worldIn, throwerIn, pl);
	}
	
	
	@Override
	public String getName() {
		return "plasmaShotEntityRed";
	}
}
