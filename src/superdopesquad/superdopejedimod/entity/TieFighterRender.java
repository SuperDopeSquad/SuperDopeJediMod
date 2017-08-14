package superdopesquad.superdopejedimod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

@SideOnly(Side.CLIENT)
public class TieFighterRender extends BaseRenderLiving {

	public TieFighterRender(RenderManager renderManager, ModelBase par1ModelBase, float parShadowSize) {
        super(renderManager, par1ModelBase, parShadowSize, "tiefighter");   
    }
 
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
    }
}