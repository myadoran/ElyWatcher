package com.myadoran.elywatch;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.api.Client;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
		name = "ElyWatch",
		description = "Handles Ely watching duties",
		tags = {"shield", "ely"},
		enabledByDefault = true
)

public class ElyWatchPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Provides
	ElyWatchConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ElyWatchConfig.class);
	}

	@Inject
	private ElyWatchConfig config;

	static final String CONFIG_GROUP = "elywatch";

	private boolean isWearingEly;

	@Override
	protected void startUp() throws Exception
	{
		clientThread.invokeLater(() ->
		{
			final ItemContainer container = client.getItemContainer(InventoryID.WORN);

			if (container != null)
			{
				isWearingEly = false;
				checkInventory(container);
			}
		});
	}


	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != client.getItemContainer(InventoryID.WORN))
		{
			return;
		}

		checkInventory(event.getItemContainer());
	}


	private void checkInventory(ItemContainer equipment)
	{
		try
		{
			final Item shield = equipment.getItem(EquipmentInventorySlot.SHIELD.getSlotIdx());
			if (shield.getId() == 12817)
			{
				if (!isWearingEly)
				{
					isWearingEly = true;
					if (config.chatNotify())
					{
						sendChatMessage(Color.blue, "Ely equipped");
						makeApiCall();
					}
				}
			}
			else
			{
				if (isWearingEly)
				{
					isWearingEly = false;
					if (config.chatNotify())
					{
						sendChatMessage(Color.red, "Ely unequipped");
						makeApiCall();
					}
				}
			}
		}
		catch (Exception e)
		{
			if (isWearingEly)
			{
				isWearingEly = false;
				if (config.chatNotify())
				{
					sendChatMessage(Color.red, "Ely unequipped");
					makeApiCall();
				}
			}
		}


	}
	private void makeApiCall()
	{
		String url = "";
		String method = "";
		try
		{
			if (isWearingEly)
			{
				url = config.apiHostEquip();
				method = String.valueOf(config.apiMethodEquip());
			}
			if (!isWearingEly)
			{
				url = config.apiHostUnEquip();
				method = String.valueOf(config.apiMethodUnEquip());
			}
			var client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(
							URI.create(url))
					.header("accept", "application/json")
					.method(method, HttpRequest.BodyPublishers.ofString(""))
					.build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
		}
		catch (Exception e)
		{
			sendChatMessage(Color.red, "Something has gone wrong in ElyWatch.");
		}
	}

	private void sendChatMessage(Color color, String chatMessage)
	{
		final String message = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append(color, chatMessage)
				.build();

		chatMessageManager.queue(
				QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(message)
						.build());
	}


}
