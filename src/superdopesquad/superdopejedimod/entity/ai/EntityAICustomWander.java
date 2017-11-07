package superdopesquad.superdopejedimod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.entity.BaseEntityAnimal;



public class EntityAICustomWander extends EntityAIBase {

	
	private final BaseEntityAnimal _entity;

	
	public EntityAICustomWander(BaseEntityAnimal entity)
	{
	   this._entity = entity;
	   setMutexBits(1);

	    // DEBUG
	    //System.out.println("EntityAIWander constructor()");
	}
	
	
	@Override
	public boolean shouldExecute()
	{
	    //if (this._entity.getAITarget() == null && this._entity.isBurning())
		if (this._entity.getAttackTarget() == null && this._entity.isBurning())
	    {
	        return false;
	    }
	   // else
	   // {
	        //if (this._entity.isRearingFirstTick()) // only set the first tick that is rearing
	        //{
	            return true;                    
	        //}
//	        else
//	        {
//	            return false;
//	        }
	    //}       
	}
	
	
	@Override
	public void startExecuting()
	{
	   // DEBUG
	   //System.out.println("EntityAIWander startExecute()");
	}
	
	
	@Override
	public boolean shouldContinueExecuting()
	{
	   //theEntity.decrementRearingCounter();;
	   Boolean shouldContinueExecuting = true; // theEntity.getRearingCounter()>0; 
	   if (!shouldContinueExecuting)
	   {
	      //theEntity.setRearing(false);
	      // now attack back
	      //this._entity.setAttackTarget(this._entity.getLastAttacker()); 
	      this._entity.setAttackTarget(this._entity.getAttackingEntity()); 
	   }
	   // DEBUG
	   //System.out.println("EntityAIWander continueExecuting =" +shouldContinueExecuting);
	   return (shouldContinueExecuting);
	}
}
