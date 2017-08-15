package superdopesquad.superdopejedimod.entity.droid;


import java.util.Random;
import net.minecraft.block.material.Material;
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


public class DroidKit extends BaseBlock {

	
	public DroidKit(String unlocalizedName) {

		super(Material.IRON, unlocalizedName);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.entityManager.droidKit);
	}
	
	
	public void registerRecipe() {
		
		// An 8-box of DroidParts creates a DroidKit.	
	    ItemStack itemStackDroidPartsOne = new ItemStack(SuperDopeJediMod.entityManager.droidParts);
	    ItemStack itemStackDroidKit = new ItemStack(this);
	    ItemStack itemStackDroidPartsMany = new ItemStack(SuperDopeJediMod.entityManager.droidParts, 8);
		    
	    GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackDroidKit, "xxx", "x x", "xxx", 'x', itemStackDroidPartsOne);	
	    GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackDroidPartsMany, "x", 'x', itemStackDroidKit);	
	}
}
