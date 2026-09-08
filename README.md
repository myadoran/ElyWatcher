# Elysian Home Assistant Connector

No AI was used in making this plugin.

This plugin fulfills a very specific use case I encountered recently. I got an Elysian Spirit Shield lamp that I really wanted to connect to the in-game item.

As such, it's designed with two triggers that occur when you equip or unequip your Ely. The idea is that two Home Assistant webhook automations are created, with one turning on the lamp and one turning it off. These would create two webhook URLs that are triggered on the relevant game events to toggle your lamp (or any other automation, really).

However, any API that works with GET/POST along with bearer token auth should work just fine.

These variables are used:

|Name|type|Function|Comments/warnings|
|---|---|---|---|
|Chat notifications|Toggle|Show or hide chat messages to signal the plugin working||
|Method Equip|Dropdown|Choose between a GET or POST request|Depending on what you connect this plugin to, POST might be necessary|
|API Hostname Equip|String|URL to send the request to on equipping your shield (e.g https://example.com)|Make sure to include the protocol (http/https)|
|Method UnEquip|Dropdown|Choose between a GET or POST request|Depending on what you connect this plugin to, POST might be necessary|
|API Hostname UnEquip|String|URL to send the request to on UNequipping your shield (e.g https://example.com)|Make sure to include the protocol (http/https)|
|Bearer token|String|API key to be sent for bearer token authentication|Consider creating a limited key for this plugin if you can, in case the variable gets stored in your Runelite account.|

If you are the other weirdo on this planet that needs this plugin, feel free to let me know through an issue if you run into a problem.