package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class FragGrenade extends CustomCard{
	public static final String ID = "FragGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;	
	private static final int DMG = 6;
	private static final int DMG_UP = 1;
	private static final int AMOUNT = 6;
	private static final int AMOUNT_UP = 4;
	
	Random rand = new Random();
	

	public FragGrenade() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = AMOUNT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FragGrenade();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.name += "+";
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(AMOUNT_UP);
			this.magicNumber = AMOUNT + AMOUNT_UP;
			this.upgraded = true;
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
		for (int i = 0; i < this.magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, 2, DamageType.THORNS),AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
	    }				
	}
}
