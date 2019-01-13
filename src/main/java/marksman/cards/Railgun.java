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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import marksman.MarksmanMod;
import marksman.patches.AbstractCardEnum;
import marksman.powers.CritsThisTurn;

public class Railgun extends CritCard{
	public static final String ID = "Railgun";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -1;	
	private static final int DMG = 3;
	private static final int MAGIC = 15;
	private static final int MAGIC_UP = 10;
	

	public Railgun() {
		super(ID, NAME, MarksmanMod.CARD_IMG_PATH + ID + ".png", COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.BLACK,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = DMG;
		this.baseCrit = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Railgun();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeCrit(MAGIC_UP);			
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			if (this.energyOnUse < EnergyPanel.totalCount) {
	            this.energyOnUse = EnergyPanel.totalCount;
	        }
			int effect = this.energyOnUse;
			if (!this.freeToPlayOnce) {
	            p.energy.use(effect);
	        }
			if (p.hasRelic("Chemical X")) {
		         effect += 2;
		         p.getRelic("Chemical X").flash();
		    }
			for (int i = 0; i < effect; i++) {
				int dmg = this.damage;
				if(AbstractDungeon.cardRandomRng.random(99)  < this.crit*this.energyOnUse) {
					dmg *= 3;
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CritsThisTurn(p, 1), 1));
				}
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, dmg, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));			    	
	        }		
		}
	}
}
