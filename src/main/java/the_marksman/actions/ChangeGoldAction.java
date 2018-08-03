package the_marksman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class ChangeGoldAction extends AbstractGameAction {
    private int amount;
    private AbstractMonster target;

    public ChangeGoldAction(int amount, final AbstractMonster target) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.amount = amount;
        this.target = target;
    }

    @Override
    public void update() {
        if (this.isDone) return;
        this.isDone = true;
        if (amount > 0) {
            AbstractDungeon.player.gainGold(amount);
            for (int i = 0; i < amount; i++) {
            	AbstractDungeon.effectsQueue.add(new GainPennyEffect(target, target.hb.cX, target.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true));
            }
        }else if(amount < 0) {
        	AbstractDungeon.player.loseGold(-amount);
        }
    }
}
