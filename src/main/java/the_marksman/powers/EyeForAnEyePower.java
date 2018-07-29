package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EyeForAnEyePower extends AbstractPower
{
	public static final String POWER_ID = "EyeForAnEyePower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you take unblocked damage, deal that much damage to a random enemy.",
    		"Every time you take unblocked damage, deal #b",
    		" times as much damage to a random enemy."
	};
    
    public EyeForAnEyePower(final AbstractCreature owner, final int newAmount) {
        this.name = "Eye For An Eye";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(this.amount == 1)
    		this.description = DESCRIPTIONS[0];
    	else
    		this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    } 
    
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
    	if (damageAmount > 0)
    		AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damageAmount*this.amount, DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    	return damageAmount;
    }
}
