package marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import marksman.MarksmanMod;

public class IgnitePower extends AbstractPower
{
	public static final String POWER_ID = "IgnitePower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Whenever an #yAttack deals unblocked damage, apply #b",
    		" #yBurning."
	};
    
    public IgnitePower(final AbstractCreature owner, final int newAmount) {
        this.name = "Ignite";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture(MarksmanMod.POWER_IMG_PATH + name + ".png");
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    } 
    
    @Override
    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new BurningPower(target, this.amount), this.amount, true));
        }
    }
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
    
}
