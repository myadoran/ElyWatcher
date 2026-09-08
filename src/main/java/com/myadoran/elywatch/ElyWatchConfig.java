/*
 * Copyright (c) 2020, dekvall <https://github.com/dekvall>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.myadoran.elywatch;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(com.myadoran.elywatch.ElyWatchPlugin.CONFIG_GROUP)
public interface ElyWatchConfig extends Config
{
	@ConfigItem(
			position = 1,
			keyName = "chatNotify",
			name = "Chat notifications",
			description = "Show game messages for equipping and un-equipping."
	)
	default boolean chatNotify()
	{
		return true;
	}

	enum apiMethodEquip
	{
		GET,
		POST
	}

	@ConfigItem(
			position = 2,
			keyName = "apiMethodEquip",
			name = "Method Equip",
			description = "HTTP method to be used for API call on equip."
	)
	default apiMethodEquip apiMethodEquip()
	{
		return apiMethodEquip.GET;
	}

	@ConfigItem(
			position = 3,
			keyName = "apiHostEquip",
			name = "API Hostname Equip",
			description = "Hostname that gets called on equip."
	)
	default String apiHostEquip()
	{
		return "";
	}

	enum apiMethodUnEquip
	{
		GET,
		POST
	}

	@ConfigItem(
			position = 4,
			keyName = "apiMethodUnEquip",
			name = "Method UnEquip",
			description = "HTTP method to be used for API call on unequip."
	)
	default apiMethodUnEquip apiMethodUnEquip()
	{
		return apiMethodUnEquip.GET;
	}

	@ConfigItem(
			position = 5,
			keyName = "apiHostUnEquip",
			name = "API Hostname UnEquip",
			description = "Hostname that gets called on unequip."
	)
	default String apiHostUnEquip()
	{
		return "";
	}

	@ConfigItem(
			position = 6,
			keyName = "bearerToken",
			name = "Bearer token",
			description = "API key to be used for the call."
	)
	default String bearerToken()
	{
		return "";
	}

}
