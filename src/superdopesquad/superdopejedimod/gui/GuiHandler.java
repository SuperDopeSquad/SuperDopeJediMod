package superdopesquad.superdopejedimod.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import superdopesquad.superdopejedimod.tinkertable.TinkerTableContainer;
import superdopesquad.superdopejedimod.tinkertable.TinkerTableGui;

public class GuiHandler implements IGuiHandler {

	
	public static final int CLASS_GUI = 0;
	public static final int TINKERTABLE_GUI = 1;
	
	
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		//System.out.println("Inside GuiHandler:getServerGuiElement.  ID = " + ID);
		//System.out.println(ID == GuiEnum.TINKER_TABLE.ordinal());
		
	    TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
	    BlockPos blockPos = new BlockPos(x,y,z);
		
		if (ID == TINKERTABLE_GUI) {
			
			System.out.println("Inside GuiHandler:GetServerGuiElement, TINKERTABLE case");
			return new TinkerTableContainer(player, world, blockPos);			
		}
		
		System.out.println("ERROR: bad Gui ID sent to GuiHandler:getServerGuiElement: " + ID);
		
		return null;
	}

	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		//System.out.println("Inside GuiHandler:getClientGuiElement");
		//System.out.println(ID == GuiEnum.TINKER_TABLE.ordinal());
		
	    TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		if (ID == CLASS_GUI) {
			System.out.println("Inside GuiHandler:GetClientGuiElement, CLASS case");
			
	        return new ClassGUI(player);
		}
		
		
		// Example of how you would do it if you were using a block with TileEntities:
//		  if (tileEntity != null)
//	        {
//	            if (ID == BlockSmith.GUI_ENUM.GRINDER.ordinal())
//	            {
//	                return new GuiGrinder(player.inventory, (IInventory)tileEntity);
//	            }
//	        }
		
		else if (ID == TINKERTABLE_GUI) {
			
			//System.out.println("Inside GuiHandler:GetClientGuiElement, TINKERTABLE case");
			//System.out.println("x:" + x + " y:" + y + "z:" + z);
	    	
			return new TinkerTableGui(player, world, 
	                  I18n.format("tile.tinkertable.name"), x, y, z);			
		}
		
		System.out.println("ERROR: bad Gui ID sent to GuiHandler:getClientGuiElement: " + ID);
		
		return null;
	}
}