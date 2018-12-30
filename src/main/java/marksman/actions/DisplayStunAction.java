package marksman.actions;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

public class DisplayStunAction extends AbstractGameAction {
    private AbstractMonster target;

    public DisplayStunAction(AbstractMonster target) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.target = target;
    }

    @Override
    public void update() {
        if (this.isDone) return;
        this.isDone = true;
        if(target.hasPower(StunMonsterPower.POWER_ID)) {
        	target.setMove(target.nextMove, Intent.STUN);
        	target.createIntent();
		}
    }
}
