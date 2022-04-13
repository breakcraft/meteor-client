/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.defaultworld;

import eventbus.events.GameStateChanged;
import eventbus.events.WorldChanged;
import lombok.extern.slf4j.Slf4j;
import meteor.Main;
import meteor.game.WorldService;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.rs.ClientThread;
import meteor.util.WorldUtil;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;

@PluginDescriptor(
	name = "Default World",
	description = "Enable a default world to be selected when launching the client",
	tags = {"home"}
)
@Slf4j
public class DefaultWorldPlugin extends Plugin
{
	private Client client = Main.INSTANCE.getClient();
	private ClientThread clientThread = ClientThread.INSTANCE;
	private DefaultWorldConfig config = (DefaultWorldConfig) javaConfiguration(DefaultWorldConfig.class);
	private WorldService worldService = WorldService.INSTANCE;

	@Override
	public void onGameStateChanged(GameStateChanged it)
	{
			if (it.getGamestate() == GameState.LOGIN_SCREEN) {
				applyWorld();
				unsubscribe();
			}
	}

	private void applyWorld()
	{
		if (client.getGameState() != GameState.LOGIN_SCREEN)
		{
			return;
		}

		if (System.getProperty("cli.world") != null)
		{
			return;
		}

		final int newWorld = config.useLastWorld() ? config.lastWorld() : config.getWorld();
		int correctedWorld = newWorld < 300 ? newWorld + 300 : newWorld;

		// Old School RuneScape worlds start on 301 so don't even bother trying to find lower id ones
		// and also do not try to set world if we are already on it
		if (correctedWorld <= 300 || client.getWorld() == correctedWorld)
		{
			return;
		}

		final WorldResult worldResult = worldService.getWorlds();
		if (worldResult == null)
		{
			log.warn("Failed to lookup worlds.");
			return;
		}

		final World world = worldResult.findWorld(correctedWorld);
		if (world == null)
		{
			log.warn("World {} not found.", correctedWorld);
			return;
		}

		final net.runelite.api.World rsWorld = client.createWorld();
		rsWorld.setActivity(world.getActivity());
		rsWorld.setAddress(world.getAddress());
		rsWorld.setId(world.getId());
		rsWorld.setPlayerCount(world.getPlayers());
		rsWorld.setLocation(world.getLocation());
		rsWorld.setTypes(WorldUtil.INSTANCE.toWorldTypes(world.getTypes()));

		client.changeWorld(rsWorld);
		log.debug("Applied new world {}", correctedWorld);
	}
}