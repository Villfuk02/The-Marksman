package the_marksman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

import basemod.abstracts.CustomCard;
import the_marksman.AbstractCardEnum;

public class VoidGrenade extends CustomCard{
	public static final String ID = "VoidGrenade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UP_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 4;
	//private static final int UP_COST = 3;
	private static final int DMG = 3;
	private static final int DMG_UP = 2;
	
	Random rand = new Random();
	

	public VoidGrenade() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = DMG;
	}

	@Override
	public AbstractCard makeCopy() {
		return new VoidGrenade();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeDamage(DMG_UP);
			//this.upgradeBaseCost(UP_COST);
			this.rawDescription = UP_DESCRIPTION;
			this.initializeDescription();
			
		} 
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
		final CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.name.contains(" Grenade")) {
            	tmp.addToTop(c);                
            }
        }
		while (tmp.size() > 0) {
			AbstractDungeon.player.drawPile.removeCard(tmp.getTopCard().cardID);
			AbstractDungeon.player.drawPile.addToTop(tmp.getTopCard());
			tmp.removeTopCard();
			AbstractDungeon.actionManager.addToTop(new PlayTopCardAction(m, false));
        }
		if(upgraded) {
			for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
	            if (c.name.contains("Grenade")) {	            	
	            	tmp.addToTop(c);
	            }
	        }
			while (tmp.size() > 0) {
				AbstractDungeon.player.discardPile.removeCard(tmp.getTopCard().cardID);
				AbstractDungeon.player.drawPile.addToTop(tmp.getTopCard());
				tmp.removeTopCard();
				AbstractDungeon.actionManager.addToTop(new PlayTopCardAction(m, false));
	        }
	    }
				
    }			
}

