package fr.mizakigaming.pvp.killstreak.ressources;

import java.util.HashMap;
import java.util.Map;

public class KillStreakList
{

	private static Map<String, Byte> list = new HashMap<String, Byte>();

	public static void addPlayer(String name)
	{
		list.put(name, (byte) 0);
	}

	public static Map<String, Byte> getList()
	{
		return list;
	}

	public static boolean containsPlayer(String name)
	{
		if (list.containsKey(name))
			return true;
		else
			return false;
	}

	public static void set(String name, byte kill)
	{
		list.put(name, kill);
	}
}
