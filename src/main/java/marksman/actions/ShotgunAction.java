package marksman.actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.*;

import marksman.powers.CritsThisTurn;

import com.megacrit.cardcrawl.actions.utility.*;

public class ShotgunAction extends AbstractGameAction
{
    private DamageInfo info;
    private int numTimes;
    private int crit;
    private int action;
    
    
    public ShotgunAction(final AbstractCreature target, final DamageInfo info, final int numTimes, final int crit, final int action) {
        this.info = info;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = action == 4?AttackEffect.SLASH_HORIZONTAL:AttackEffect.BLUNT_LIGHT;
        this.duration = 0.01f;
        this.numTimes = numTimes;
        this.crit = crit;
        this.action = action;
    }
    
    @Override
    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }
        if (this.target.currentHealth > 0) {
            this.target.damageFlash = true;
            this.target.damageFlashFrames = 4;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            
            DamageInfo tempInfo = new DamageInfo(AbstractDungeon.player, info.base, info.type);
            
			if(AbstractDungeon.cardRandomRng.random(99)  < this.crit) {
				tempInfo = new DamageInfo(AbstractDungeon.player, info.base*3, info.type);
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new CritsThisTurn(AbstractDungeon.player, 1), 1));
				switch (action) {
				case 0:
					AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
					break;
				case 1:
					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -1), -1));
					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GainStrengthPower(AbstractDungeon.player, 1), 1));
					break;
				case 2:
					AbstractDungeon.actionManager.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, true));
					break;
				case 3:
					this.numTimes++;
					break;
				case 4:
					break;
				}
			}

            this.target.damage(tempInfo);
            if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                --this.numTimes;
                AbstractDungeon.actionManager.addToTop(new ShotgunAction(AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes, crit, action));
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.2f));
        }
        this.isDone = true;
    }
}

