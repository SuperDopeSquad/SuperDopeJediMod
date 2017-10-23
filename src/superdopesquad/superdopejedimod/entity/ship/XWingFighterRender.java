package superdopesquad.superdopejedimod.entity.ship;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.entity.BaseRenderLiving;

@SideOnly(Side.CLIENT)
public class XWingFighterRender extends BaseRenderLiving {

	public XWingFighterRender(RenderManager renderManager, ModelBase par1ModelBase, float parShadowSize) {
        super(renderManager, par1ModelBase, parShadowSize, "xwingfighter");   
    }
 
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
    }
}