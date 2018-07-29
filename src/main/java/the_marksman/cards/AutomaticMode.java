package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class AutomaticMode extends CustomCard{
	public static final String ID = "AutomaticMode";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int MAGIC = 2;
	private static final int MAGIC_UP = -1;

	public AutomaticMode() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new AutomaticMode();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(MAGIC_UP);
			this.exhaust = false;
		}
	}	
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() > 0) {
			return false;
		}
		return super.canUse(p, m);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainStrengthPower(p, this.magicNumber), this.magicNumber));
	}
}
