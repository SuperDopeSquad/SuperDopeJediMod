package superdopesquad.superdopejedimod.hangar;


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
import superdopesquad.superdopejedimod.entity.droid.RepublicHunterDroidEntity;


public class ShipKit extends BaseBlock {

	
	private Class _entityToMake;
	private Item _recipeCenterSquare;

	
	public ShipKit(String unlocalizedName, Class entityToMake, Item recipeCenterSquare) {

		super(Material.IRON, unlocalizedName);
		
		this._entityToMake = entityToMake;
		this._recipeCenterSquare = recipeCenterSquare;
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        		
		return Item.getItemFromBlock(this);
	}
	
	
	public Class getEntityToMake() {
		
		return this._entityToMake;
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
}
