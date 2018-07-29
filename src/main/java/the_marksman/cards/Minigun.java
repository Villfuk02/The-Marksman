package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import the_marksman.powers.CritsThisTurn;

public class Minigun extends CustomCard{
	public static final String ID = "Minigun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;	
	private static final int DMG = 1;
	private static final int CRIT = 20;
	private static final int AMT = 8;
	private static final int AMT_UP = 3;
	
	Random rand = new Random();
	

	public Minigun() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseMagicNumber = this.magicNumber = AMT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Minigun();
	}

	@Override
	public void upgrade() {
		this.timesUpgraded++;
		this.name = NAME + "+" + timesUpgraded;
		this.upgradeMagicNumber(AMT_UP);
		
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		
		if(p.getPower("PrecisionPower") != null && p.getPower("PrecisionPower").amount > 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "PrecisionPower"));
		}
		
		for (int i = 0; i < this.magicNumber; i++) {
			int pr = 0;
			int sc = 1;
			int dmg = DMG;
			if(p.getPower("PrecisionPower") != null) {
				pr = p.getPower("PrecisionPower").amount;
			}
			if(p.getPower("ConcentratedPower") != null) sc = 2;
			if(rand.random(100)  < (CRIT + pr) * sc) {
				dmg *= 3;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
				i--;
			}			
			AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, dmg, DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
	    }				
	}
}
