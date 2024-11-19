package org.mrdarkimc.limitedgoods.cooldowns;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.mrdarkimc.SatanicLib.chat.SimpleMessage;
import org.mrdarkimc.SatanicLib.loops.DelayTimer;
import org.mrdarkimc.SatanicLib.taskManager.interfaces.TimedTask;
import org.mrdarkimc.limitedgoods.LimitedGoods;

import java.util.List;
import java.util.Map;

public class TimerTask extends TimedTask {
    public TimerTask(long delayPluginBoot, long taskActiveTimeInTicks, long timeBetweenActivation) {
        super(delayPluginBoot, taskActiveTimeInTicks, timeBetweenActivation);
    }


    @Override
    public void activate() {
        LimitedGoods.isShopActive = true;
        Bukkit.getLogger().info("Магазин активирован.");
        SimpleMessage.Broadcast.sendListMessages(LimitedGoods.config.get().getStringList("messages.start"), Map.of("{time}", DelayTimer.getCurrentTimeFormatted(getTimeBetweenActivation()/20)));
    }

    @Override
    public void deactivate() {
        LimitedGoods.isShopActive = false;
        Bukkit.getLogger().info("Магазин выключен.");
        SimpleMessage.Broadcast.sendListMessages(LimitedGoods.config.get().getStringList("messages.end"), Map.of("{time}", DelayTimer.getCurrentTimeFormatted(getTimeBetweenActivation()/20)));
        LimitedGoods.getInstance().menus.reset();
        List<Integer> intlist = LimitedGoods.config.get().getIntegerList("timers.notifications");
        for (Integer integer : intlist) {
            sendNotification(integer);
        }

    }
    void sendNotification(long seconds){
        long time = (getTimeBetweenActivation()-seconds*20); //40 мин - 5 мин = через 35 мин.
        new BukkitRunnable(){

            @Override
            public void run() {

                String timeStr = DelayTimer.getCurrentTimeFormatted(seconds);
                SimpleMessage.Broadcast.sendListMessages(LimitedGoods.config.get().getStringList("messages.notification"), Map.of("{time}", timeStr));
                Bukkit.getLogger().info("Магазин будет активирован через: " + timeStr);
            }
        }.runTaskLaterAsynchronously(LimitedGoods.getInstance(),time);
    }
}
