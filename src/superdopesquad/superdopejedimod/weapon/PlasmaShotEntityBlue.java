package superdopesquad.superdopejedimod.weapon;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;


public class PlasmaShotEntityBlue extends PlasmaShotEntityBase {

	
	 public PlasmaShotEntityBlue(World worldIn) {
	        
		 super(worldIn);
	 }
	
	
	public PlasmaShotEntityBlue(World worldIn, EntityLivingBase throwerIn, float damageAmount) {
		super(worldIn, throwerIn, damageAmount);
	}
	
	
	 @Override
	 public String getName() {
			
		return "plasmaShotEntityBlue";
	}
}
