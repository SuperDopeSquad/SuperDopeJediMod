package superdopesquad.superdopejedimod.teleporter;


import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.BaseBlock;


public class TeleporterEdgeBlock extends BaseBlock {
	
	
	public TeleporterEdgeBlock(String name) {
		
		super(Material.PORTAL, name);
		
		// Make it so it can't be broken by anything lower than a diamond pickaxe.
		this.setHarvestLevel("pickaxe", 3);
	}
}

