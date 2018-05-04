package com.bowhealth;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.ItemID;
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
        if (event instanceof EntityDamageByEntityEvent && event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && ((EntityDamageByEntityEvent) event).getDamager() instanceof Player && ((Player) ((EntityDamageByEntityEvent) event).getDamager()).getInventory().getItemInHand().getId() == ItemID.BOW) {
            Player player = (Player) ((EntityDamageByEntityEvent) event).getDamager();
            if (this.getConfig().getBoolean("actionbar.enable", false))
                player.sendActionBar(TextFormat.colorize(this.getConfig().getString("actionbar.format"))
                        .replace("%name%", event.getEntity().getName())
                        .replace("%health%", Float.toString(event.getEntity().getHealth())), this.getConfig().getInt("actionbar.fadein"), this.getConfig().getInt("actionbar.duration"), this.getConfig().getInt("actionbar.fadeout"));
            if (this.getConfig().getBoolean("message.enable", true))
                player.sendMessage(TextFormat.colorize(this.getConfig().getString("message.format"))
                        .replace("%name%", event.getEntity().getName())
                        .replace("%health%", Float.toString(event.getEntity().getHealth())));
        }
    }
}
