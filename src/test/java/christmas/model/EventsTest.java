package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.Event;
import java.util.List;
import org.junit.jupiter.api.Test;

class EventsTest {

    @Test
    void 모든_이벤트들을_목록에_담는다() {
        List<Event> Events = christmas.model.Events.list(120000, 25, 2, 2);

        assertThat(Events.size()).isEqualTo(5);
    }
}