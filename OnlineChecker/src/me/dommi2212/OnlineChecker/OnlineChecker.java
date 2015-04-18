package me.dommi2212.OnlineChecker;

import org.bukkit.plugin.java.JavaPlugin;

public class OnlineChecker extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("isonline").setExecutor(new CommandIsOnline());
	}

}
