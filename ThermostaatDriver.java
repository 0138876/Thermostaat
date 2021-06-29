import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThermostaatDriver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Thermostaat thermoStaat = new Thermostaat(true, true, 15.5, 25, 19.5, 15, 0.5, 23, 25);
        boolean onOff = true;
        String activate;

        // draait altijd op achtergrond blijft maar loopen ook als de thermostaat uit staat
        while (onOff == true)
        {
            // als thermostaat uit staat voert hij dit code uit anders wordt dit geskipt.
            // als het uit staat dan activeert het de thermostaat bij een input maakt niet uit wat voor input.
            // als er geen input is blijft hij uit (leeg scherm)
            if (thermoStaat.getOperational() == false)
            {
                activate = in.nextLine();
                if (activate.length() > 0)
                {
                    thermoStaat.setOperational(true);
                }
            }
            // als thermostaat aan staat blijft het in deze loop draaien
            while (thermoStaat.getOperational() == true)
            {
                try
                {
                    System.out.println("Kies een van de mogelijke opties:");
                    System.out.println("1:  Tijdprogramma instellen");
                    System.out.println("2:  Temperatuur instellen");
                    System.out.println("3:  Huidige Temperatuur");
                    System.out.println("4:  Stepsize instellen");
                    System.out.println("5:  Fabrieksinstellingen hersetten");
                    System.out.println("6:  Snapshot");
                    System.out.println("7:  Zet uit \n");

                    int keuze = in.nextInt();
                    try
                    {
                        // code voor tijdprogramma om het te activeren of uit te zetten
                        // ook voor dag en nacht temperatuur instellen
                        if (keuze == 1)
                        {
                            try
                            {
                                int menukeuze;
                                do
                                {
                                    System.out.println("1:  Tijdprogramma aan/uit");
                                    System.out.println("2:  Dag Temperatuur");
                                    System.out.println("3:  Nacht Temperatuur");
                                    System.out.println("4:  Terug \n");
                                    menukeuze = in.nextInt();

                                    if (menukeuze == 1)
                                    {
                                        if (thermoStaat.getProgActive() == true)
                                        {
                                            System.out.println("Tijdprogramma is Uitgezet \n");
                                            thermoStaat.setProgActive(false);
                                        } else
                                        {
                                            System.out.println("Tijdprogramma is Aangezet \n");
                                            thermoStaat.setProgActive(true);
                                        }
                                    } else if (menukeuze == 2)
                                    {
                                        int dagkeuze;
                                        System.out.println("Dag temperatuur is: " + thermoStaat.getDayTemp() + "\n");
                                        System.out.println("Wilt u Dag temperatuur instellen? kies \n 1: Ja \n 2: Nee \n");
                                        dagkeuze = in.nextInt();
                                        do
                                        {
                                            if (dagkeuze == 1)
                                            {
                                                double dagtemp;
                                                do
                                                {
                                                    System.out.println("Welke temperatuur wilt u hebben overdag?\n");
                                                    dagtemp = in.nextDouble();
                                                    if (dagtemp > 6)
                                                    {
                                                        thermoStaat.setDayTemp(dagtemp);
                                                    } else if (dagtemp <= 0)
                                                    {
                                                        System.out.println("kies een temperatuur boven de 0 graden \n");
                                                    }
                                                } while (dagtemp <= 0);
                                            } else if (dagkeuze == 2)
                                            {

                                            }
                                        } while (dagkeuze < 0 || dagkeuze > 2);
                                        //nacht temperatuur code
                                    } else if (menukeuze == 3)
                                    {
                                        int dagkeuze;
                                        do
                                        {
                                            System.out.println("Nacht temperatuur is: " + thermoStaat.getNightTemp() + "\n");
                                            System.out.println("Wilt u Nacht temperatuur instellen? kies \n 1: Ja \n 2: Nee \n");
                                            dagkeuze = in.nextInt();
                                            if (dagkeuze == 1)
                                            {
                                                double nachttemp;
                                                do
                                                {
                                                    System.out.println("Welke temperatuur wilt u hebben in de nacht?\n");
                                                    nachttemp = in.nextDouble();
                                                    if (nachttemp > 6)
                                                    {
                                                        thermoStaat.setNightTemp(nachttemp);
                                                    } else if (nachttemp <= 0)
                                                    {
                                                        System.out.println("kies een temperatuur boven de 0 graden \n");
                                                    }
                                                } while (nachttemp <= 0);

                                            }
                                        } while (dagkeuze < 0 || dagkeuze > 2);
                                    } else if (menukeuze == 4)
                                    {
                                        System.out.println("");
                                    } else
                                    {
                                        System.out.println("foute keuze\n");
                                    }
                                } while (menukeuze < 0 || menukeuze > 4);
                            } catch (Exception error)
                            {
                                in.nextLine();
                                System.out.println("\nIncorrect Data type entered \n");
                            }
                        }
                    } catch (Exception e)
                    {
                        in.nextLine();
                        System.out.println("\nIncorrect Data type entered \n");
                    }

                    //code om de minimaale temperatuur in testellen en de maximale temperatuur
                    if (keuze == 2)
                    {
                        int men;
                        do
                        {
                            System.out.println("1:  Minimum Temperatuur");
                            System.out.println("2:  Maximum Temperatuur");
                            System.out.println("3:  Terug \n");
                            men = in.nextInt();
                            if (men == 1)
                            {
                                int minkeuze;
                                do
                                {
                                    System.out.println("Minumum Temperatuur is: " + thermoStaat.getNightTemp() + "\n");
                                    System.out.println("Wilt u de minumum temperatuur instellen? kies \n 1: Ja \n 2: Nee \n");
                                    minkeuze = in.nextInt();
                                    if (minkeuze == 1)
                                    {
                                        double mintemp;
                                        do
                                        {
                                            System.out.println("Welke temperatuur wilt u instellen?\n");
                                            mintemp = in.nextDouble();
                                            if (mintemp > 6)
                                            {
                                                thermoStaat.setMinTemp(mintemp);
                                            } else if (mintemp <= 0)
                                            {
                                                System.out.println("kies een temperatuur boven de 0 graden \n");
                                            }
                                        } while (mintemp <= 0);
                                    } else if (minkeuze == 2)
                                    {

                                    } else
                                    {
                                        System.out.println("foute keuze");
                                    }
                                } while (minkeuze < 0 || minkeuze > 2);

                            } else if (men == 2)
                            {
                                double maxtemp;
                                do
                                {
                                    System.out.println("Welke maximum temperatuur wilt u hebben\n");
                                    maxtemp = in.nextDouble();
                                    if (maxtemp > 6)
                                    {
                                        thermoStaat.setMaxTemp(maxtemp);
                                    } else if (maxtemp <= 0)
                                    {
                                        System.out.println("kies een temperatuur boven de 0 graden \n");
                                    }
                                } while (maxtemp <= 0);
                            }
                        } while (men < 0 || men > 3);
                    } else if (keuze == 3)
                    {
                        System.out.println("het is hier " + thermoStaat.getCurTemp() + "°C\n");
                    } else if (keuze == 4)
                    {
                        double stepsize;
                        do
                        {
                            System.out.println("Met hoeveel graden wilt u het verstellen\n");
                            stepsize = in.nextDouble();
                            thermoStaat.setStepSize(stepsize);
                        } while (stepsize <= 0.0);
                    } else if (keuze == 5)
                    {
                        System.out.println("wilt u zeker resetten? \n 1: Ja \n 2: Terug");
                        int reset = in.nextInt();
                        if (reset == 1)
                        {
                            thermoStaat = new Thermostaat();
                        } else
                        {
                            System.out.println("");
                        }

                    } else if (keuze == 6)
                    {
                        int snapshot;
                        do
                        {
                            System.out.println("1: Neem Snapshot");
                            System.out.println("2: Show Snapshots");
                            System.out.println("3: Terug");
                            snapshot = in.nextInt();
                            if (snapshot == 1)
                            {
                                thermoStaat.snapShot();
                            } else if (snapshot == 2)
                            {
                                thermoStaat.showSnapShots();
                            } else if (snapshot == 3)
                            {
                                System.out.println();
                            } else
                            {
                                System.out.println("Foute Keuze");
                            }
                        } while (snapshot <= 0 || snapshot > 3);
                    } else if (keuze == 7)
                    {
                        int aanUit;
                        do
                        {
                            System.out.println("Wilt u het systeem uitzetten? \n 1: Ja \n 2: Nee");
                            aanUit = in.nextInt();
                            if (aanUit == 1)
                            {
                                System.out.println("Tot Ziens!");
                                activate = "";
                                thermoStaat.setOperational(false);

                            } else if (aanUit == 2)
                            {
                                System.out.println();
                            } else
                            {
                                System.out.println("Foute Keuze");
                            }
                        } while (aanUit < 0 || aanUit > 2);
                    }


                } catch (Exception e)
                {
                    in.nextLine();
                    System.out.println("\nIncorrect Data type entered \n");
                }


            }
        }
    }
}
