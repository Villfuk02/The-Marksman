package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class ClusterGrenade extends CustomCard{
	public static final String ID = "ClusterGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;	
	private static final int DMG = 4;
	private static final int DMG_UP = 2;
	
	Random rand = new Random();
	

	public ClusterGrenade() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
		this.isMultiDamage = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ClusterGrenade();
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
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new GainEnergyAction(-this.energyOnUse));
		for (int i = 0; i < this.energyOnUse+1; i++) {
			AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
		}
		if(this.energyOnUse != 0) {
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
		}
		
		
	}
}
