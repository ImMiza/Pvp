package fr.mizakigaming.pvp.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import fr.mizakigaming.pvp.killstreak.command.KillStreakCommand;
import fr.mizakigaming.pvp.killstreak.events.KillEvent;
import fr.mizakigaming.pvp.killstreak.ressources.KillStreakList;

public class PvpMain extends JavaPlugin
{

	private static PvpMain				instance;
	private boolean						statutKillStreak;
	private File						killStreakFile;
	private FileConfiguration			killStreakConfig;
	KillStreakCommand					ksCommand;
	JoinEvent							ksJoinEvent;
	KillEvent							killEvent;
	DeathEvent							deathEvent;
	fr.mizakigaming.pvp.main.JoinEvent	mJoinEvent;
	@Override
	public void onEnable()
	{
		instance = this;
		this.statutKillStreak = true;
		this.killStreakFile = new File(getDataFolder(), "KillStreak.yml");
		this.killStreakConfig = YamlConfiguration.loadConfiguration(this.killStreakFile);
		ksCommand = new KillStreakCommand();
		ksJoinEvent = new JoinEvent();
		killEvent = new KillEvent();
		deathEvent = new DeathEvent();
		mJoinEvent = new fr.mizakigaming.pvp.main.JoinEvent();
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			KillStreakList.addPlayer(player.getName());
			Scoreboard.newScoreboard(player);
		}

		if (!this.killStreakFile.exists())
		{
			try
			{
				this.killStreakFile.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}


		// commands
		this.getCommand("killstreak").setExecutor(ksCommand);

		// events
		this.getServer().getPluginManager().registerEvents(ksJoinEvent, this);
		this.getServer().getPluginManager().registerEvents(killEvent, this);
		this.getServer().getPluginManager().registerEvents(deathEvent, this);
		this.getServer().getPluginManager().registerEvents(mJoinEvent, this);
	}

	@Override
	public void onDisable()
	{

	}

	public static PvpMain getInstance()
	{
		return instance;
	}

	public boolean isKillStreakActived()
	{
		return this.statutKillStreak;
	}

	public void setKillStreak(boolean statutKillStreak)
	{
		this.statutKillStreak = statutKillStreak;
	}

	public FileConfiguration getKillStreakConfig()
	{
		return killStreakConfig;
	}

	public File getKillStreakFile()
	{
		return killStreakFile;
	}


}
