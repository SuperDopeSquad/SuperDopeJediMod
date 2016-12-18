package superdopesquad.superdopejedimod.entity;

import net.minecraft.entity.ai.EntityAIBase;


public class EntityAICustomWander extends EntityAIBase {

	
	private final BaseEntityAnimal _entity;

	
	public EntityAICustomWander(BaseEntityAnimal entity)
	{
	   this._entity = entity;
	   setMutexBits(1);

	    // DEBUG
	    System.out.println("EntityAIWander constructor()");
	}
	
	
	@Override
	public boolean shouldExecute()
	{
	    if (this._entity.getAITarget() == null && this._entity.isBurning())
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
	   System.out.println("EntityAIWander startExecute()");
	}
	
	
	@Override
	public boolean continueExecuting()
	{
	   //theEntity.decrementRearingCounter();;
	   Boolean shouldContinueExecuting = true; // theEntity.getRearingCounter()>0; 
	   if (!shouldContinueExecuting)
	   {
	      //theEntity.setRearing(false);
	      // now attack back
	      this._entity.setAttackTarget(this._entity.getLastAttacker()); 
	   }
	   // DEBUG
	   System.out.println("EntityAIWander continueExecuting =" +shouldContinueExecuting);
	   return (shouldContinueExecuting);
	}
	
	
	
}
