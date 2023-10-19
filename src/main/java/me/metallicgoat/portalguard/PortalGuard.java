package me.metallicgoat.portalguard;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class PortalGuard extends JavaPlugin implements Listener {

	private final List<EntityType> blacklistedEntities = Arrays.asList(
			EntityType.DROPPED_ITEM,
			EntityType.BOAT,
			EntityType.MINECART,
			EntityType.MINECART_CHEST,
			EntityType.MINECART_COMMAND,
			EntityType.MINECART_FURNACE,
			EntityType.MINECART_TNT,
			EntityType.MINECART_HOPPER,
			EntityType.MINECART_MOB_SPAWNER
	);

	public void onEnable(){
		final PluginDescriptionFile pdf = this.getDescription();

		log(
				"------------------------------",
				pdf.getName() + " For MBedwars",
				"By: " + pdf.getAuthors(),
				"Version: " + pdf.getVersion(),
				"------------------------------"
		);

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPortalUse(EntityPortalEvent event) {
		if (blacklistedEntities.contains(event.getEntityType())) {
			event.setCancelled(true);
		}
	}

	private void log(String... strings) {
		for (String s : strings)
			getLogger().info(s);
	}
}
