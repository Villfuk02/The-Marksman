package marksman.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.random.Random;

import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.actions.ShotgunAction;

public class PumpShotgun extends CritCard{
	public static final String ID = "PumpShotgun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;	
	private static final int DMG = 3;
	//private static final int DMG_UP = 1;
	private static final int CRIT = 25;
	private static final int AMT = 4;
	private static final int AMT_UP = 1;
	
	Random rand = new Random();
	

	public PumpShotgun() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.baseMagicNumber = this.magicNumber = AMT;
		this.baseCrit = CRIT;
	}

	@Override
	public AbstractCard makeCopy() {
		return new PumpShotgun();
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
		
		if(p.getPower("Vulnerable") == null) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
		}
		
		
		AbstractDungeon.actionManager.addToBottom(new ShotgunAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.damage, damageTypeForTurn), this.magicNumber, this.crit, 1));
	    				
	}
}
