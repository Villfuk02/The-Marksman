package marksman.actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import marksman.powers.BurningPower;

public class ApplyBurningAction extends AbstractGameAction
{
    public ApplyBurningAction(final AbstractCreature target, final AbstractCreature source, final int amount) {
    	this.target = target;
    	this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.amount = amount;
        this.duration = 0.01f;
    }
    
    @Override
    public void update() {    	
		int p = target.currentBlock;
		if(p >= amount) {
			p = amount;	
		}	else {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new BurningPower(target, amount - p), amount - p));
		}	
		if(p > 0)
			AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, p, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        this.isDone = true;
    }
}

