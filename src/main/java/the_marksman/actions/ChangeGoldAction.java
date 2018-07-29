package the_marksman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChangeGoldAction extends AbstractGameAction {
    private int amount;

    public ChangeGoldAction(int amount) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.isDone) return;
        this.isDone = true;
        if (amount > 0) {
            AbstractDungeon.player.gainGold(amount);
        }else if(amount < 0) {
        	AbstractDungeon.player.loseGold(-amount);
        }
    }
}
