package the_marksman.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayCardFromHandAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
    AbstractCard cardToPlay;

    public PlayCardFromHandAction (AbstractCreature target, AbstractCard card) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.cardToPlay = card;
    }

    public void update() {
        AbstractCard card = this.cardToPlay;
        AbstractDungeon.player.hand.group.remove(card);
        card.freeToPlayOnce = true;
        card.exhaustOnUseOnce = true;
        card.current_y = (-200.0F * Settings.scale);
        card.target_x = (Settings.WIDTH / 2.0F + 200.0F);
        card.target_y = (Settings.HEIGHT / 2.0F);
        card.targetAngle = 0.0F;
        card.lighten(false);
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        AbstractDungeon.player.limbo.group.add(card);
        card.applyPowers();
        AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.QueueCardAction(card, this.target));        //AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.limbo));
        this.isDone = true;
    }

}
