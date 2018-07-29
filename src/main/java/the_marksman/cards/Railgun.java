package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class Railgun extends CustomCard{
	public static final String ID = "Railgun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;	
	private static final int DMG = 5;
	private static final int MAGIC = 35;
	private static final int MAGIC_UP = 20;
	
	Random rand = new Random();
	

	public Railgun() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Railgun();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.name += "+";
			this.upgradeMagicNumber(MAGIC_UP);
			this.magicNumber = MAGIC + MAGIC_UP;
			this.upgraded = true;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			AbstractDungeon.actionManager.addToTop(new GainEnergyAction(-this.energyOnUse));
			int pr = 0;
			int sc = 1;
			int dmg = this.damage;
			if(p.getPower("PrecisionPower") != null) {
				pr = p.getPower("PrecisionPower").amount;
			}
			if(p.getPower("ConcentratedPower") != null) sc = 2;
			if(rand.random(100)  < (this.magicNumber*this.energyOnUse + pr) * sc) {
				dmg *= 3;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			}
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));			    	
        }		
	}
}
