package superdopesquad.superdopejedimod.ship;


import net.minecraft.block.material.Material;
import superdopesquad.superdopejedimod.entity.TieFighterEntity;


public class TieFighterShipKit extends ShipKit {


	public TieFighterShipKit(String unlocalizedName) {

		super(unlocalizedName);
	}
	
	
	@Override
	public Class getEntityToMake() {
		
		return TieFighterEntity.class;
	}
}
