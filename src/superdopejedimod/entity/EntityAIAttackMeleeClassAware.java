package superdopesquad.superdopejedimod.entity;


import java.util.Arrays;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.player.EntityPlayer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.playerclass.ClassInfo;


public class EntityAIAttackMeleeClassAware extends EntityAIAttackMelee {

	
	
	
		
		super(creature, speedIn, useLongMemory);	
		
	}
	
	
	@Override
	public boolean shouldExecute() {
	
		EntityLivingBase entity = this.attacker.getAttackTarget();
		if (entity == null) {
			return false;
		}
				
		if (entity instanceof EntityPlayer) {
			
			ClassInfo classInfo = SuperDopeJediMod.classManager.getPlayerClass((EntityPlayer)entity);
			
			// Debug info.
			String attackTargetName = entity.getName();
			String className = classInfo.getName();
			//System.out.println("DEBUG: Contemplating attacking " + attackTargetName + ", of faction " + factionName );
			
			// Error handling: if factionsToAttack was not initialized properly.
				//System.out.println("DEBUG: There is noone i like to attack");
				return false;
			}
			
			// Test to see if target's classInfo is in my list of things to attack.
			//System.out.println("DEBUG: Attack? " + shouldAttack);
			return shouldAttack;
		}
			
		return super.shouldExecute();
	}
}
