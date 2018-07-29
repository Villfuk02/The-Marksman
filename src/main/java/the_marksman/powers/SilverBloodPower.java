package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SilverBloodPower extends AbstractPower
{
	public static final String POWER_ID = "SilverBloodPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you take unblocked damage greater than 2, gain #b",
    		" Regen."
	};
    
    public SilverBloodPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Silver Blood";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    } 
    
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
    	if (damageAmount > 2)
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new RegenPower(owner, this.amount), this.amount));
    	return damageAmount;
    }
}
