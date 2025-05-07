package mk.ukim.finki.labb.events;

import org.springframework.context.ApplicationEvent;

public class HostChangedEvent extends ApplicationEvent {

    public HostChangedEvent(Object source) {
        super(source);
    }

}
