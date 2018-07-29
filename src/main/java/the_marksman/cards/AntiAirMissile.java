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

public class AntiAirMissile extends CustomCard{
	public static final String ID = "AntiAirMissile";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;

	public AntiAirMissile() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.isEthereal = true;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new AntiAirMissile();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.name += "+";
			this.upgraded = true;
			this.isEthereal = false;
			this.retain = true;
			this.isInnate = true;
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
		}
	}
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if(mo.hasPower("Ritual")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Ritual"));
			}
			if(mo.hasPower("Flight")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Flight"));
			}
		}
	}
}
