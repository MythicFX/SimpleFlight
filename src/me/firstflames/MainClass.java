package me.firstflames;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;


@SuppressWarnings("deprecation")
public class MainClass extends JavaPlugin implements Listener{

	private static final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		update();
		log.info("SimpleFly is now enabled!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		getLogger().info("SimpleFly is now disabled!");
		reloadConfig();
		saveConfig();
		}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.contains("reloadConfig")){
			if(player.hasPermission("simplefly.reload") == true){
				player.sendMessage(ChatColor.AQUA + "Successfully reloaded config!");
				reloadConfig();
				event.setCancelled(true);
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to reload the config!");
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(getConfig().getBoolean("particle-fly") == true){
			if(player.isFlying() == true){
				player.playEffect(player.getLocation(), Effect.SMOKE, 2);
				player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
	   String prefix = getConfig().getString("prefix").replaceAll("&", "§");
		if(cmd.getName().equalsIgnoreCase("fly")){
			 if (!sender.hasPermission("simplefly.fly")) {
				 player.sendMessage("§4You do not have any permission to use this command! Please contact a Admin to give you permission to use this command!");
				   player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                 return true;
         }
		if(args.length <= 0){
			if(!(player.getAllowFlight())){
				player.setAllowFlight(true);
				   player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.sendMessage(prefix + getConfig().getString("fly-on-msg").replaceAll("&", "§"));
			} else if(player.getAllowFlight() == true){
				player.setAllowFlight(false);
				   player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
				player.sendMessage(prefix + getConfig().getString("fly-off-msg").replaceAll("&", "§"));
		
			}
			return true;
			} else if(args.length == 0){
				Player target = Bukkit.getPlayer(args[0]);
				if(target.isOnline() == true){
				if(!(target.getAllowFlight())){
					target.setAllowFlight(true);
					   target.playSound(target.getLocation(), Sound.ANVIL_BREAK, 1, 1);
					target.sendMessage(prefix + getConfig().getString("fly-on-msg").replaceAll("&", "§"));
				} else if(target.getAllowFlight() == true){
					target.setAllowFlight(false);
					   target.playSound(target.getLocation(), Sound.ANVIL_BREAK, 1, 1);
					target.sendMessage(prefix + getConfig().getString("fly-off-msg").replaceAll("&", "§"));
			
				}
			} else if(target.isOnline() == false){
				player.sendMessage(prefix + "§4The player is not online!");
			}
			}
		} else if(cmd.getName().equalsIgnoreCase("speed")){
			if(!player.hasPermission("simplefly.speed")){
				 player.sendMessage("§4You do not have any permission to use this command! Please contact a Admin to give you permission to use this command!");
				   player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
               return true;
       }
			if(args.length == 0){
				player.sendMessage(prefix + "§6Sucessfully changed your speed!");
				if(player.isFlying() == true){
					if(args[0].equalsIgnoreCase("1")){
					player.setFlySpeed(0.5F);
				}
				else if(args[0].equalsIgnoreCase("2")){
					player.setFlySpeed(1F);
					} else if(args[0].equalsIgnoreCase("3")){
					player.setFlySpeed(1.5F);
				} else if(args[0].equalsIgnoreCase("4")){
					player.setFlySpeed(2F);
					} else if(args[0].equalsIgnoreCase("5")){
					player.setFlySpeed(2.5F);
				} else if(args[0].equalsIgnoreCase("6")){
					player.setFlySpeed(3F);
					} else if(args[0].equalsIgnoreCase("7")){
					player.setFlySpeed(3.5F);
				} else if(args[0].equalsIgnoreCase("8")){
					player.setFlySpeed(4F);
					} else if(args[0].equalsIgnoreCase("9")){
					player.setFlySpeed(4.5F);
				} else if(args[0].equalsIgnoreCase("10")){
					player.setFlySpeed(5F);
				}
				} else if(player.isOnGround() == true){
						if(args[0].equalsIgnoreCase("1")){
						player.setFlySpeed(0.5F);
					}
					else if(args[0].equalsIgnoreCase("2")){
						player.setWalkSpeed(1F);
						} else if(args[0].equalsIgnoreCase("3")){
						player.setWalkSpeed(1.5F);
					} else if(args[0].equalsIgnoreCase("4")){
						player.setWalkSpeed(2F);
						} else if(args[0].equalsIgnoreCase("5")){
						player.setWalkSpeed(2.5F);
					} else if(args[0].equalsIgnoreCase("6")){
						player.setWalkSpeed(3F);
						} else if(args[0].equalsIgnoreCase("7")){
						player.setWalkSpeed(3.5F);
					} else if(args[0].equalsIgnoreCase("8")){
						player.setWalkSpeed(4F);
						} else if(args[0].equalsIgnoreCase("9")){
						player.setWalkSpeed(4.5F);
					} else if(args[0].equalsIgnoreCase("10")){
						player.setWalkSpeed(5F);
					}
					}
				
				}
			} 
		

			
			
		
		
		return false;
		
	}

	public String update(){
		try {
			int resource = 20137;
	        HttpURLConnection con = (HttpURLConnection) new URL(
	                "http://www.spigotmc.org/api/general.php").openConnection();
	        con.setDoOutput(true);
	        con.setRequestMethod("POST");
	        con.getOutputStream()
	                .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + resource)
	                        .getBytes("UTF-8"));
	        String version = new BufferedReader(new InputStreamReader(
	                con.getInputStream())).readLine();
	        if (version.length() <= 7) {
	            return version;
	        }
	    } catch (Exception ex) {
	        log.info("Failed to check for a update on spigot.");
	    }
		return null;
	}
	
	
}
