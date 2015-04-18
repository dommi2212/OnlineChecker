package me.dommi2212.OnlineChecker;

import java.util.UUID;

import me.dommi2212.BungeeBridge.packets.PacketGetPlayerUUID;
import me.dommi2212.BungeeBridge.packets.PacketGetServerByPlayer;
import me.dommi2212.BungeeBridge.packets.PacketIsPlayerOnline;
import me.dommi2212.BungeeBridge.util.IsOnlineResult;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandIsOnline implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {		
		if(cs.hasPermission("isonline.use")) {
			if(args.length != 1) {
				cs.sendMessage("§cSyntax: /isonline <Player>");
				return true;
			}
			PacketIsPlayerOnline isonlinepacket = new PacketIsPlayerOnline(args[0]);
			IsOnlineResult isonline = (IsOnlineResult) isonlinepacket.send();
			if(isonline == IsOnlineResult.ONLINE) {
				PacketGetPlayerUUID uuidpacket = new PacketGetPlayerUUID(args[0]);
				UUID uuid = (UUID) uuidpacket.send();
				PacketGetServerByPlayer getserverpacket = new PacketGetServerByPlayer(uuid);
				String server = (String) getserverpacket.send();
				cs.sendMessage("§6" + args[0] + " §7is §aonline!\n§7Server: §6" + server);
			} else if(isonline == IsOnlineResult.OFFLINE) {
				cs.sendMessage("§6" + args[0] + " §7is §coffline!");
			} else {
				cs.sendMessage("§cAn error occured :/");
			}
		}
		
		return false;
	}

}