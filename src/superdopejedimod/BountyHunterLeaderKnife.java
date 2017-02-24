package superdopesquad.superdopejedimod;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import superdopesquad.superdopejedimod.weapon.BaseMeleeWeapon;

public class BountyHunterLeaderKnife extends BaseMeleeWeapon{

	public BountyHunterLeaderKnife(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.lightSaberKnifeMaterial);
		
		this.setMaxStackSize(1);
		
		this.setCreativeTab(CreativeTabs.COMBAT);

		
		
	}
	
	
	
	
}
