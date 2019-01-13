package marksman.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.FlameParticleEffect;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.actions.ApplyBurningAction;
import marksman.patches.AbstractCardEnum;

public class Flamethrower extends CustomCard{
	public static final String ID = "Flamethrower";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;
	private static final int BURNING = 3;
	private static final int BURNING_UP = 1;
	

	public Flamethrower() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseMagicNumber = this.magicNumber = BURNING;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Flamethrower();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(BURNING_UP);
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
		int effect = this.energyOnUse;
		if (!this.freeToPlayOnce) {
            p.energy.use(effect);
        }
		if (p.hasRelic("Chemical X")) {
	         effect += 2;
	         p.getRelic("Chemical X").flash();
	    }
		for (int i = 0; i < effect; i++) {
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {				
				AbstractDungeon.actionManager.addToBottom(new ApplyBurningAction(mo, p, this.magicNumber));
				
		    }		
		}
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			for(int j = 0; j < 100; j++) {
				AbstractDungeon.effectsQueue.add(new FlameParticleEffect(mo.hb.cX, mo.hb.cY));
			}
		}
	}
}
