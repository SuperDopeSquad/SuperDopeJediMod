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
public class XWingFighterModel extends ModelBase {
	
	/* Constants */
	public static final int TEXTURE_WIDTH = 256; 
	public static final int TEXTURE_HEIGHT = 256;
    
	//public ModelRenderer cockpit;
	public List<ModelRenderer> renderers = new ArrayList<ModelRenderer>();
	
//	 private static final int cockpitWidth = 48;
//   private static final int wingThickness = 4;
//   private static final int maxwingwidth = 96;
//   private static final int sectionHeight = 32;
//   private static final float absoluteDistanceFromCenter = 48.0F + 24.0F;
//    
//   private static final int middleWidth = cockpitWidth * 2;
//   private static final int quatreWidth = middleWidth - 6;
//   private static final int cinqWidth = quatreWidth - 6;
//   private static final int capWidth = quatreWidth - 6;
//   //private static final float yoffset_begin = -10F; //-88.0f;
   
   private static final float _globalOffsetY = -20F; //-88.0f;
   
   private static final int _fuselageWidth = 36;
   private static final int _fuselageHeight = 36; 

   
	/* Constructors */
    public XWingFighterModel() {   
    	
    	this.renderers.add(buildFuselage());
    
        // Wings.
        this.renderers.add(buildWing(true, true));
        this.renderers.add(buildWing(true, false));
        this.renderers.add(buildWing(false, true));
        this.renderers.add(buildWing(false, false));
    }
    
    
    /* */
    private ModelRenderer buildFuselage() {
    	
    	ModelRenderer renderer = null;

    	int fuselageDepth = 96;
    	
    	int fuselage2Depth = 96;
    	int fuselage2Width = this._fuselageWidth - 8;
    	
    	int fuselageNoseDepth = 36;
    	int fuselageNoseDiameter = fuselage2Width + 2;
    	
    	int cockpitWidth = this._fuselageWidth - 8;
    	int cockpitHeight = 32;
    	int cockpitDepth = fuselage2Depth / 4;
    	
    	int r2UnitWidth = 12;
      	int r2UnitHeight = 6;
    	int r2UnitDepth = 12;
    	
    	float originX = -(this._fuselageWidth / 2);
     	float originY = this._globalOffsetY - (this._fuselageHeight / 2);
     	float originZ = -(fuselageDepth / 2);
    	
    	// Main cube
     	renderer = new ModelRenderer(this, 0, 0);
     	renderer.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);
     	renderer.setRotationPoint(0.0F, 0.0F, 0.0F);
     	renderer.setRotationPoint(originX, originY, originZ);
        
    	// Main rear fuselage box where the wings connect.
     	renderer.addBox(0.0F, 0.0F, 0.0F, this._fuselageWidth, this._fuselageHeight, fuselageDepth);
    	
    	// Add another box, elongating the bow.
     	renderer.addBox(4F, 4F, -0F, //-(float)fuselageDepth, 
    			fuselage2Width, this._fuselageHeight - 8, -fuselage2Depth);
    	
    	// Add another box, capping the bow.
     	int fuselageCapX = ((this._fuselageWidth - fuselageNoseDiameter) / 2);
     	renderer.addBox(fuselageCapX, fuselageCapX, -(float)(fuselage2Depth - 10F),
    			fuselageNoseDiameter, fuselageNoseDiameter, (-fuselageNoseDepth));
        
    	// Cockpit.
     	renderer.addBox(4F, cockpitHeight, -0F,
    			cockpitWidth, -cockpitHeight, (-cockpitDepth));
   
    	// R2 Unit.
    	int r2UnitOriginX = (this._fuselageWidth - r2UnitWidth) / 2;
    	int r2UnitOriginZ = (fuselageDepth - r2UnitDepth) / 2;
    	renderer.addBox(r2UnitOriginX, -r2UnitHeight, r2UnitOriginZ,
    			r2UnitWidth, r2UnitHeight, r2UnitDepth);
   	
    	return renderer;
    }
    
    
    /* */
   private ModelRenderer buildWing(boolean isBottomWing, boolean isLeftWing) {
    		   
    	// middle part
    	ModelRenderer renderer = new ModelRenderer(this, 0, 0);
    	renderer.setTextureSize(TEXTURE_WIDTH, TEXTURE_HEIGHT);

    	int wingWidth = 128;
    	int wingHeight = 4;
    	int wingDepth = 36;
    	float wingOffsetFromCenterOfFuselage = 10F;
    	
    	int engineDiameter = 20;
    	int engineDepth = wingDepth * 5 / 4;
     	
    	float originX = (this._fuselageWidth / 2); // + 20F; // = fuselageWidth + 20F;
    	float originY = this._globalOffsetY + wingOffsetFromCenterOfFuselage;
    	float originZ = -(wingDepth / 2);
    	float rotateAngleZ = ((float)Math.PI / 8);
    	
    	float originEngineX = 4F;
    	float originEngineY = wingHeight;
    	float originEngineZ = 0F;
    	
    	// Laser gun box.
    	int laserGunBoxDiameter = 8;
    	int laserGunBoxDepth = wingDepth + 8;
    	float originLaserGunBoxX = wingWidth - laserGunBoxDiameter;
    	float originLaserGunBoxY = wingHeight ; // + laserGunBoxDiameter;
    	float originLaserGunBoxZ = -4;
         	
    	
    	if (isBottomWing) {
    	
    		if (isLeftWing) { // Bottom Left Wing
    		
     			originX = -originX; 
    			originY = originY + wingHeight;
    			rotateAngleZ = (float)Math.PI - rotateAngleZ;		
    			originEngineY = originEngineY - ( wingHeight + engineDiameter);		
    			originLaserGunBoxY = - laserGunBoxDiameter;
     		}
    		
    		else { // Bottom Right Wing
    			
    			originEngineY = -(engineDiameter) + (wingHeight + engineDiameter);
    		} 
    	}
    	
    	else { 
    		
 			originY = (originY - (2 * wingOffsetFromCenterOfFuselage));
    		
    		if (isLeftWing) { // Top Left Wing
  		
    			originX = -originX;  //- wingHeight; // - wingWidth;
    			originY = originY + wingHeight;
    			rotateAngleZ = (float)Math.PI + rotateAngleZ;
    		}
    		
    		else { // Top Right Wing
    			
    			rotateAngleZ = - rotateAngleZ;
    			originEngineY = -(engineDiameter);
    			originLaserGunBoxY = - laserGunBoxDiameter;
    		}
    	}
    	
    	// Measuring out the laser gun pole, after laser gun box is tweaked.
    	int laserGunPoleDiameter = 4;
     	int laserGunPoleDepth = wingDepth + 8;
    	float originLaserGunPoleX = originLaserGunBoxX + ((laserGunBoxDiameter - laserGunPoleDiameter) / 2);
    	float originLaserGunPoleY = originLaserGunBoxY + ((laserGunBoxDiameter - laserGunPoleDiameter) / 2);
    	float originLaserGunPoleZ = originLaserGunBoxZ;
    	
    	renderer.rotateAngleZ = rotateAngleZ;
    	renderer.setRotationPoint(originX, originY, originZ);
    	
    	int backWingDepth = wingDepth / 2 / 5;
    	
    	// Wing
    	renderer.addBox(
    			0.0F, 0.0F, 0.0F, 
    			wingWidth, wingHeight, wingDepth); 
    	
    	// Backwing 1
    	renderer.addBox(0.0F, 0.0F, wingDepth, 
    			wingWidth * 5 / 6, wingHeight, backWingDepth); 
    	
     	// Backwing 2
    	renderer.addBox(0.0F, 0.0F, wingDepth + ( 1 * backWingDepth), 
    			wingWidth * 4 / 6, wingHeight, backWingDepth); 
   
     	// Backwing 3
    	renderer.addBox(0.0F, 0.0F, wingDepth + ( 2 * backWingDepth), 
    			wingWidth * 3 / 6, wingHeight, backWingDepth); 
   
     	// Backwing 4
    	renderer.addBox(0.0F, 0.0F, wingDepth + ( 3 * backWingDepth), 
    			wingWidth * 2 / 6, wingHeight, backWingDepth); 
   
     	// Backwing 5
    	renderer.addBox(0.0F, 0.0F, wingDepth + ( 4 * backWingDepth), 
    			wingWidth * 1 / 6, wingHeight, backWingDepth); 
   
    	// Engine Front.
    	renderer.addBox(originEngineX, originEngineY, originEngineZ, engineDiameter, engineDiameter, engineDepth);
  
     	// Engine Back.
    	int engineBackDiameter = 14;
    	int engineBackDepth = engineDepth;
    	float originEngineBackX = originEngineX + ((engineDiameter - engineBackDiameter) / 2);
    	float originEngineBackY = originEngineY;
    	float originEngineBackZ = originEngineZ + engineDepth;
    	renderer.addBox(originEngineBackX, originEngineBackY, originEngineBackZ, engineBackDiameter, engineBackDiameter, engineBackDepth);
   
    	// Laser gun box.
      	renderer.addBox(originLaserGunBoxX, originLaserGunBoxY, originLaserGunBoxZ, 
      			laserGunBoxDiameter, laserGunBoxDiameter, laserGunBoxDepth);
        
      	// laser gun pole.
       	renderer.addBox(originLaserGunPoleX, originLaserGunPoleY, (originLaserGunPoleZ), 
       			laserGunPoleDiameter, laserGunPoleDiameter, -laserGunPoleDepth);
        
    	// laser gun dish.
       	renderer.addBox(originLaserGunBoxX, originLaserGunBoxY, (originLaserGunPoleZ - laserGunPoleDepth + 8), 
       			laserGunBoxDiameter, laserGunBoxDiameter, -2);
     
	    return renderer;
    }
    
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	
    	for (ModelRenderer renderer : this.renderers) {
    		if (renderer != null) {
    			renderer.render(scale);
    		}
    	}
    }

    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {	
    }
    
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {
    }
}
