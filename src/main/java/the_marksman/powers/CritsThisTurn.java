package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CritsThisTurn extends AbstractPower
{
	public static final String POWER_ID = "CritsThisTurn";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"This turn, you've dealt #b",
    		" Critical hits.",
    		" Critical hit."
	};
    
    public CritsThisTurn(final AbstractCreature owner, final int newAmount) {
        this.name = "Crits This Turn";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.canGoNegative = true;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(this.amount == 1) {
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    	}else {
        	this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];    		
    	}
    }   
    
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
