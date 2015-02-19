package Kekse;

/**
 * Created by papazong on 19.02.2015.
 */
public class Keksdose
{
    private static int kekse;

    public static void addKeks(int anz)
    {
        kekse +=anz;
    }

    public static int getKekse() {
        return kekse;
    }

    public static void setKekse(int kekse) {
        Keksdose.kekse = kekse;
    }
}
