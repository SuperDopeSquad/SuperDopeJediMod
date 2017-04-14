package superdopesquad.superdopejedimod.ship;


import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;


public class HangarPadBlock extends BaseBlock {
	
	
	public HangarPadBlock(String name) {
		
		super(Material.PORTAL, name);
		
		// Make it so it can't be broken by anything lower than an iron pickaxe.
		this.setHarvestLevel("pickaxe", 2);
	}
}

