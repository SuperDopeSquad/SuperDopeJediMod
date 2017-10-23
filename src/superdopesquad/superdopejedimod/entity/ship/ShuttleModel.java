package superdopesquad.superdopejedimod.entity.ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.faction.ClassManager;

// Geometry CHEAT SHEET
// x: left (negative) and right (positive)
// y: up (negative) and down (positive)
// z: front (negative) and back (positive)
// width: left to right distance
// height: up and down distance
// depth: front to back distance
// angles are in radians!
// addBox: coordinates are 16 to a block

// in y dimension, 0 is 24 off the ground.


@SideOnly(Side.CLIENT)
public class ShuttleModel extends ModelBase {
	
	/* Constants */
	public static final int TEXTURE_WIDTH = 256; 
	public static final int TEXTURE_HEIGHT = 256;
    
	public ModelRenderer cockpit;
	public List<ModelRenderer> parts = new ArrayList<ModelRenderer>();
	
	 private static final int cockpitWidth = 48;
   private static final int wingThickness = 4;
   private static final int maxwingwidth = 96;
   private static final int sectionHeight = 32;
   private static final float absoluteDistanceFromCenter = 48.0F + 24.0F;
    
   private static final int middleWidth = cockpitWidth * 2;
   private static final int quatreWidth = middleWidth - 6;
   private static final int cinqWidth = quatreWidth - 6;
   private static final int capWidth = quatreWidth - 6;
   private static final float yoffset_begin = -88.0f;
	
	/* Constructors */
    public ShuttleModel() {   
    	ModelRenderer subpart;
    	
    	// main cockpit
    	this.cockpit = buildCockpit();
    
        // struts & wings.
        this.parts.add(builStrut(false));
        this.parts.add(builStrut(true));
        this.parts.add(buildWing(absoluteDistanceFromCenter));
        this.parts.add(buildWing(-absoluteDistanceFromCenter));
    }

    /* */
    private ModelRenderer buildCockpit() {
    	ModelRenderer mainpart = null, subpart = null;
    	
    	// Main cube
    	mainpart = new ModelRenderer(this, 0, 0);
    	mainpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	mainpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	mainpart.addBox((float)-(cockpitWidth/2), yoffset_begin + (float)-(cockpitWidth/2), (float)-(cockpitWidth/2), 
    			cockpitWidth, cockpitWidth, cockpitWidth);
        
    	// lid
        subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-6)/2, 
    			yoffset_begin + (float)((-cockpitWidth/2)-4), 
    			(float) -(cockpitWidth-6)/2, 
    			cockpitWidth-6, 
    			4, 
    			cockpitWidth-6);
    	mainpart.addChild(subpart);
    	
    	// lid2
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-12)/2, 
    			yoffset_begin + (float)((-cockpitWidth/2)-8), 
    			(float) -(cockpitWidth-12)/2, 
    			cockpitWidth-12, 
    			4, 
    			cockpitWidth-12);
    	mainpart.addChild(subpart);
    	
    	// bottom
        subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-6)/2, 
    			yoffset_begin + (float)((cockpitWidth/2)), 
    			(float) -(cockpitWidth-6)/2, 
    			cockpitWidth-6, 
    			4, 
    			cockpitWidth-6);
    	mainpart.addChild(subpart);
    	
    	// bottom2
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-12)/2, 
    			yoffset_begin + (float)((cockpitWidth/2)+4), 
    			(float) -(cockpitWidth-12)/2, 
    			cockpitWidth-12, 
    			4, 
    			cockpitWidth-12);
    	mainpart.addChild(subpart);
    	
    	// back-window
	    subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-6)/2, 
    			(float)  yoffset_begin + -(cockpitWidth-6)/2, 
    			(float) cockpitWidth/2, 
    			cockpitWidth-6, 
    			cockpitWidth-6,
    			4);
    	mainpart.addChild(subpart);
    	
    	// front-window
	    subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(
    			(float) -(cockpitWidth-6)/2, 
    			(float)  yoffset_begin + -(cockpitWidth-6)/2, 
    			(float) (-cockpitWidth/2)-4, 
    			cockpitWidth-6, 
    			cockpitWidth-6,
    			4);
    	mainpart.addChild(subpart);
    	
    	return mainpart;
    }
    
    
    /* */
    private ModelRenderer builStrut(boolean reverse) {
    	
    	ModelRenderer subpart, base = null;
    	
	    // struts, going x positive
		int strutOffsets[] = {4, 8, 6, 6, 18, 2, 2, 2};
		int strutRadius[] = { cockpitWidth-4, cockpitWidth-20, cockpitWidth-26, cockpitWidth-32, cockpitWidth-38, cockpitWidth-36, cockpitWidth-34, cockpitWidth-26};
		
		int currX = (cockpitWidth / 2) * (reverse ? -1 : 1);
		for (int i = 0 ; i < strutOffsets.length ; ++i) {
			
			if (reverse) {
				currX -= strutOffsets[i];
			}
			
	        subpart = new ModelRenderer(this, 0, 0);
	    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
	    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
	    	subpart.addBox(
	    			(float) currX, 
	    			yoffset_begin + (float)-(strutRadius[i]/2), 
	    			(float)-(strutRadius[i]/2), 
	    			strutOffsets[i], 
	    			strutRadius[i], 
	    			strutRadius[i]);
	    	
	    	if (i == 0) 
	    		base = subpart;
	    	else
	    		base.addChild(subpart); 
	        
	    	if (!reverse) {
	    		currX += strutOffsets[i];
	    	}
		}
		
		return base;
    }

    
    /* */
    private ModelRenderer buildWing(float absoluteDistanceFromCenter2) {
    
    	ModelRenderer subpart = null;
    	boolean reverse = (absoluteDistanceFromCenter2 < 0.0);
    	float xDistance = reverse ? (absoluteDistanceFromCenter2 - wingThickness) : absoluteDistanceFromCenter2;
    	
        // middle part
    	ModelRenderer subwing = new ModelRenderer(this, 0, 0);
    	subwing.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subwing.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subwing.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2), 
    			-(middleWidth / 2), 
    			wingThickness, sectionHeight, middleWidth);
        
    	// up mid
        subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + -sectionHeight, 
    			-(quatreWidth / 2), 
    			wingThickness, sectionHeight, quatreWidth);
    	subwing.addChild(subpart);
    	
    	// top
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + -(sectionHeight * 2), 
    			-(cinqWidth /2 ), 
    			wingThickness, sectionHeight, cinqWidth);
    	subwing.addChild(subpart);
    	
    	// top extended
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + -(sectionHeight * 3), 
    			-(capWidth /2 ), 
    			wingThickness, sectionHeight, capWidth);
    	subwing.addChild(subpart);
    	
    	// down mid
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + sectionHeight, 
    			-(quatreWidth / 2), 
    			wingThickness, sectionHeight, quatreWidth);
    	subwing.addChild(subpart);
    	
    	// bottom
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + (sectionHeight * 2), 
    			-(cinqWidth / 2), 
    			wingThickness, sectionHeight, cinqWidth);
    	subwing.addChild(subpart);
    	
    	// bottom extended
    	subpart = new ModelRenderer(this, 0, 0);
    	subpart.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    	subpart.setRotationPoint(0.0F, 0.0F, 0.0F);
    	subpart.addBox(xDistance, 
    			yoffset_begin + -(sectionHeight/2) + (sectionHeight * 3), 
    			-(capWidth /2 ), 
    			wingThickness, sectionHeight, capWidth);
    	subwing.addChild(subpart);
	    	
	    return subwing;
    }
    
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	if (this.cockpit != null) {
    		this.cockpit.render(scale);
    	}
    	for (ModelRenderer subpart : parts) {
    		subpart.render(scale);
    	}
    }

    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {	
    }
    
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {
    }
}
