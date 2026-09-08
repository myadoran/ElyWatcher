package com.myadoran.elywatch;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ElyWatchPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ElyWatchPlugin.class);
		RuneLite.main(args);
	}
}