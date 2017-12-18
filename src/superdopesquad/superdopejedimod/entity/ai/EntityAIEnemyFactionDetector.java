package superdopesquad.superdopejedimod.entity.ai;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.FactionInfo;


/**
 * 
 *  This AI module is meant to be installed in the "targetTasks" of an entity. It simply identifies a potential target, and if so, sets the target by
 * calling setAttackTarget() on the entity that is executing this AI. How to attack the target is handled by a seperate AI module; here, we just
 * identify the target.
 *
 */
public class EntityAIEnemyFactionDetector extends EntityAITarget {
	
	/* Configurable properties. */
    private final boolean 					entityCallsForHelp;
    private final @Nullable FactionInfo 	factionToDetect;
    private final float						distanceToDetect;

    
    /**
     * 
     */
    public EntityAIEnemyFactionDetector(EntityCreature creatureIn, boolean entityCallsForHelpIn, boolean checkSight, float distanceToDetectIn,
    		@Nullable FactionInfo factionToDetectIn) {
        super(creatureIn, checkSight);
        this.entityCallsForHelp = entityCallsForHelpIn;
        this.factionToDetect = factionToDetectIn;
        this.distanceToDetect = distanceToDetectIn;
        
        // TODO: figure out what this does
        this.setMutexBits(1);
    }

    
    /**
     * Called both before and during execution.
     */
    @Override
    public boolean shouldExecute() {
    	
        // Scan the area for any players.
        for (EntityPlayer player : this.taskOwner.world.getEntitiesWithinAABB(EntityPlayer.class, 
        		(new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, 
        				this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(this.distanceToDetect, 10.0D, this.distanceToDetect)))
        {
        	// TODO: Instead of attacking the first one we find in the scan, choose the closest one.
            if (this.shouldAttack(player)) {
            	this.taskOwner.setAttackTarget(player);
            	return true;
            }
        }
       
       // Could not find anyone to attack.
       return false;
    }

    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
    	        
        // Set state machine for when we are executing. 
        this.target = this.taskOwner.getAttackTarget();
        this.unseenMemoryTicks = 300;
        
        if (this.entityCallsForHelp) {
            this.alertOthers();
        }

        //System.out.println("EntityAIEnemyFactionDetector: starting with this=" + this + ", taskOwner=" + this.taskOwner + ", target="+ this.target);
        super.startExecuting();
    }
    
    
    /**
     * Resets the task, called when the task manager is ending our execution.
     */
    @Override
    public void resetTask() {
    	// Clear the state machine.
    	//System.out.println("EntityAIEnemyFactionDetector: resetting");
    	this.target = null;
    	this.taskOwner.setAttackTarget(null);
    }


    /**
     * A static method used to see if an entity is a suitable target through a number of checks.
     */
    public boolean shouldAttack(EntityPlayer target)
    {
    	EntityLiving attacker = this.taskOwner;
  
    	// If no target, or the target is us, or ifs it already dead, then forget about it!
        if ((target == null) || !target.isEntityAlive()) {
            return false;
        }
        
        // Make sure the team logic is not broken.
        if (!attacker.canAttackClass(target.getClass()) || attacker.isOnSameTeam(target)) {
            return false;
        }
       
        // If we own this, forget it.
        if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null) {
            if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(target.getUniqueID())) {
                return false;
            }

            if (target == ((IEntityOwnable)attacker).getOwner()) {
                return false;
            }
        }
        
        // If we are forced to use vision-only, verify we are in line of sight.
        if (this.shouldCheckSight && !attacker.getEntitySenses().canSee(target)) {
       	  return false;
        }
        
        // If they are not in the faction we are looking for, bail.
        if ((this.factionToDetect == null) || !SuperDopeJediMod.classManager.isPlayerInFaction(target, this.factionToDetect)){
			return false;
		}
        
        // Time to Attack!
        return true;
    }
    
    
    /**
     * 
     */
    protected void alertOthers() {

        double dist = this.getTargetDistance();
        AxisAlignedBB alertBoundary = new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, 
        		this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand(dist, 10.0D, dist);

        for (EntityCreature entitycreature : this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), alertBoundary))
        {
        	// Skip alerting them if they are actually us, or if they are already engaged with someone else.
            if ((entitycreature == this.taskOwner)  || (entitycreature.getAttackTarget() != null))
            	continue;
            
            // If we are tameable, and this object is not tamed by the same owner, then skip it - we only keep our alert-group to 
            // entities tamed by the same owner.
            if ((this.taskOwner instanceof EntityTameable) && 
            				(((EntityTameable)this.taskOwner).getOwner() != ((EntityTameable)entitycreature).getOwner()))
            				 continue;
            	
            // If they are on the same team, bail.
            if (entitycreature.isOnSameTeam(this.target)) 
            	continue;
            	
            // If we got this far, alert them by setting their attack target.	
            entitycreature.setAttackTarget(this.target);
        }
    }
}