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
import the_marksman.actions.ChangeGoldAction;
import the_marksman.powers.CritsThisTurn;

public class GoldenBullet extends CustomCard{
	public static final String ID = "GoldBullet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 12;
	private static final int DMG_UP = 4;
	private static final int CRIT = 10;
	
	Random rand = new Random();
	

	public GoldenBullet() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = CRIT;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new GoldenBullet();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.name += "+";
			this.upgradeDamage(DMG_UP);
			this.upgraded = true;
		} 
	}
	
	@Override
    public boolean hasEnoughEnergy() {
        if (AbstractDungeon.player.gold < 8)
        	return false;
        return super.hasEnoughEnergy();
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {	
			
		AbstractDungeon.actionManager.addToBottom(new ChangeGoldAction(-8));
		
		int pr = 0;
		int sc = 1;
		int dmg = this.damage;
		if(p.getPower("PrecisionPower") != null) {
			pr = p.getPower("PrecisionPower").amount;
		}
		if(p.getPower("ConcentratedPower") != null) sc = 2;
		if(rand.random(100)  < (this.magicNumber + pr) * sc) {
			dmg *= 3;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ChangeGoldAction(40));
		}			
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
	    				
	}
}
