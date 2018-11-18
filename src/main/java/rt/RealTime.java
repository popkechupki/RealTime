package rt;

import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RealTime extends PluginBase {

    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleRepeatingTask(this, new TimeTask(), 1);
    }

    public int getTime() {
        String rdat = new SimpleDateFormat("HHmm").format(new Date()) + "0";
        int time = Integer.parseInt(rdat);
        time -= 6000;
        if (time < 0) {
            time += 24000;
        }
        return time;
    }

    public class TimeTask extends Thread {

        public TimeTask() {
            setName("RealTimeTask");
        }

        @Override
        public void run() {
            for (Level level : getServer().getLevels().values()) {
                level.setTime(getTime());
            }
        }
    }
}
