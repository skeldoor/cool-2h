package com.skeldoor.cool2h;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.ClientTick;
import net.runelite.api.kit.KitType;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.stream.IntStream;

@Slf4j
@PluginDescriptor(
		name = "Cool 2h",
		description = "Fix your posture and look cooler while using two-handed weapons"
)
public class Cool2hPlugin extends Plugin
{
	@Inject
	private Client client;

	// Bronze Iron Steel Black Mithril Adamant Rune White Dragon Gilded
	int[] rune2hItemId = {1307, 1309, 1311, 1313, 1315, 1317, 1319, 6609, 7158, 20155};

	int superCool2hAnimationIdIdle = 7053;
	int superCool2hAnimationIdIdleRotateLeft = 7044;
	int superCool2hAnimationIdIdleRotateRight = 7044;
	int superCool2hAnimationIdWalking = 7052;
	int superCool2hAnimationIdWalkingRotate180 = 7052;
	int superCool2hAnimationIdWalkingRotateLeft = 7048;
	int superCool2hAnimationIdWalkingRotateRight = 7047;
	int superCool2hAnimationIdRunning = 7043;

	// Run before the current frame is rendered to stop glitchy animation blending
	@Subscribe(priority = -1000.0f)
	public void onBeforeRender(BeforeRender e) {
		if (client.getGameState() != GameState.LOGGED_IN) return;
		Player local = client.getLocalPlayer();
		if (local == null) return;
		PlayerComposition localComp = local.getPlayerComposition();
		if (localComp == null) return;
		int weaponId = localComp.getEquipmentId(KitType.WEAPON);
		if (IntStream.of(rune2hItemId).anyMatch(x -> x == weaponId)) {
			swapPlayerAnimation(local);
		}
	}

	private void swapPlayerAnimation(Player local)
	{
		local.setIdlePoseAnimation(superCool2hAnimationIdIdle);
		local.setIdleRotateLeft(superCool2hAnimationIdIdleRotateLeft);
		local.setIdleRotateRight(superCool2hAnimationIdIdleRotateRight);
		local.setWalkAnimation(superCool2hAnimationIdWalking);
		local.setWalkRotate180(superCool2hAnimationIdWalkingRotate180);
		local.setWalkRotateLeft(superCool2hAnimationIdWalkingRotateLeft);
		local.setWalkRotateRight(superCool2hAnimationIdWalkingRotateRight);
		local.setRunAnimation(superCool2hAnimationIdRunning);
	}
}
