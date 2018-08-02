package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.random.Random;

import the_marksman.AbstractCardEnum;
import the_marksman.powers.CritsThisTurn;

public class Crossbow extends CritCard{
	public static final String ID = "Crossbow";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;	
	private static final int DMG = 3;
	private static final int DMG_UP = 1;
	private static final int MAGIC = 1;
	private static final int MAGIC_UP = 1;
	
	Random rand = new Random();
	

	public Crossbow() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.damageType = DamageType.THORNS;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.baseCrit = 15;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Crossbow();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(MAGIC_UP);
			
			
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			int dmg = this.damage;
			if(rand.random(100)  < this.crit) {
				dmg *= 3;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			}	
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, DamageType.THORNS),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new FrailPower(m,this.magicNumber,false), this.magicNumber));
			
			dmg = this.damage;
			if(rand.random(100)  < this.crit) {
				dmg *= 3;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			}	
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, DamageType.THORNS),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new FrailPower(m,this.magicNumber,false), this.magicNumber));
        }		
	}
}
