package superdopesquad.superdopejedimod.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class SuperDopeRenderManager {

	static RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();


	private static RenderItem itemRendererIn = Minecraft.getMinecraft().getRenderItem();
	
	
	public static RenderSnowball plasmaFireRender = new RenderSnowball(renderManager, Items.SNOWBALL, itemRendererIn);

}
