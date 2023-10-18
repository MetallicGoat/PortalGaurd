package me.metallicgoat.portalguard;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalGuard extends JavaPlugin implements Listener {

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
		if (event.getEntityType() == EntityType.DROPPED_ITEM) {
			event.setCancelled(true);
		}
	}

	private void log(String... strings) {
		for (String s : strings)
			getLogger().info(s);
	}
}
