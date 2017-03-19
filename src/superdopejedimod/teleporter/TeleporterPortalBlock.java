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


public class TeleporterPortalBlock extends BaseBlock {
	
	
	public TeleporterPortalBlock(String name) {
		
		super(Material.IRON, name);
	}
}
