package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MandalorianIronOre extends BaseBlock {

	
	public MandalorianIronOre(String unlocalizedName) {
		
		super(Material.iron, unlocalizedName);
	}

	
	public Item getItemDropped(int metadata, Random random, int fortune) {
	        
		return Item.getItemFromBlock(SuperDopeJediMod.mandalorianIronOre);
	}
	
}





