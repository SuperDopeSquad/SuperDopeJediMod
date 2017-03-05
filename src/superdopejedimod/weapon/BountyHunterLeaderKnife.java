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
	
	
	@Override
	public List<ClassInfo> GetFriendlyClasses() {
	
		// By default, we only let partial force-wielding classes use this.
		return SuperDopeJediMod.classManager.getPartialForceWieldingClasses();
	}
	
	
	@Override
	public List<ClassInfo> GetUnfriendlyClasses() {
	
		// By default, we do not let non force-wielding classes use blasters.
		return SuperDopeJediMod.classManager.getNonForceWieldingClasses();
	}
	
	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		return true;
	}
	
}
