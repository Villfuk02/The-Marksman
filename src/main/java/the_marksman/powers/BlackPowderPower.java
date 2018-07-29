package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;

import the_marksman.cards.Grenade;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlackPowderPower extends AbstractPower
{
	public static final String POWER_ID = "BlackPowderPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time a card is exhausted, shuffle #yHE #yGrenade into your draw pile.",
    		"Every time a card is exhausted, shuffle #b",
    		" #yHE #yGrenades into your draw pile."
	};
    
    public BlackPowderPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Black Powder";
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
    public void onExhaust(AbstractCard c) {
    	AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Grenade(), 1, true, false));
    }
   
    
    
}
