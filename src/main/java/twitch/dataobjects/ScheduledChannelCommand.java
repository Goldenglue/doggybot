package twitch.dataobjects;

import javax.persistence.Entity;

@Entity
public class ScheduledChannelCommand extends ChannelCommand {
    private long period;

    public ScheduledChannelCommand(String executor, String command, long period) {
        super(executor, command);
        this.period = period;
    }

    public ScheduledChannelCommand() {
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ScheduledChannelCommand{" +
                "period=" + period +
                '}';
    }
}
