package the_marksman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import the_marksman.TheMarksmanMod;
import the_marksman.powers.BurningPower;

@SpirePatch(cls="com.megacrit.cardcrawl.cards.status.Burn", method="use")
public class BurnPatch {
	@SuppressWarnings("rawtypes")
	@SpireInsertPatch(rloc=3)
	public static SpireReturn Insert(Burn __instance, AbstractPlayer p, AbstractMonster m) {
		TheMarksmanMod.logger.info("BURN");
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BurningPower(AbstractDungeon.player, __instance.upgraded?4:2), __instance.upgraded?4:2));
		return SpireReturn.Return(null);
	}
}
