package the_marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class ScaleOfInjustice extends CustomRelic {
	public static final String ID = "ScaleOfInjustice";
	public static final int DMG = 5;
	
	public ScaleOfInjustice() {
		super(ID, new Texture("img/relics/"+ID+".png"), 
				RelicTier.UNCOMMON, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
    public void onPlayerEndTurn() {
        flash();    
        int hp = 9999;
        AbstractMonster t = null;
        for (final AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	if(hp > m.currentHealth && m.currentHealth > 0) {
        		hp = m.currentHealth;
        		t = m;
        	}
        }  
        if (hp > AbstractDungeon.player.currentHealth) {
        	AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,new DamageInfo(null, DMG, DamageType.THORNS),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }else {
        	AbstractDungeon.actionManager.addToBottom(new DamageAction(t ,new DamageInfo(null, DMG, DamageType.THORNS),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new ScaleOfInjustice();
	}
	
}