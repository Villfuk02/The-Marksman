package marksman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;

import marksman.MarksmanMod;
import marksman.character.TheMarksman;

public class TalkBubblePatches {
	@SpirePatch(clz= SpireShield.class,method="takeTurn")
	public static class ShieldBubble {

		public static void Prefix(SpireShield sb) {
	        if (AbstractDungeon.player instanceof TheMarksman && MarksmanMod.talk < 1) {
	            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "#y@Dad,@ ~is~ ~that~ ~you?~ NL ~You~ ~should~ ~not~ ~be~ #r~here.~ ", 3.0F, 4.0F));
	            MarksmanMod.talk = 1;
	        }
	    }		
	}
	
	@SpirePatch(clz= SpireSpear.class,method="takeTurn")
	public static class SpearBubble {

		public static void Prefix(SpireSpear sb) {
	        if (AbstractDungeon.player instanceof TheMarksman && MarksmanMod.talk < 10) {
	            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "#y@father,@ ~why~ ~did~ ~you~ ~come~ ~here?~ NL ~We~ ~now~ ~HAVE~ ~to~ #r@KILL@ #r@YOU.@ ", 4.0F, 5.0F));
	            MarksmanMod.talk = 10;
	        }
	    }
	}
	
	@SpirePatch(clz= CorruptHeart.class,method="takeTurn")
	public static class HeartBubble {

		public static void Prefix(CorruptHeart sb) {
			if (AbstractDungeon.player instanceof TheMarksman && MarksmanMod.talk < 100) {
	            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "#r@I@ #r@DON'T@ #r@WANT@ #r@TO@ #r@KILL@ #r@YOU.@ ", 3.0F, 4.0F));
	            MarksmanMod.talk = 100;
	        }else if (AbstractDungeon.player instanceof TheMarksman && MarksmanMod.talk == 100) {
	            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "#r@IT@ #r@BREAKS@ #r@MY@ #r@HEART.@ ", 3.0F, 4.0F));
	            MarksmanMod.talk = 1000;
	        }
	    }
	}
}
