package com.bowhealth;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class BowHealth extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info("Enabled!");
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onDamageByBow(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player) {
                    Player player = (Player) ((EntityDamageByEntityEvent) event).getDamager();
                    player.sendMessage(TextFormat.colorize(this.getConfig().getString("Format"))
                            .replace("%name%", event.getEntity().getName())
                            .replace("%health%", Float.toString(event.getEntity().getHealth())));
                }
            }
        }
    }


}
