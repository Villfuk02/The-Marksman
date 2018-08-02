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

import the_marksman.AbstractCardEnum;
import the_marksman.powers.CritsThisTurn;
import the_marksman.powers.PrecisionPower;

public class BurstFire extends CritCard{
	public static final String ID = "BurstFire";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 4;
	//private static final int DMG_UP = 3;
	private static final int AMT = 3;
	private static final int AMT_UP = 1;
	
	Random rand = new Random();
	

	public BurstFire() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = AMT;
		this.baseCrit = 15;
	}

	@Override
	public AbstractCard makeCopy() {
		return new BurstFire();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(AMT_UP);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {			
			for	(int i = 0; i < this.magicNumber; i++) {
				int dmg = this.damage;
				if(rand.random(100)  < this.crit) {
					dmg *= 3;
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
				}		
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PrecisionPower(p, -30), -30));			
        }		
	}
}
