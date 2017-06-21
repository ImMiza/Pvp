package fr.mizakigaming.pvp.main;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.mizakigaming.pvp.killstreak.ressources.KillStreakList;

public class JoinEvent implements Listener
{

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();

		/*
		 * Quand le joueur se connecte, si c'est sa 1er connexion il sera
		 * enregistré dans le dossier KillStreak du type
		 * 
		 * nom_joueur = 0
		 */
		if (!PvpMain.getInstance().getKillStreakConfig().contains("BestKillStreak." + player.getName()))
		{
			PvpMain.getInstance().getKillStreakConfig().set("BestKillStreak." + player.getName(), 0);
			try
			{
				PvpMain.getInstance().getKillStreakConfig().save(PvpMain.getInstance().getKillStreakFile());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		KillStreakList.set(player.getName(), (byte) 0);

		player.setScoreboard(Scoreboard.newScoreboard(player));
	}
}
