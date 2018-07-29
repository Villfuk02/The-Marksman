package the_marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import the_marksman.powers.PrecisionPower;

public class RumBottle extends CustomRelic {
	public static final String ID = "RumBottle";
	
	public RumBottle() {
		super(ID, new Texture("img/relics/"+ID+".png"), 
				RelicTier.COMMON, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
    public void atBattleStart() {
        flash();        
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));        
    }
	
	@Override
    public void atTurnStart() {
        flash();        
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PrecisionPower(AbstractDungeon.player, -10), -10));        
    }
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new RumBottle();
	}
	
}