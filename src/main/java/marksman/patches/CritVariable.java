package marksman.patches;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;
import marksman.cards.CritCard;

public class CritVariable extends DynamicVariable {


    @Override
    public boolean isModified(final AbstractCard card) {
        return ((CritCard)card).isCritModified;
    }
    
    @Override
    public int value(final AbstractCard card) {
        return ((CritCard)card).crit;
    }
    
    @Override
    public int baseValue(final AbstractCard card) {
        return ((CritCard)card).baseCrit;
    }
    
    @Override
    public boolean upgraded(final AbstractCard card) {
        return ((CritCard)card).upgradedCrit;
    }

	@Override
	public String key() {
		return "C";
	}
}
