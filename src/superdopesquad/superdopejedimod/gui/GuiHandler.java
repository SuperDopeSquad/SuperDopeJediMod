package superdopesquad.superdopejedimod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	
	public static final int CLASS_GUI = 0;
	public static final int GENERATOR_GUI = 1;
	
	
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == GENERATOR_GUI) {
			
			return new GeneratorGUI(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x, y, z)));
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if (ID == CLASS_GUI) {
	        return new ClassGUI(player);
		}
		
		if (ID == GENERATOR_GUI) {
			
			return new GeneratorGUI(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x, y, z)));
			
		}
		
		
		return null;
		
	}

}
