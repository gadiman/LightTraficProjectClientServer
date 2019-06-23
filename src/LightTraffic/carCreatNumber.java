package LightTraffic;

public class carCreatNumber {

    private static Integer ourInstance = null;

    public static Integer getInstance() {
        if (ourInstance == null)
            ourInstance = 0;
        return ourInstance++;
    }
}
