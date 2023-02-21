package com.skeldoor.cool2h;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class Cool2hPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(Cool2hPlugin.class);
		RuneLite.main(args);
	}
}