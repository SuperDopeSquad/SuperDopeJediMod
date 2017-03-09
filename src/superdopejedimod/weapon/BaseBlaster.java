package superdopesquad.superdopejedimod.weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.SuperDopeObject;
import superdopesquad.superdopejedimod.faction.*;


public abstract class BaseBlaster  extends BaseRangedWeapon implements SuperDopeObject, ClassAwareInterface {

	
	public BaseBlaster(String name) {
		
		super(name);
		
		
		 this.maxStackSize = 1;
         this.setMaxDamage(384);
         this.setCreativeTab(CreativeTabs.COMBAT);
         this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
         {
             @SideOnly(Side.CLIENT)
             public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
             {
                 return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() != Items.BOW ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F);
             }
         });
         this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
         {
             @SideOnly(Side.CLIENT)
             public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
             {
                 return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
             }
         });
	}
	
		
	@Override
	public void registerRecipe() {
		return;
	}


	@Override
	public List<ClassInfo> GetUnfriendlyClasses() {
	
		// By default, we do not let force-wielding classes use blasters.
		return SuperDopeJediMod.classManager.getForceWieldingClasses();
	}


	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		return true;
	}
	
	
	
//	  public EnumAction getItemUseAction(ItemStack itemStack) {
//	        //return EnumAction.BOW;
//		  
//
//		  System.out.println("TEST in GetItemUseAction: " + itemStack.getDisplayName());
//			return EnumAction.BOW;
//	  }
	  
	  
//	  @Override
//	  public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
//	    {
//		  
//		  
//		  
//		  ItemStack itemStack2 = player.getActiveItemStack();
//			System.out.println("TEST onItemUse: " + itemStack2.getDisplayName());
//		  
//			//SuperDopeJediMod.weaponManager.ThrowPlasmaShot(null, world, player, hitX, hitY, hitZ, 10F, 2F);
//
//			
//			
//	        //if (!worldIn.isRemote) return net.minecraftforge.common.ForgeHooks.onPlaceItemIntoWorld(this, playerIn, worldIn, pos, side, hitX, hitY, hitZ, hand);
//	        //EnumActionResult enumactionresult = itemStack2.getItem().onItemUse(player, worldIn, pos, hand, side, hitX, hitY, hitZ);
//
////	        if (enumactionresult == EnumActionResult.SUCCESS)
////	        {
////	            player.addStat(StatList.getObjectUseStats(this.item));
////	        }
//
//			
//	        //return enumactionresult;
//			return EnumActionResult.SUCCESS;
//	    }

	  
//	  /**
//	     * This is called when the item is used, before the block is activated.
//	     * @param stack The Item Stack
//	     * @param player The Player that used the item
//	     * @param world The Current World
//	     * @param pos Target position
//	     * @param side The side of the target hit
//	     * @param hand Which hand the item is being held in.
//	     * @return Return PASS to allow vanilla handling, any other to skip normal code.
//	     */
//	    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
//	    {
//	    	
//	    	 ItemStack itemStack2 = player.getActiveItemStack();
//				System.out.println("TEST onItemUseFirst: " + itemStack2.getDisplayName());
//			  
//			
//	        return EnumActionResult.PASS;
//	    }
//
//	  
	  
//	  protected boolean isArrow(ItemStack stack)
//	    {
//	        return stack.getItem() instanceof ItemArrow;
//	    }
//	  
//	  
//	  private ItemStack findAmmo(EntityPlayer player)
//	    {
//	        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
//	        {
//	            return player.getHeldItem(EnumHand.OFF_HAND);
//	        }
//	        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
//	        {
//	            return player.getHeldItem(EnumHand.MAIN_HAND);
//	        }
//	        else
//	        {
//	            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
//	            {
//	                ItemStack itemstack = player.inventory.getStackInSlot(i);
//
//	                if (this.isArrow(itemstack))
//	                {
//	                    return itemstack;
//	                }
//	            }
//
//	            return ItemStack.field_190927_a;
//	        }
//	    }
//	  
//	  
//	  /**
//	     * Gets the velocity of the arrow entity from the bow's charge
//	     */
//	    public static float getArrowVelocity(int charge)
//	    {
//	        float f = (float)charge / 20.0F;
//	        f = (f * f + f * 2.0F) / 3.0F;
//
//	        if (f > 1.0F)
//	        {
//	            f = 1.0F;
//	        }
//
//	        return f;
//	    }
//	  
//	  
//	  /**
//	     * Called when the player stops using an Item (stops holding the right mouse button).
//	     */
//	    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
//	    {
//	    	System.out.println("onPlayerStoppedUsing");
//	    	
//	        if (entityLiving instanceof EntityPlayer)
//	        {
//	            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
//	            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
//	            ItemStack itemstack = this.findAmmo(entityplayer);
//
//	            int i = this.getMaxItemUseDuration(stack) - timeLeft;
//	            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
//	            if (i < 0) return;
//
//	            if (!itemstack.func_190926_b() || flag)
//	            {
//	                if (itemstack.func_190926_b())
//	                {
//	                    itemstack = new ItemStack(Items.ARROW);
//	                }
//
//	                float f = getArrowVelocity(i);
//
//	                if ((double)f >= 0.1D)
//	                {
//	                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);
//
//	                    if (!worldIn.isRemote)
//	                    {
//	                        ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
//	                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
//	                        entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);
//
//	                        if (f == 1.0F)
//	                        {
//	                            entityarrow.setIsCritical(true);
//	                        }
//
//	                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
//
//	                        if (j > 0)
//	                        {
//	                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
//	                        }
//
//	                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
//
//	                        if (k > 0)
//	                        {
//	                            entityarrow.setKnockbackStrength(k);
//	                        }
//
//	                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
//	                        {
//	                            entityarrow.setFire(100);
//	                        }
//
//	                        stack.damageItem(1, entityplayer);
//
//	                        if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
//	                        {
//	                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
//	                        }
//
//	                        worldIn.spawnEntityInWorld(entityarrow);
//	                    }
//
//	                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
//
//	                    if (!flag1 && !entityplayer.capabilities.isCreativeMode)
//	                    {
//	                        itemstack.func_190918_g(1);
//
//	                        if (itemstack.func_190926_b())
//	                        {
//	                            entityplayer.inventory.deleteStack(itemstack);
//	                        }
//	                    }
//
//	                    entityplayer.addStat(StatList.getObjectUseStats(this));
//	                }
//	            }
//	        }
//	    }
//
//	  
//	  
//	  // This is client side, use entityPlayerSp, not entityPlayerMp!
//	    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
//	    {
//	    	
//	    	ItemStack itemStack2 = player.getActiveItemStack();
//			System.out.println("TEST onItemRightClick: " + itemStack2.getDisplayName());
//			//System.out.println(("Test: " + itemStack2.);
//			
//			//EntityPlayerMP entityPlayerMp = (EntityPlayerMP) player;
//			//entityPlayerMp.getAdjustedHorizontalFacing().
//			
//			float hitX = (float) player.posX + 1;
//			//float hitX = (float) player.getHorizontalFacing().
//			float hitY = (float) player.posY + 1;
//			float hitZ = (float) player.posZ + 1;
//			
//			//SuperDopeJediMod.weaponManager.ThrowPlasmaShot(null, world, player, hitX, hitY, hitZ, 10F, 2F);
//
//			//player.get
//			//return new ActionResult(EnumActionResult.PASS, null);
//			
//		//	SuperDopeJediMod.weaponManager.ThrowPlasmaShot(null, world, player, float targetX, float targetY, float targetZ, float distanceFactor, float damageAmount) {
//
//			
//			
//			//System.out.println(itemStack2.onItemUse(playerIn, worldIn, pos, hand, side, hitX, hitY, hitZ));
//			
//			//world.getbl
//			
//			//SuperDopeJediMod.weaponManager.ThrowPlasmaShotRed(world, thrower, target, distanceFactor, damageAmount);
//			
//			
//			//player.setit
//			//player.setItemInUse(itemStack);
//			
//	        ItemStack itemStack = player.getHeldItem(hand);
//	        boolean flag = false; // !this.findAmmo(worldIn).func_190926_b();
//
//	        
//	    	System.out.println("TEST: " + itemStack.getDisplayName());
//			
//	       // ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, world, player, hand, flag);
//	        //if (ret != null) return ret;
//
//	        if (!player.capabilities.isCreativeMode && !flag)
//	        {
//	        	if (flag) {
//	        		System.out.println("PASS");
//	        		 return new ActionResult(EnumActionResult.PASS, itemStack);
//	        	}
//	        	else {
//	        		System.out.println("FAIL");
//	        		return new ActionResult(EnumActionResult.FAIL, itemStack);
//	        	}
//	            //return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
//	        }
//	        else
//	        {
//	            player.setActiveHand(hand);
//	            System.out.println("SUCCESS");
//	            return new ActionResult(EnumActionResult.SUCCESS, itemStack);
//	        }
//	    }
//	
////	@Override
////	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
////		
////		ItemStack itemStack = player.getActiveItemStack();
////		System.out.println("TEST: " + itemStack.getDisplayName());
////
////		return new ActionResult(EnumActionResult.PASS, null);
////		
////		//SuperDopeJediMod.weaponManager.ThrowPlasmaShotBlue(world, player, EntityLivingBase target, float distanceFactor, float damageAmount) {
////
////		
//////		ItemStack itemstack = worldIn.getHeldItem(playerIn);
//////	        boolean flag = !this.findAmmo(worldIn).func_190926_b();
//////
//////	        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, itemStackIn, worldIn, playerIn, flag);
//////	        if (ret != null) return ret;
//////
//////	        if (!worldIn.capabilities.isCreativeMode && !flag)
//////	        {
//////	            return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
//////	        }
//////	        else
//////	        {
//////	            worldIn.setActiveHand(playerIn);
//////	            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
//////	        }
//////	    }
////	}
	  
//	  
//	      public ItemBow()
//	      {
//	          this.maxStackSize = 1;
//	          this.setMaxDamage(384);
//	          this.setCreativeTab(CreativeTabs.COMBAT);
//	          this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
//	          {
//	              @SideOnly(Side.CLIENT)
//	              public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
//	              {
//	                  return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() != Items.BOW ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F);
//	              }
//	          });
//	          this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
//	          {
//	              @SideOnly(Side.CLIENT)
//	              public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
//	              {
//	                  return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
//	              }
//	          });
//	      }

	      private ItemStack findAmmo(EntityPlayer player)
	      {
	          if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
	          {
	              return player.getHeldItem(EnumHand.OFF_HAND);
	          }
	          else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
	          {
	              return player.getHeldItem(EnumHand.MAIN_HAND);
	          }
	          else
	          {
	              for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	              {
	                  ItemStack itemstack = player.inventory.getStackInSlot(i);

	                  if (this.isArrow(itemstack))
	                  {
	                      return itemstack;
	                  }
	              }

	              return ItemStack.field_190927_a;
	          }
	      }

	      protected boolean isArrow(ItemStack stack)
	      {
	          return stack.getItem() instanceof ItemArrow;
	      }

	      /**
	       * Called when the player stops using an Item (stops holding the right mouse button).
	       */
	      public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	      {
	          if (entityLiving instanceof EntityPlayer)
	          {
	              EntityPlayer entityplayer = (EntityPlayer)entityLiving;
	              boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
	              ItemStack itemstack = this.findAmmo(entityplayer);

	              int i = this.getMaxItemUseDuration(stack) - timeLeft;
	              i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
	              if (i < 0) return;

	              if (!itemstack.func_190926_b() || flag)
	              {
	                  if (itemstack.func_190926_b())
	                  {
	                      itemstack = new ItemStack(Items.ARROW);
	                  }

	                  float f = getArrowVelocity(i);

	                  if ((double)f >= 0.1D)
	                  {
	                      boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

	                      if (!worldIn.isRemote)
	                      {
	                          ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
	                         
	                          //EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
	                          PlasmaShotEntityBase entityarrow =  new PlasmaShotEntityBlue(worldIn, entityplayer, 1.0F);

	                          
	                          entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

	                          //if (f == 1.0F)
	                          //{
	                          //    entityarrow.setIsCritical(true);
	                          //}

	                          int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

	                          if (j > 0)
	                          {
	                              entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
	                          }

	                          int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

//	                          if (k > 0)
//	                          {
//	                              entityarrow.setKnockbackStrength(k);
//	                          }

	                          if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
	                          {
	                              entityarrow.setFire(100);
	                          }

	                          stack.damageItem(1, entityplayer);

//	                          if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
//	                          {
//	                              entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
//	                          }

	                          worldIn.spawnEntityInWorld(entityarrow);
	                      }

	                      worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	                      if (!flag1 && !entityplayer.capabilities.isCreativeMode)
	                      {
	                          itemstack.func_190918_g(1);

	                          if (itemstack.func_190926_b())
	                          {
	                              entityplayer.inventory.deleteStack(itemstack);
	                          }
	                      }

	                      entityplayer.addStat(StatList.getObjectUseStats(this));
	                  }
	              }
	          }
	      }

	      /**
	       * Gets the velocity of the arrow entity from the bow's charge
	       */
	      public static float getArrowVelocity(int charge)
	      {
	          float f = (float)charge / 20.0F;
	          f = (f * f + f * 2.0F) / 3.0F;

	          if (f > 1.0F)
	          {
	              f = 1.0F;
	          }

	          return f;
	      }

	      /**
	       * How long it takes to use or consume an item
	       */
	      public int getMaxItemUseDuration(ItemStack stack)
	      {
	          return 72000;
	      }

	      /**
	       * returns the action that specifies what animation to play when the items is being used
	       */
	      public EnumAction getItemUseAction(ItemStack stack)
	      {
	          return EnumAction.BOW;
	      }

	      public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
	      {
	    	  System.out.print("inside onItemRightClick");
	    	  
	          ItemStack itemstack = worldIn.getHeldItem(playerIn);
	          boolean flag = !this.findAmmo(worldIn).func_190926_b();

	          ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, itemStackIn, worldIn, playerIn, flag);
	          if (ret != null) return ret;

	          if (!worldIn.capabilities.isCreativeMode && !flag)
	          {
	              return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
	          }
	          else
	          {
	              worldIn.setActiveHand(playerIn);
	              return new ActionResult(EnumActionResult.SUCCESS, itemstack);
	          }
	      }

	      /**
	       * Return the enchantability factor of the item, most of the time is based on material.
	       */
	      public int getItemEnchantability()
	      {
	          return 1;
	      }
	  
	  
}
