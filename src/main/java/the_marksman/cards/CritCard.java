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

}
