package marksman.relics;


import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import basemod.abstracts.CustomRelic;
import marksman.MarksmanMod;
import marksman.cards.RustyPistol;
import marksman.cards.TrustyPistol;

public class TrustyMagazine extends CustomRelic {
	public static final String ID = "TrustyMagazine";	
	
	public TrustyMagazine() {
		super(ID, new Texture(MarksmanMod.RELIC_IMG_PATH + ID + ".png"),
				RelicTier.BOSS, LandingSound.SOLID); 
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
    public void obtain()
    {
        if (AbstractDungeon.player.hasRelic(RustyMagazine.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(RustyMagazine.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
	
	@Override
    public void onEquip() {		
		int n = 0;
		final Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator();
        while (i.hasNext()) {
            final AbstractCard e = i.next();
            if (e.cardID == RustyPistol.ID) {
                i.remove();
                n++;
            }
        }
        for (int j = 0; j < n; ++j) {
            AbstractCard card = new TrustyPistol();
            UnlockTracker.markCardAsSeen(card.cardID);
            card.isSeen = true;
            final float x = MathUtils.random(0.1f, 0.9f) * Settings.WIDTH;
            final float y = MathUtils.random(0.2f, 0.8f) * Settings.HEIGHT;
            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(), x, y));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
            AbstractDungeon.player.masterDeck.addToTop(card);
        }
	}
	
	@Override
    public boolean canSpawn() {
		return AbstractDungeon.player.hasRelic(RustyMagazine.ID);
	}
    
	
	@Override
	public AbstractRelic makeCopy() {
		return new TrustyMagazine();
	}
	
}