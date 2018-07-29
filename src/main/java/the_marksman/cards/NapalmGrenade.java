package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
import the_marksman.powers.BurningPower;

public class NapalmGrenade extends CustomCard{
	public static final String ID = "NapalmGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 3;
	private static final int DMG_UP = 1;
	private static final int BURNING = 2;
	private static final int BURNING_UP = 2;
	
	Random rand = new Random();
	

	public NapalmGrenade() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = BURNING;
	}

	@Override
	public AbstractCard makeCopy() {
		return new NapalmGrenade();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(BURNING_UP);
			this.magicNumber = BURNING + BURNING_UP;
			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
		for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new BurningPower(mo, this.magicNumber), this.magicNumber));
	    }				
	}
}
