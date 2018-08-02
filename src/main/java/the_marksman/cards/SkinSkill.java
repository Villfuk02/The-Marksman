package the_marksman.cards;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class SkinSkill extends CustomCard{
	public static final String ID = "SkinSkill";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 13;
	private static final int BLOCK_UP = 4;	
	
	Random rand = new Random();
	

	public SkinSkill() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;		
	}

	@Override
	public AbstractCard makeCopy() {
		return new SkinSkill();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			this.upgradeBlock(BLOCK_UP);
			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		
		for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK && c.canUpgrade() ) {
            	tmp.addToRandomSpot(c);
            }
        }		
		
		for (int i=0;i<(upgraded?10:2);i++) {
			if (tmp.size() > 0) {
				final AbstractCard c = tmp.getTopCard();
				c.upgrade();
				tmp.removeTopCard();
			}
		}
		tmp.clear();
    }			
}

