package marksman.cards;

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
import com.megacrit.cardcrawl.random.Random;

import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.CritsThisTurn;
import marksman.powers.PrecisionPower;

public class HandCannon extends CritCard{
	public static final String ID = "HandCannon";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 8;
	private static final int DMG_UP = 2;
	private static final int LOSS = 6;
	private static final int LOSS_UP = 2;
	
	Random rand = new Random();
	

	public HandCannon() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = LOSS;
		this.baseCrit = 10;
	}

	@Override
	public AbstractCard makeCopy() {
		return new HandCannon();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			this.upgradeMagicNumber(LOSS_UP);
			this.magicNumber = LOSS + LOSS_UP;
			
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
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, 1, DamageType.HP_LOSS),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PrecisionPower(p, -25), -25));
			    	
        }		
	}
}
