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
import com.megacrit.cardcrawl.random.Random;

import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.actions.ChangeGoldAction;
import marksman.powers.CritsThisTurn;

public class GoldenBullet extends CritCard{
	public static final String ID = "GoldBullet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 20;
	private static final int DMG_UP = 4;
	private static final int CRIT = 10;
	
	Random rand = new Random();
	

	public GoldenBullet() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY);
		this.baseDamage = DMG;
		this.baseCrit = CRIT;
		this.exhaust = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new GoldenBullet();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			
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
			
		AbstractDungeon.actionManager.addToBottom(new ChangeGoldAction(-8, m));
		
		int dmg = this.damage;
		if(rand.random(100)  < this.crit) {
			dmg *= 3;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ChangeGoldAction(40, m));
		}			
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
	    				
	}
}
