package superdopesquad.superdopejedimod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGenerator extends Container{

	private TileEntityGenerator te;
	
	public ContainerGenerator(IInventory playerInv, TileEntityGenerator te) {
		
		this.te = te;
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 56, 17));
		//this.addSlotToContainer(new Slot(playerInv, 0, 56, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 112, 31));
		//this.addSlotToContainer(new Slot(playerInv, 1, 112, 31));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 56, 53));
		//this.addSlotToContainer(new Slot(playerInv, 2, 56, 53));
		
		int xPos = 8;
		int yPos = 84;
				
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}
				
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		
		return !player.isSpectator();
	}

}
