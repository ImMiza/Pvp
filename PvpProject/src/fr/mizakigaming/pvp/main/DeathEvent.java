package fr.mizakigaming.pvp.main;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class DeathEvent implements Listener
{

	short	killerMoney;
	short	targetMoney;

	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		final Player killer = event.getEntity().getKiller(), target = event.getEntity().getPlayer();


		event.setDeathMessage(ChatColor.RED + killer.getName() + ChatColor.RESET + " a tué(e) " + ChatColor.GREEN + target.getName());

		Scoreboard.Refresh(killer);
		Scoreboard.Refresh(target);
	}
}
