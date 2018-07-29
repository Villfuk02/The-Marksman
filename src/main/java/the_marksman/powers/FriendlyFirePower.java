package the_marksman.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;

public class FriendlyFirePower extends AbstractPower
{
	public static final String POWER_ID = "FriendlyFirePower";
	
    public static final String[] DESCRIPTIONS = new String[] {
    		"For every #yBurning damage you deal, gain that much #yBlock.",
    		"For every #yBurning damage you deal, gain #b",
    		" times as much #yBlock."
	};
    
    public FriendlyFirePower(final AbstractCreature owner, final int newAmount) {
        this.name = "Friendly Fire";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = new Texture("img/powers/"+name+".png");
    }  
    
    @Override
    public void updateDescription() {
    	if(amount == 1)
    		this.description = DESCRIPTIONS[0];
    	else
    		this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }    
   
}
