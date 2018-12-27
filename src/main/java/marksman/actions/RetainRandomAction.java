package marksman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RetainRandomAction extends AbstractGameAction {

    public RetainRandomAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    @Override
    public void update() {
        if (this.isDone) return;
        this.isDone = true;
        
        CardGroup retainable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
        	if(!c.isEthereal && !c.retain) {
        		retainable.addToRandomSpot(c);
        	}
        }
        if(retainable.size() > 0) {
	        final AbstractCard c = retainable.getTopCard();
	        c.retain = true;
	        c.superFlash();
        }
    }
}
