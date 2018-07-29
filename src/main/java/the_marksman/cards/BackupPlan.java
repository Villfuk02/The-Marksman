package the_marksman.cards;


import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class BackupPlan extends CustomCard{
	public static final String ID = "BackupPlan";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;

	public BackupPlan() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new BackupPlan();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.name += "+";
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.upgraded = true;
			this.retain = true;
		}
	}
	
	@Override
	public void atTurnStart() {
		if(upgraded)
			retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom(new SeekAction(1));
	}
}
