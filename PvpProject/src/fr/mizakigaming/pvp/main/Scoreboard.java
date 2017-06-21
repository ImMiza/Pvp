package fr.mizakigaming.pvp.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import fr.mizakigaming.pvp.killstreak.ressources.KillStreakList;
import net.md_5.bungee.api.ChatColor;

public class Scoreboard
{

	private static org.bukkit.scoreboard.Scoreboard	scoreBoard;
	private static Objective						objective;
	private static int								money;
	private static int								bestKillStreak;
	private static byte								actualKillStreak;

	public static org.bukkit.scoreboard.Scoreboard newScoreboard(Player player)
	{
		scoreBoard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreBoard.registerNewObjective("stats", "dummy");
		bestKillStreak = PvpMain.getInstance().getKillStreakConfig().getInt("BestKillStreak." + player.getName());
		actualKillStreak = KillStreakList.getList().get(player.getName());

		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("stats");

		Score scoreMoney = objective.getScore(ChatColor.GREEN + "Gems:");
		scoreMoney.setScore(money);

		Score scoreBestKillStreak = objective.getScore(ChatColor.GOLD + "Meilleur KillStreak:");
		scoreBestKillStreak.setScore(bestKillStreak);

		Score scoreActualKillStreak = objective.getScore(ChatColor.GOLD + "KillStreak Actuel:");
		scoreActualKillStreak.setScore(actualKillStreak);

		return scoreBoard;
	}

	public static void Refresh(Player player)
	{
		player.setScoreboard(newScoreboard(player));
	}

	public static void EraseScoreBoard(Player player)
	{
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
}
