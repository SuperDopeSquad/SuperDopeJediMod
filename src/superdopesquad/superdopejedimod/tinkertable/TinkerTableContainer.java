package superdopesquad.superdopejedimod.tinkertable;


import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superdopesquad.superdopejedimod.GeneratorRecipeHandler;


public class TinkerTableContainer extends Container {

	public static int CRAFTING_INPUT_WIDTH = 3;
	public static int CRAFTING_INPUT_HEIGHT = 3;

	public InventoryCrafting inventoryInput = new InventoryCrafting(this, CRAFTING_INPUT_WIDTH, CRAFTING_INPUT_HEIGHT);
	public InventoryCraftResult inventoryOutput = new InventoryCraftResult();

	private EntityPlayer _player;
	private World _world;
	private BlockPos _blockPos;
	
	//public int inputSlotNumber;
	public GeneratorRecipeHandler recipeHandler;


	public TinkerTableContainer(EntityPlayer player, World world, BlockPos blockPos) {

		//super();

		this._player = player;
		this._world = world;
		this._blockPos = blockPos;
		
		recipeHandler = new GeneratorRecipeHandler();
		
		// Crafting matrix.
	    for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
        		//System.out.println("adding input slot: " + (j + i * 3));
                this.addSlotToContainer(new Slot(this.inventoryInput, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

		// Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
           		//System.out.println("adding player slot: " + (i1 + k * 9 + 9));
                this.addSlotToContainer(new Slot(this._player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int l = 0; l < 9; ++l)
        {
      		//System.out.println("adding player hot slot: " + (l));
            this.addSlotToContainer(new Slot(this._player.inventory, l, 8 + l * 18, 142));
        }
		
		// output slot.
		this.addSlotToContainer(new Slot(this.inventoryOutput, 100, 124, 35));
	}


	   /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.slotChangedCraftingGrid(this._world, this._player, this.inventoryInput, this.inventoryOutput);
    }
    
    
    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this._world.isRemote)
        {
            this.clearContainer(playerIn, this._world, this.inventoryInput);
        }
    }
    
//	/**
//	 * Callback for when the crafting gui is closed.
//	 */
//	@Override
//	public void onContainerClosed(EntityPlayer parPlayer)
//	{
//		if((this._player.inventory != null) && (this._player.inventory.getItemStack() != null))
//		{
//			parPlayer.entityDropItem(this._player.inventory.getItemStack(), 0.5f);
//		}
//
//		if(!this._world.isRemote)
//		{
//			ItemStack itemStack = inventoryInput.getStackInSlot(0);
//			if(itemStack != null)
//			{
//				parPlayer.entityDropItem(itemStack, 0.5f);
//			}
//
//			for(int i = 0; i < this.inventoryOutput.getSizeInventory(); i++ )
//			{
//				itemStack = this.inventoryOutput.getStackInSlot(i);
//
//				if(itemStack != null)
//				{
//					parPlayer.entityDropItem(itemStack, 0.5f);
//				}
//			}
//		}
//	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
//	  public boolean canInteractWith(EntityPlayer playerIn)
//	    {
//	        if (this.world.getBlockState(this.pos).getBlock() != Blocks.CRAFTING_TABLE)
//	        {
//	            return false;
//	        }
//	        else
//	        {
//	            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
//	        }
//	    }



	
	@Override
	 public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	    {
	        ItemStack itemstack = ItemStack.EMPTY;
	        Slot slot = this.inventorySlots.get(index);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (index == 0)
	            {
	                itemstack1.getItem().onCreated(itemstack1, this._world, playerIn);

	                if (!this.mergeItemStack(itemstack1, 10, 46, true))
	                {
	                    return ItemStack.EMPTY;
	                }

	                slot.onSlotChange(itemstack1, itemstack);
	            }
	            else if (index >= 10 && index < 37)
	            {
	                if (!this.mergeItemStack(itemstack1, 37, 46, false))
	                {
	                    return ItemStack.EMPTY;
	                }
	            }
	            else if (index >= 37 && index < 46)
	            {
	                if (!this.mergeItemStack(itemstack1, 10, 37, false))
	                {
	                    return ItemStack.EMPTY;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
	            {
	                return ItemStack.EMPTY;
	            }

	            if (itemstack1.isEmpty())
	            {
	                slot.putStack(ItemStack.EMPTY);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }

	            if (itemstack1.getCount() == itemstack.getCount())
	            {
	                return ItemStack.EMPTY;
	            }

	            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

	            if (index == 0)
	            {
	                playerIn.dropItem(itemstack2, false);
	            }
	        }

	        return itemstack;
	    }
	
	
	@Override
	public boolean canMergeSlot(ItemStack itemStack, Slot slot)
	{
		  return slot.inventory != this.inventoryOutput && super.canMergeSlot(itemStack, slot);
		//return !parSlot.inventory.equals(this.inventoryOutput);
	}

	//	    @Override
	//	    public Slot getSlot(int parSlotIndex)
	//	    {
	//	        if(parSlotIndex >= inventorySlots.size())
	//	            parSlotIndex = inventorySlots.size() - 1;
	//	        return super.getSlot(parSlotIndex);
	//	    }

}
