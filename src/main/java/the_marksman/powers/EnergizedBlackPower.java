package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EnergizedBlackPower extends AbstractPower
{
	public static final String POWER_ID = "EnergizedBlackPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"At the start of next turn, gain #b",
    		"At the start of next turn, lose #b",
    		" #yEnergy."
	};
    
    public EnergizedBlackPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Energized";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        if(amount > 999)
        	amount = 999;        
        this.canGoNegative = true;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(this.amount < 0) {
    		this.description = DESCRIPTIONS[1] + -this.amount + DESCRIPTIONS[2];
    	}else {
        	this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];    		
    	}
    }   
    
    @Override
    public void atStartOfTurn() {
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
