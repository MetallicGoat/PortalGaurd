package me.metallicgoat.portalguard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PortalGuard extends JavaPlugin implements Listener {
	private final List<EntityType> blacklistedEntities = new ArrayList<>();

	public void onEnable() {
		final PluginDescriptionFile pdf = this.getDescription();

		log(
				"------------------------------",
				pdf.getName() + " For MBedwars",
				"By: " + pdf.getAuthors(),
				"Version: " + pdf.getVersion(),
				"------------------------------"
		);

		getServer().getPluginManager().registerEvents(this, this);
		loadConfig();
	}

	private void loadConfig() {
		final File configFile = new File(getDataFolder(), "config.yml");

		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			saveResource("config.yml", false);
		}

		final FileConfiguration config = new YamlConfiguration();

		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final List<String> entityTypes = config.getStringList("Blacklisted-Portal-Entities");

		if (entityTypes == null) {
			getLogger().warning("Your PortalGuard config seems to be missing the 'Blacklisted-Portal-Entities' config!");
			return;
		}

		for(String type : entityTypes){
			try {
				final EntityType entityType = EntityType.valueOf(type);
				blacklistedEntities.add(entityType);
			} catch (Exception e){
				getLogger().warning("EntityType '" + type + "' does not exist!");
			}
		}
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
