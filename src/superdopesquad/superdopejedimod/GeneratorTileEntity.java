//package superdopesquad.superdopejedimod;
//
//import java.util.ArrayList;
//
////tile entities store information about the block
//
//import net.minecraft.block.state.IBlockState;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SPacketUpdateTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.ITickable;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentString;
//import net.minecraft.util.text.TextComponentTranslation;
//import net.minecraft.world.World;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.capabilities.ICapabilityProvider;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.ItemStackHandler;
//
//public class GeneratorTileEntity extends TileEntity implements IInventory, ICapabilityProvider {
//
//	 private ItemStack[] inventory;
//	 private String customName;
//
//	    
//	//private ItemStackHandler handler;
//	
//	public GeneratorTileEntity() {
//		
//		//this.handler = new ItemStackHandler(3);
//		
//	//}
//	
//
//	   // public ModTileEntity() {
//	        this.inventory = new ItemStack[this.getSizeInventory()];
//	    }
//
//	    public String getCustomName() {
//	        return this.customName;
//	    }
//
//	    public void setCustomName(String customName) {
//	        this.customName = customName;
//	    }
//	
//	
//	@Override
//	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
////	    super.writeToNBT(nbt);
//
//	    NBTTagList list = new NBTTagList();
//	    for (int i = 0; i < this.getSizeInventory(); ++i) {
//	        if (this.getStackInSlot(i) != null) {
//	            NBTTagCompound stackTag = new NBTTagCompound();
//	            stackTag.setByte("Slot", (byte) i);
//	            this.getStackInSlot(i).writeToNBT(stackTag);
//	            list.appendTag(stackTag);
//	        }
//	    }
//	    nbt.setTag("Items", list);
//
//	    if (this.hasCustomName()) {
//	        nbt.setString("CustomName", this.getName());
//	    }
//	    
//	    return super.writeToNBT(nbt);
//	}
//
//
//	@Override
//	public void readFromNBT(NBTTagCompound nbt) {
//	    super.readFromNBT(nbt);
//
//	    NBTTagList list = nbt.getTagList("Items", 10);
//	    for (int i = 0; i < list.tagCount(); ++i) {
//	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
//	        int slot = stackTag.getByte("Slot") & 255;
//	        
//	        this.setInventorySlotContents(slot, stackTag.get);
//	        
//	        //this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
//	    }
//
//	    
//	    if (nbt.hasKey("CustomName", 8)) {
//	        this.setCustomName(nbt.getString("CustomName"));
//	    }
//	}
//	
////	public void readFromNBT(NBTTagCompound nbt) {
////		
////		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
////		
////		super.readFromNBT(nbt);
////		
////		
////	}
////    
////    @Override
////    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
////    	
////    	nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
////    	
////    	return super.writeToNBT(nbt);
////    }
//
//	
//	
//	@Override
//	public SPacketUpdateTileEntity getUpdatePacket() {
//		NBTTagCompound nbt = new NBTTagCompound();
//		this.writeToNBT(nbt);
//		int metadata = getBlockMetadata();
//		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
//	}
//
//	@Override
//	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
//		this.readFromNBT(pkt.getNbtCompound());
//	}	
//
//	@Override
//	public NBTTagCompound getUpdateTag() {
//		NBTTagCompound nbt = new NBTTagCompound();
//		this.writeToNBT(nbt);
//		return nbt;
//	}
//
//	@Override
//	public void handleUpdateTag(NBTTagCompound tag) {
//		this.readFromNBT(tag);
//	}
//
//	@Override
//	public NBTTagCompound getTileData() {
//		NBTTagCompound nbt = new NBTTagCompound();
//		this.writeToNBT(nbt);
//		return nbt;
//	}
//	
//	@Override
//	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
//
//		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//			return (T) this.handler;
//		
//		return super.getCapability(capability, facing);
//	}
//	
//	@Override
//	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
//
//		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//			return true;
//		
//		return super.hasCapability(capability, facing);
//	}
//
//	@Override
//	public String getName() {
//		// TODO Auto-generated method stub
//		return "GeneratorTileEntity";
//	}
//
//	@Override
//	public boolean hasCustomName() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	
//	@Override
//	public ItemStack getStackInSlot(int index) {
//	    if (index < 0 || index >= this.getSizeInventory())
//	        return null;
//	    return this.inventory[index];
//	}
//
//	@Override
//	public ItemStack decrStackSize(int index, int count) {
//	    if (this.getStackInSlot(index) != null) {
//	        ItemStack itemstack;
//
//	        if (this.getStackInSlot(index).stackSize <= count) {
//	            itemstack = this.getStackInSlot(index);
//	            this.setInventorySlotContents(index, null);
//	            this.markDirty();
//	            return itemstack;
//	        } else {
//	            itemstack = this.getStackInSlot(index).splitStack(count);
//
//	            if (this.getStackInSlot(index).stackSize <= 0) {
//	                this.setInventorySlotContents(index, null);
//	            } else {
//	                //Just to show that changes happened
//	                this.setInventorySlotContents(index, this.getStackInSlot(index));
//	            }
//
//	            this.markDirty();
//	            return itemstack;
//	        }
//	    } else {
//	        return null;
//	    }
//	}
//
////	@Override
////	public ItemStack getStackInSlotOnClosing(int index) {
////	    ItemStack stack = this.getStackInSlot(index);
////	    this.setInventorySlotContents(index, null);
////	    return stack;
////	}
//
//	@Override
//	public void setInventorySlotContents(int index, ItemStack stack) {
//	    if (index < 0 || index >= this.getSizeInventory())
//	        return;
//
//	    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
//	        stack.stackSize = this.getInventoryStackLimit();
//	        
//	    if (stack != null && stack.stackSize == 0)
//	        stack = null;
//
//	    this.inventory[index] = stack;
//	    this.markDirty();
//	}
//	
//	@Override
//	public int getSizeInventory() {
//		// TODO Auto-generated method stub
//		return 9;
//	}
//
//	@Override
//	public boolean isEmpty() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	
//
//	@Override
//	public ItemStack removeStackFromSlot(int index) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//
//	@Override
//	public int getInventoryStackLimit() {
//		// TODO Auto-generated method stub
//		return 64;
//	}
//	
//	
//	@Override
//	public boolean isUsableByPlayer(EntityPlayer player) {
//	    return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
//	}
//
//	@Override
//	public void openInventory(EntityPlayer player) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void closeInventory(EntityPlayer player) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isItemValidForSlot(int index, ItemStack stack) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public int getField(int id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setField(int id, int value) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getFieldCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void clear() {
//	    for (int i = 0; i < this.getSizeInventory(); i++)
//	        this.setInventorySlotContents(i, null);
//	}
//	
//}
