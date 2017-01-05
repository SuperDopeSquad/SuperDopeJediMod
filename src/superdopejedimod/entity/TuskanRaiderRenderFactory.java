package superdopesquad.superdopejedimod.entity;


import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.client.registry.IRenderFactory;


public class TuskanRaiderRenderFactory implements IRenderFactory<EntityLiving> {

 
    @Override
    public Render<? super EntityLiving> createRenderFor(RenderManager manager) {
   	
        return new TuskanRaiderRender(manager, new TuskanRaiderModel(), 0.0f);
    }
}
