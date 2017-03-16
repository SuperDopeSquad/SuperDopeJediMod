package superdopesquad.superdopejedimod.weapon;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassAwareInterface;
import superdopesquad.superdopejedimod.faction.ClassInfo;

public class BountyHunterLeaderKnife extends BaseMeleeWeapon implements ClassAwareInterface {

	public BountyHunterLeaderKnife(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.lightSaberKnifeMaterial);
		
		this.setMaxStackSize(1);
		
		this.setCreativeTab(CreativeTabs.COMBAT);

		
		
	}
	
	

	
	
	
	
}
