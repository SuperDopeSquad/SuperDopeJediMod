package superdopesquad.superdopejedimod.hangar;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;
import superdopesquad.superdopejedimod.faction.FactionAwareInterface;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class HangarPadBlock extends BaseBlock implements FactionAwareInterface  {
	
	
	private List<FactionInfo> _friendlyFactions;
	private List<FactionInfo> _unfriendlyFactions;
	private boolean _isUseFriendlyOnly;
	private boolean _isUseUnfriendlyBanned;
	
	
	public HangarPadBlock(String name) {
		
		this(name, new ArrayList<FactionInfo>());
	}
	
	
	public HangarPadBlock(String name, ArrayList<FactionInfo> friendlyFactions) {
		
		super(Material.PORTAL, name);
		
		// Make it so it can't be broken by anything lower than an diamond pickaxe.
		this.setHarvestLevel("pickaxe", 3);
		
		this._friendlyFactions = friendlyFactions;
		this._unfriendlyFactions = new ArrayList<FactionInfo>();
		this._isUseFriendlyOnly = (this._friendlyFactions.size() > 0);
		this._isUseUnfriendlyBanned = false;
	}
	
	
	public boolean CanBuildThisShipKit(ShipKit shipKit) {
		
		if (this.IsUseFriendlyOnly()) {
			
			for (int i = 0; i < shipKit.GetFriendlyFactions().size(); i++) {
				for (int j = 0; j < this.GetFriendlyFactions().size(); j++) {
					if (this.GetFriendlyFactions().contains(shipKit.GetFriendlyFactions().toArray()[i])) {
						return true;
					}
				}
			}
			
			return false;
		}
		
		// MC-TODO: add the logic if someone ever uses IsUseUnfriendlyBanned.
		
		return true;
	}
	
	
	@Override
	public List<FactionInfo> GetFriendlyFactions() {
		return this._friendlyFactions;
	}


	@Override
	public List<FactionInfo> GetUnfriendlyFactions() {
		return this._unfriendlyFactions;
	}


	@Override
	public boolean IsUseFriendlyOnly() {
		return this._isUseFriendlyOnly;
	}


	@Override
	public boolean IsUseUnfriendlyBanned() {
		return this._isUseUnfriendlyBanned;
	}
}
