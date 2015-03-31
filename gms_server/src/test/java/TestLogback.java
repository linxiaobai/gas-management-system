import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kevin on 2015/3/30.
 */
public class TestLogback {
    @Test
    public void testLog() {
        Logger logger = LoggerFactory.getLogger(TestLogback.class);
        logger.info("test {}","hello,world");
    }
}
