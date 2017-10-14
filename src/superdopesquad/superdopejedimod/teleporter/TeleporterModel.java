package superdopesquad.superdopejedimod.teleporter;


import java.time.Instant;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class TeleporterModel extends ModelBase
{
	private ModelRenderer greenBox;
	private ModelRenderer redBox;
	
	private static int textureWidth = 64;
	private static int textureHeight = 64;
	
	private Instant _instant = null; 
	
    
    public TeleporterModel() {   
    	
    	this(1.0F);
    }    

    
    public TeleporterModel(float scale) {
        
    	this(scale, 0.0F, textureWidth, textureHeight);
    }

    
    public TeleporterModel(float scale, float p_i1164_2_, int width, int height) {
    	
        // x: left (negative) and right (positive)
        // y: up (negative) and down (positive)
        // z: front (negative) and back (positive)
        // width: left to right distance
        // height: up and down distance
        // depth: front to back distance
        // public void addBox(float offX, float offY, float offZ, int width, int height, int depth, float scaleFactor)
        // public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
            
    	float x = -4F;
    	float y = -16F;
    	float z = -4F;
    	int _width = 8;
    	int _height = 32;
    	int _depth = 8;
    	
    	// the box we render when the teleporter is correctly configured.
        this.greenBox = (new ModelRenderer(this)).setTextureSize(width, height);   
        this.greenBox.setTextureOffset(0, 0).addBox(x, y, z, _width, _height, _depth, scale); 

        // the box we render when the teleporter is not configured.
        this.redBox = (new ModelRenderer(this)).setTextureSize(width, height);   
        this.redBox.setTextureOffset(32, 0).addBox(x, y, z, _width, _height, _depth, scale);  
    }

    
    private boolean shouldSyncWithServer() {
    	
    	if (this._instant == null) {
    		this._instant = Instant.now();
    		return true;
    	}
    	
    	Instant rightnow = Instant.now();
    	
    	if (this._instant.isBefore(rightnow.minusSeconds(5))) {
    		this._instant = Instant.now();
    		return true;
    	}
    	
    	return false;
    }
    
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        
    	if (!(entity instanceof TeleporterEntity)) {
    		System.out.println("ERROR! Bad entity being used for TeleporterModel.");
    		return;
    	}
    	
    	TeleporterEntity teleporterEntity = (TeleporterEntity)entity;
    	float realScale = scale * 1.0F;  
    	
    	// If we never asked the server to tell us whether the teleporter is configured right or not, do so now.
    	if (this.shouldSyncWithServer()) {	
   
    		teleporterEntity.syncWithServer();
    	}
    	       
    	if (teleporterEntity.isCorrectlyConfigured()) {
    		
    		this.greenBox.render(realScale);
    	}
    	else {
    		
    		this.redBox.render(realScale);
    	}
    }
}