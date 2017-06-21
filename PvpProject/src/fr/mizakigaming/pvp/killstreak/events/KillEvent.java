package fr.mizakigaming.pvp.killstreak.events;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.mizakigaming.pvp.killstreak.ressources.KillStreakList;
import fr.mizakigaming.pvp.main.PvpMain;
import net.md_5.bungee.api.ChatColor;

public class KillEvent implements Listener
{

	@EventHandler
	public void onKill(PlayerDeathEvent event)
	{
		if (PvpMain.getInstance().isKillStreakActived())
		{
			final Player killer = event.getEntity().getKiller(), target = event.getEntity().getPlayer();

			KillStreakList.set(killer.getName(), (byte) (KillStreakList.getList().get(killer.getName()) + 1));
			
			if(KillStreakList.getList().get(target.getName()) > PvpMain.getInstance().getKillStreakConfig().getInt("BestKillStreak." + target.getName()))
			{
				PvpMain.getInstance().getKillStreakConfig().set("BestKillStreak."+target.getName(), KillStreakList.getList().get(target.getName()));
				try
				{
					PvpMain.getInstance().getKillStreakConfig().save(PvpMain.getInstance().getKillStreakFile());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			KillStreakList.set(target.getName(), (byte) 0);
			
			NotificationBroadcast(killer.getName(), KillStreakList.getList().get(killer.getName()));
			
			
			
			
		}
	}

	private void NotificationBroadcast(String name, byte kill)
	{
		switch (kill)
		{
			case 3:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "3");
				break;
			case 5:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "5");
				break;
			case 7:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "7");
				break;
			case 10:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "10");
				break;
			case 15:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "15");
				break;
			case 20:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "20");
				break;
			case 30:
				Bukkit.broadcastMessage(
						ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + "30");
				break;
		}

		if (kill > 30)
			Bukkit.broadcastMessage(
					ChatColor.GOLD + name + ChatColor.RESET + " est en série de " + ChatColor.GOLD + kill);
	}

}
