package superdopesquad.superdopejedimod.entity;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassInfo;
import superdopesquad.superdopejedimod.faction.ClassManager;


@SideOnly(Side.CLIENT)
public class LayerClassCape implements LayerRenderer<EntityLivingBase>
{
	
	
    private final RenderPlayer playerRenderer;

    
    public LayerClassCape(RenderPlayer playerRendererIn)
    {
        this.playerRenderer = playerRendererIn;
    }

    
     public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)  {
    	
    	 // I don't know what this does yet, it was in the code i stole from the original cape layer code.
    	 GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                
         // Bail if the current entity is not a player.  
    	 if (!(entitylivingbaseIn instanceof EntityPlayer)) {
                	
    		 System.out.println("DEBUG: FAILURE! Unexpected object in LayerFactionCape.");      	
    		 return;     
    	 }
                
         // figure out the class and the corresponding cape.    
    	 EntityPlayer player = (EntityPlayer)entitylivingbaseIn;      
    	 ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass(player);           
    	 String resourceName = classInfo.getCapeResource();
                
         // if resourceName == null, that means FactionManager is telling us this faction doesn't get a cape.
         if (resourceName == null) {
        	 return;
         }
                
         //System.out.println("DEBUG: FACTION: " + factionInfo.getId().toString() + ":" + factionInfo.getName());
       
         // Bind the texture.
         ResourceLocation resourceLocation = new ResourceLocation(SuperDopeJediMod.MODID, resourceName);
         this.playerRenderer.bindTexture(resourceLocation);
                   
                
                // The rest of this code is copied from the original layercape.
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 0.0F, 0.125F);
                double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
                double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
                double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
                float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
                double d3 = (double)MathHelper.sin(f * 0.017453292F);
                double d4 = (double)(-MathHelper.cos(f * 0.017453292F));
                float f1 = (float)d1 * 10.0F;
                f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
                float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;

                if (f2 < 0.0F)
                {
                    f2 = 0.0F;
                }

                float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
                f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

                if (player.isSneaking())
                {
                    f1 += 25.0F;
                }

                GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                this.playerRenderer.getMainModel().renderCape(0.0625F);
                GlStateManager.popMatrix();
    }

     
    public boolean shouldCombineTextures() {
        
    	return false;
    }
}
