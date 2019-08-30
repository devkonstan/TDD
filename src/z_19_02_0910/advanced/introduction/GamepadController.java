package z_19_02_0910.advanced.introduction;

public class GamepadController {
    public static final int ANALOG_TO_RPM_RATIO = 100;

    private final MobileRobot mobileRobot;

    public GamepadController(MobileRobot mobileRobot) {
        this.mobileRobot = new MobileRobot();
    }

    public void analogPositionChanged(double xAxis, double yAxis) {
        mobileRobot.move(xAxis * ANALOG_TO_RPM_RATIO, yAxis * ANALOG_TO_RPM_RATIO);
    }
}