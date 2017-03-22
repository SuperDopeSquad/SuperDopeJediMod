package superdopesquad.superdopejedimod.teleporter;


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


public class TeleporterFinishingKit extends BaseBlock {

	
	private TeleporterData _teleporterData;
	
	
	public TeleporterFinishingKit(String unlocalizedName) {

		super(Material.IRON, unlocalizedName);
		
		
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.teleporterManager.teleporterFinishingKit);
	}
	
	
	public void setTeleporterData(TeleporterData teleporterData) {
		
		this._teleporterData = teleporterData;
		System.out.println("finishingKit's teleportData set: " + this._teleporterData.getBlockPosThere().toString());
	}
	
	
	public TeleporterData getTeleporterData() {
		
		return this._teleporterData;
	}
}
