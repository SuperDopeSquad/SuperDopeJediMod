package superdopesquad.superdopejedimod.ship;


import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityChicken;


public class TestShipKit extends ShipKit {


	public TestShipKit(String unlocalizedName) {

		super(unlocalizedName);
	}
	
	
	@Override
	public Class getEntityToMake() {
		
		return EntityChicken.class;
	}
}
