package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawDownPower;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class Hideout extends CustomCard{
	public static final String ID = "Hideout";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int BLOCK = 5;
	private static final int BLOCK_UP = 3;

	public Hideout() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Hideout();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.upgradeBlock(BLOCK_UP);
			
		}
	}	

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int pr = 0;
		if(p.getPower("CritsThisTurn") != null) {
			pr = p.getPower("CritsThisTurn").amount;
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawDownPower(p, 1), 1));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, pr*this.block));
		
	}
}
