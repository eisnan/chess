import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.impl.BaseFXRobot;
import org.junit.Test;

import java.awt.*;
import java.util.Random;

public class Test2 {

    @Test
    public void test() throws AWTException, InterruptedException {


        while (true) {
            Robot robot = new Robot();
            int y = new Random().nextInt(100);
            int x = new Random().nextInt(100);
            robot.mouseMove(x, y);

            Thread.sleep(18000);
        }
    }
}
