package superdopesquad.superdopejedimod;

import java.util.Random;

import javax.swing.Icon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ControlPanel extends BaseBlock{
	
	public ControlPanel(String unlocalizedName) {
		
		super(Material.IRON, unlocalizedName);
	}

		
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.controlPanel);
	}
}
