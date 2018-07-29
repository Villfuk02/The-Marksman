package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;
import the_marksman.actions.RetainRandomAction;

public class CombatTrousers extends CustomCard{
	public static final String ID = "CombatTrousers";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int BLOCK_UP = 4;

	public CombatTrousers() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new CombatTrousers();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.name += "+";
			this.upgradeBlock(BLOCK_UP);
			this.upgraded = true;	
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {				
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block)); 
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p,p,1,true));
		AbstractDungeon.actionManager.addToBottom(new RetainRandomAction());
	}
}
