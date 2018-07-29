package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class FryingPan extends CustomCard{
	public static final String ID = "FryingPan";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;	
	private static final int DMG = 7;
	private static final int DMG_UP = 3;
	private static final int BLOCK = 6;
	private static final int BLOCK_UP = 3;
	
	Random rand = new Random();
	

	public FryingPan() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF_AND_ENEMY);
		this.baseDamage = DMG;
		this.baseBlock = BLOCK;
		this.retain = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FryingPan();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeBlock(BLOCK_UP);
			
		}
	}	
	
	@Override
	public void atTurnStart() {
		retain = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			if(p.currentBlock > 0) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}else {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			}			
	}
}
