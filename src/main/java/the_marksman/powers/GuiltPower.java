package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GuiltPower extends AbstractPower
{
	public static final String POWER_ID = "GuiltPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the start of each turn, gain #b",
    		" Energy. Lose #b",
    		" Strength for one turn every time you play an Attack."
	};
    
    public GuiltPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Guilt";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.canGoNegative = true;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {    	
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]; 
    }   
    
    @Override
    public void atStartOfTurn() {
    	this.flash();
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
    }
    
    @Override
    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {
    	if(c.type == AbstractCard.CardType.ATTACK) {
    		this.flashWithoutSound();
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new StrengthPower(owner, -this.amount), -this.amount));
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new GainStrengthPower(owner, this.amount), this.amount));
    	}
    }
}
