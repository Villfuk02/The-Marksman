package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import the_marksman.powers.CritsThisTurn;

public class Revolver extends CustomCard{
	public static final String ID = "Revolver";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;	
	private static final int DMG = 3;
	//private static final int DMG_UP = 3;
	private static final int CRIT = 5;
	private static final int CRIT_UP = 2;
	
	Random rand = new Random();
	

	public Revolver() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = CRIT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Revolver();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.name += "+";
			//this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(CRIT_UP);
			this.magicNumber = CRIT + CRIT_UP;
			this.upgraded = true;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			int pr = 0;
			int sc = 1;
			int dmg = this.damage;
			if(p.getPower("PrecisionPower") != null) {
				pr = p.getPower("PrecisionPower").amount;
			}
			if(p.getPower("ConcentratedPower") != null) sc = 2;
			if(rand.random(100)  < (35 + pr) * sc) {
				dmg *= this.magicNumber;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			}	
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));			    	
        }		
	}
}
