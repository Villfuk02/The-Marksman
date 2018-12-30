package marksman.relics;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;

public class RustyMagazine extends CustomRelic {
	public static final String ID = "RustyMagazine";
	
	boolean triggered = false;
	
	public RustyMagazine() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.STARTER, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}	
	
	@Override
    public void atBattleStart() {
		if(triggered)
			return;
		
		triggered = true;
		
		flash();
		AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));               
		for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID == "RustyPistol" && c.canUpgrade()) {                         
            	final float x = MathUtils.random(0.1f, 0.9f) * Settings.WIDTH;
                final float y = MathUtils.random(0.2f, 0.8f) * Settings.HEIGHT;
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                c.upgrade();
            }
        }
		
		for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID == "RustyPistol" && c.canUpgrade()) { 
                c.upgrade();
            }
        }
		
		for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID == "RustyPistol" && c.canUpgrade()) { 
                c.upgrade();
            }
        }
    }
	
	@Override
	public AbstractRelic makeCopy() {
		return new RustyMagazine();
	}
	
}