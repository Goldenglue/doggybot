package twitch.dataobjects;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ChannelCommand {
    @Id
    @GeneratedValue
    private long id;
    private String executor;
    private String command;

    public ChannelCommand(String executor, String command) {
        this.executor = executor;
        this.command = command;
    }

    public ChannelCommand() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "ChannelCommand{" +
                "id=" + id +
                ", executor='" + executor + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
