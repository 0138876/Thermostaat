
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Thermostaat
{
    boolean operational;
    boolean progActive;
    double minTemp;
    double maxTemp;
    double curTemp;
    double startTemp;
    double stepSize;
    double nightTemp;
    double dayTemp;
    ArrayList<Thermostaat> snapshot = new ArrayList<Thermostaat>();

    public Thermostaat()
    {
        boolean operational = true;
        boolean progactive = true;
        minTemp = 15;
        maxTemp = 26;
        curTemp = 19.5;
        startTemp = 6.0;
        stepSize = 0.5;
        nightTemp = 20;
        dayTemp = 24;
    }

    public Thermostaat(boolean operational, boolean progActive, double minTemp, double maxTemp, double curTemp, double startTemp, double stepSize, double nightTemp, double dayTemp)
    {
        this.operational = operational;
        this.progActive = progActive;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.curTemp = curTemp;
        this.startTemp = startTemp;
        this.stepSize = stepSize;
        this.nightTemp = nightTemp;
        this.dayTemp = dayTemp;
    }

    public boolean getOperational()
    {
        return operational;
    }

    public void setOperational(boolean operational)
    {
        this.operational = operational;
    }

    public boolean getProgActive()
    {
        return progActive;
    }

    public void setProgActive(boolean progActive)
    {
        this.progActive = progActive;
    }

    public double getMinTemp()
    {
        return minTemp;
    }

    public void setMinTemp(double minTemp)
    {
        this.minTemp = minTemp;
    }

    public double getMaxTemp()
    {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp)
    {
        this.maxTemp = maxTemp;
    }

    public double getCurTemp()
    {
        return curTemp;
    }

    public void setCurTemp(double curTemp)
    {
        this.curTemp = curTemp;
    }

    public double getStartTemp()
    {
        return startTemp;
    }

    public void setStartTemp(double startTemp)
    {
        this.startTemp = startTemp;
    }

    public double getStepSize()
    {
        return stepSize;
    }

    public void setStepSize(double stepSize)
    {
        this.stepSize = stepSize;
    }

    public double getNightTemp()
    {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp)
    {
        this.nightTemp = nightTemp;
    }

    public double getDayTemp()
    {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp)
    {
        this.dayTemp = dayTemp;
    }

    public void snapShot()
    {

            if (snapshot.size() < 10)
            {
                snapshot.add(new Thermostaat(this.operational, this.progActive, this.minTemp, this.maxTemp, this.curTemp, this.startTemp, this.stepSize, this.nightTemp, this.dayTemp));
            }
            else {
                snapshot.remove(0);
                snapshot.add(new Thermostaat(this.operational, this.progActive, this.minTemp, this.maxTemp, this.curTemp, this.startTemp, this.stepSize, this.nightTemp, this.dayTemp));
            }
        }



    public void showSnapShots(){

        for (int i = 0; i < snapshot.size(); i++)
        {
            System.out.println(snapshot.get(i));
        }
    }

    @Override
    public String toString()
    {
        return "Thermostaat{" +
                "operational=" + operational +
                ", progActive=" + progActive +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", curTemp=" + curTemp +
                ", startTemp=" + startTemp +
                ", stepSize=" + stepSize +
                ", nightTemp=" + nightTemp +
                ", dayTemp=" + dayTemp;
    }
}