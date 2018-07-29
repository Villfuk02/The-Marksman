package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExplosivesExpertPower extends AbstractPower
{
	public static final String POWER_ID = "ExplosivesExpertPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Every time you use a #yGrenade, draw #b",
    		" cards."
	};
    
    public ExplosivesExpertPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Explosives Expert";
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
    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {
    	if(c.name.contains(" Grenade")) {
    		this.flash();
    		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, amount));
    	}
    }
}
