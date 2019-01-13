package marksman.cards;

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
import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.CritsThisTurn;

public class RustyPistol extends CritCard{
	public static final String ID = "RustyPistol";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;	
	private static final int DMG = 4;
	//private static final int DMG_UP = 1;
	private static final int CRIT = 30;
	private static final int CRIT_UP = 25;
	

	public RustyPistol() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseCrit = this.crit = CRIT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new RustyPistol();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//this.upgradeDamage(DMG_UP);
			this.upgradeCrit(CRIT_UP);
			this.magicNumber = CRIT + CRIT_UP;
			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {			
			int dmg = this.damage;
			if(AbstractDungeon.cardRandomRng.random(99)  < this.crit) {
				dmg *= 3;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
				this.exhaust = true;
			}	
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}		
	}
}
