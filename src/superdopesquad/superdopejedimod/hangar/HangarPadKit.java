package superdopesquad.superdopejedimod.hangar;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionAwareInterface;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class HangarPadKit extends BaseBlock implements FactionAwareInterface {

	
	private List<FactionInfo> _friendlyFactions;
	private List<FactionInfo> _unfriendlyFactions;
	private boolean _isUseFriendlyOnly;
	private boolean _isUseUnfriendlyBanned;
	private int _hangarDiameter;
	private HangarPadBlock _hangarPadBlock;
	private Item _recipeCenterSquare;
	
	
	public int getHangarDiameter() { return this._hangarDiameter;};
	public HangarPadBlock getHangarPadBlock() { return this._hangarPadBlock;};
	
	
	public HangarPadKit(String unlocalizedName, HangarPadBlock hangarPadBlock, Item recipeCenterSquare) {
		this(unlocalizedName, hangarPadBlock, recipeCenterSquare, new ArrayList<FactionInfo>(),
				HangarManager.HANGARPAD_DEFAULT_WIDTH);
	}
	
	
	public HangarPadKit(String unlocalizedName, HangarPadBlock hangarPadBlock, Item recipeCenterSquare,
			List<FactionInfo> friendlyFactions, int hangarDiameter) {

		super(Material.IRON, unlocalizedName);

		this._friendlyFactions = friendlyFactions;
		this._unfriendlyFactions = new ArrayList<FactionInfo>();
		this._isUseFriendlyOnly = (this._friendlyFactions.size() > 0);
		this._isUseUnfriendlyBanned = false;

		this._hangarDiameter = hangarDiameter;
		this._hangarPadBlock = hangarPadBlock;
		this._recipeCenterSquare = recipeCenterSquare;
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        		
		return Item.getItemFromBlock(this);
	}
	
	
	@Override
	public void registerRecipe() {
		
		// If the center square item for the recipe was set to null, that means, don't create a recipe.
		if (this._recipeCenterSquare == null)
			return;
				
		ItemStack itemStackHangerParts = new ItemStack(SuperDopeJediMod.hangarManager.hangarParts);
		ItemStack itemStackThis = new ItemStack(this);
		ItemStack itemStackCenterSquare = new ItemStack(this._recipeCenterSquare);
    	
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackThis, "xxx", "xyx", "xxx", 
    			'x', itemStackHangerParts, 'y', itemStackCenterSquare);	
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
