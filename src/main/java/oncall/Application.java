package oncall;

import oncall.controller.MainController;
import oncall.controller.ScheduleController;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new ScheduleController());
        mainController.run();
    }
}
