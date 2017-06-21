package fr.mizakigaming.pvp.killstreak.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.mizakigaming.pvp.main.PvpMain;
import net.md_5.bungee.api.ChatColor;

public class KillStreakCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;

			if (PvpMain.getInstance().isKillStreakActived() == true)
			{
				PvpMain.getInstance().setKillStreak(false);
				player.sendMessage("killstreak " + ChatColor.RED + "désactivé");
			}
			else
			{
				PvpMain.getInstance().setKillStreak(true);
				player.sendMessage("killstreak " + ChatColor.GREEN + "activé");
			}

			return true;
		}
		return false;
	}

}
