package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class AntiFlame extends CustomCard{
	public static final String ID = "AntiFlame";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 10;
	private static final int BLOCK_UP = 2;
	private static final int MAGIC = 2;
	//private static final int MAGIC_UP = 4;

	public AntiFlame() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new AntiFlame();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.name += "+";
			this.upgradeBlock(BLOCK_UP);
			//this.upgradeMagicNumber(MAGIC_UP);
			//this.magicNumber = MAGIC + MAGIC_UP;
			this.upgraded = true;	
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {				
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), this.magicNumber));  
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c.cardID == "Burn")
				AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c));
		}
	}
}
