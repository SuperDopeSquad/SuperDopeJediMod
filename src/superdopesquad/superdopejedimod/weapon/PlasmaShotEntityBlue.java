package superdopesquad.superdopejedimod.weapon;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;


public class PlasmaShotEntityBlue extends PlasmaShotEntityBase {

	public PlasmaShotEntityBlue(World worldIn) {
		super(worldIn);
	}
	
	public PlasmaShotEntityBlue(World worldIn, EntityLivingBase throwerIn, PowerLevel pl) {
		super(worldIn, throwerIn, pl);
	}
	
	
	 @Override
	 public String getName() {
		return "plasmaShotEntityBlue";
	}
}
