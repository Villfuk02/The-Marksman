package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.powers.CorrosionPower;

public class BatteryAcid extends CustomCard{
	public static final String ID = "BatteryAcidBlack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;

	public BatteryAcid() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new BatteryAcid();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		if(upgraded)
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(3));
		else
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));   
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CorrosionPower(p, 1), 1));
	}
}
