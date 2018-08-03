package the_marksman.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import the_marksman.AbstractCardEnum;
import the_marksman.actions.ShotgunAction;

public class Minigun extends CritCard{
	public static final String ID = "Minigun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;	
	private static final int DMG = 1;
	private static final int CRIT = 20;
	private static final int AMT = 11;
	private static final int AMT_UP = 4;
	
	Random rand = new Random();
	

	public Minigun() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseMagicNumber = this.magicNumber = AMT;
		this.baseCrit = CRIT;
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
		this.upgraded = true;
		this.initializeTitle();
	}
	
	@Override
    public boolean canUpgrade() {
        return true;
    }

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {		
		
		AbstractDungeon.actionManager.addToBottom(new ShotgunAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, DMG, DamageType.THORNS), this.magicNumber, this.crit, 3));				
	}
}
