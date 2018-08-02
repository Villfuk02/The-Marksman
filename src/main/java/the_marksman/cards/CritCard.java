package the_marksman.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.abstracts.CustomCard;

public abstract class CritCard extends CustomCard{
	
	public int baseCrit; 
	public boolean isCritModified; 
	public int crit; 
	public boolean upgradedCrit; 

	public CritCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
	}
	
	public void upgradeCrit(int amount) {
		 this.baseCrit += amount;
	     this.upgradedCrit = true;
	}	
	
	@Override
	public void applyPowers() {
		int pr = 0;
		int sc = 1;
		if(this.cardID != "Minigun" && AbstractDungeon.player.getPower("PrecisionPower") != null) {
			pr = AbstractDungeon.player.getPower("PrecisionPower").amount;
		}
		if(AbstractDungeon.player.getPower("ConcentratedPower") != null) sc = 2;
		this.crit = (this.baseCrit + pr) * sc;
		if(this.crit > 100)
			this.crit = 100;
		if(this.crit < 0)
			this.crit = 0;
		if (this.baseCrit != this.crit) {
            this.isCritModified = true;
        }
	}
	
	@Override
	public void displayUpgrades() {
		if (this.upgradedCrit) {
			this.crit = this.baseCrit;
            this.isCritModified = true;
        }
		if (this.upgradedCost) {
            this.isCostModified = true;
        }
        if (this.upgradedDamage) {
            this.damage = this.baseDamage;
            this.isDamageModified = true;
        }
        if (this.upgradedBlock) {
            this.block = this.baseBlock;
            this.isBlockModified = true;
        }
        if (this.upgradedMagicNumber) {
            this.magicNumber = this.baseMagicNumber;
            this.isMagicNumberModified = true;
        }
	}
	
	public CritCard makeStatEquivalentCritCopy() {
        final CritCard card = (CritCard)this.makeCopy();
        for (int i = 0; i < this.timesUpgraded; ++i) {
            card.upgrade();
        }
        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.crit = this.crit;
        card.baseCrit = this.baseCrit;
        card.upgradedCrit = this.upgradedCrit;
        card.isCritModified = this.isCritModified;
        return card;
    }

}
