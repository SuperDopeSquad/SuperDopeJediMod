package superdopesquad.superdopejedimod.weapon;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;


public class PlasmaShotEntityRed extends PlasmaShotEntityBase {

	
	public PlasmaShotEntityRed(World worldIn) {
	        
		 super(worldIn);
	}
	 
	 
	public PlasmaShotEntityRed(World worldIn, EntityLivingBase throwerIn, float damageAmount) {
		super(worldIn, throwerIn, damageAmount);
	}
	
	
	@Override
	public String getName() {
			
		return "plasmaShotEntityRed";
	}
}
