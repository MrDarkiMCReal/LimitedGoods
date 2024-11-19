package org.mrdarkimc.limitedgoods.cooldowns;

import org.bukkit.scheduler.BukkitRunnable;
import org.mrdarkimc.SatanicLib.chat.SimpleMessage;
import org.mrdarkimc.SatanicLib.loops.DelayTimer;
import org.mrdarkimc.SatanicLib.loops.StartStopAsyncTask;
import org.mrdarkimc.SatanicLib.taskManager.BaseTask;
import org.mrdarkimc.limitedgoods.LimitedGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CooldownHandler {
    private final List<BaseTask> taskList;

    public CooldownHandler(){
        taskList = new ArrayList<>();
        long delayPluginBoot = LimitedGoods.config.get().getLong("timers.delayPluginBoot");
        long activeTime = LimitedGoods.config.get().getLong("timers.activeTime");
        long timeBetweenActivation = LimitedGoods.config.get().getLong("timers.timeBetweenActivation");
        TimerTask task = new TimerTask(delayPluginBoot,activeTime,timeBetweenActivation);
        taskList.add(task);
        new StartStopAsyncTask(task);

        List<Integer> intlist = LimitedGoods.config.get().getIntegerList("timers.notifications");
        for (Integer integer : intlist) {
            firstRun(integer, task);
        }

    }
    void firstRun(long seconds, TimerTask task){
        long time = (task.getDelayPluginBoot()-seconds*20); //40 мин - 5 мин = через 35 мин.
        new BukkitRunnable(){

            @Override
            public void run() {

                String timeStr = DelayTimer.getCurrentTimeFormatted(seconds);
                SimpleMessage.Broadcast.sendListMessages(LimitedGoods.config.get().getStringList("messages.notification"), Map.of("{time}", timeStr));
            }
        }.runTaskLaterAsynchronously(LimitedGoods.getInstance(),time);
    }

}
