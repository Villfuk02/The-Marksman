package marksman.cards;


import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;

public class TheLaw extends CustomCard{
	public static final String ID = "TheLaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;

	public TheLaw() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL);
		this.exhaust = true;
		this.retain = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new TheLaw();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			
			this.isInnate = true;
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
		}
	}
	@Override
	public void atTurnStart() {
		retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(mo.hasPower("Weakened")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Weakened"));
			}
			if(mo.hasPower("Vulnerable")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Vulnerable"));
			}
			if(mo.hasPower("Strength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Strength"));
			}
			if(mo.hasPower("Gain Strength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Gain Strength"));
			}
			if(mo.hasPower("Lose Strength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Lose Strength"));
			}
			if(mo.hasPower("Slow")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Slow"));
			}
			if(mo.hasPower("Intangible")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Intangible"));
			}
		}
		if(p.hasPower("Weakened")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Weakened"));
		}
		if(p.hasPower("Vulnerable")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Vulnerable"));
		}
		if(p.hasPower("Strength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Strength"));
		}
		if(p.hasPower("Gain Strength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Gain Strength"));
		}
		if(p.hasPower("Lose Strength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Lose Strength"));
		}
		if(p.hasPower("Slow")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Slow"));
		}
		if(p.hasPower("Intangible")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Intangible"));
		}
	}
}
