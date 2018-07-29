package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class TheLaw extends CustomCard{
	public static final String ID = "TheLaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;

	public TheLaw() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
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
			if(mo.hasPower("Weak")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Weak"));
			}
			if(mo.hasPower("Vulnerable")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Vulnerable"));
			}
			if(mo.hasPower("Strength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Strength"));
			}
			if(mo.hasPower("GainStrength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "GainStrength"));
			}
			if(mo.hasPower("LoseStrength")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "LoseStrength"));
			}
			if(mo.hasPower("Slow")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Slow"));
			}
			if(mo.hasPower("Intangible")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Intangible"));
			}
		}
		if(p.hasPower("Weak")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Weak"));
		}
		if(p.hasPower("Vulnerable")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Vulnerable"));
		}
		if(p.hasPower("Strength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Strength"));
		}
		if(p.hasPower("GainStrength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "GainStrength"));
		}
		if(p.hasPower("LoseStrength")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "LoseStrength"));
		}
		if(p.hasPower("Slow")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Slow"));
		}
		if(p.hasPower("Intangible")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Intangible"));
		}
	}
}
