package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PerfectionPower extends AbstractPower
{
	public static final String POWER_ID = "PerfectionPower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"Whenever you deal a #yCritical hit, play the card again. NL Works #b",
    		" times per turn. NL ( #b",
    		" uses left.)"
	};
    
    public int usesLeft = 0;
    public AbstractCard card;
    public UseCardAction action;
    
    public PerfectionPower(final AbstractCreature owner, final int newAmount) {
        this.name = "Perfect Form";
        this.ID = POWER_ID;
        this.owner = owner;     
        this.img = new Texture("img/powers/"+name+".png");
        this.amount = newAmount;   
        this.updateDescription();
    }  
    
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + usesLeft + DESCRIPTIONS[2];
    }  
    
    @Override
    public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
    	if(usesLeft > 0 && target == AbstractDungeon.player && power.ID == "CritsThisTurn") {
	    	DoubleCard();
		}
    }  
    
    @Override
    public void atStartOfTurn() {
    	this.usesLeft = this.amount;
    	this.updateDescription();
    }
    
    @Override
    public void onUseCard(final AbstractCard c, final UseCardAction a) {
    	this.card = c.makeStatEquivalentCopy();
        this.action = a;
        
        card.purgeOnUse = false;
    }
    
    private void DoubleCard() {
    	if (card != null && !card.purgeOnUse && this.amount > 0) {
	    	this.flash();    	
	    	usesLeft--;
	    	this.updateDescription();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            final AbstractCard tmp = card.makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0f;
            tmp.freeToPlayOnce = true;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }  
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
            if (tmp.cardID.equals("Rampage")) {
                AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(card, tmp.magicNumber));
            }
            else if (tmp.cardID.equals("Genetic Algorithm")) {
                AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(card.cardID, card.misc + card.magicNumber, card.magicNumber));
            }
        }
    }
}
