package superdopesquad.superdopejedimod.entity.ai;


import java.util.Arrays;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.player.EntityPlayer;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassInfo;
import superdopesquad.superdopejedimod.faction.FactionInfo;


public class EntityAIAttackMeleeFactionAware extends EntityAIAttackMelee {

	
	private FactionInfo _factionToAttack;
	
	
	public EntityAIAttackMeleeFactionAware(EntityCreature creature, double speedIn, boolean useLongMemory, FactionInfo factionToAttack) {
		
		super(creature, speedIn, useLongMemory);	
		
		this._factionToAttack = factionToAttack;
	}
	
	
	@Override
	public boolean shouldExecute() {
	
		EntityLivingBase entity = this.attacker.getAttackTarget();
		if (entity == null) {
			return false;
		}
				
		if (entity instanceof EntityPlayer) {
			
			// Error handling: if factionsToAttack was not initialized properly.
			if (this._factionToAttack == null) {
				return false;
			}
			
			// Test to see if target's factionInfo is in my list of things to attack.
			EntityPlayer player = (EntityPlayer) entity;
			boolean shouldAttack = SuperDopeJediMod.classManager.isPlayerInFaction(player, this._factionToAttack);
			return shouldAttack;
		}
			
		return super.shouldExecute();
	}
}
