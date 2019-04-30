package test;
import model.KaplanTurbine;
import model.KraftwerkException;
import model.Kraftwerksanlage;
import model.Windrad;


public class TestSave
{
	public static void main(String[] args)
	{
		try
		{
			Kraftwerksanlage ka = new Kraftwerksanlage();
			KaplanTurbine k1 = new KaplanTurbine("Gmunden",50f,100);
			KaplanTurbine k2 = new KaplanTurbine("Au",10f,50);
			KaplanTurbine k3 = new KaplanTurbine("Bad Goisern",50f,100);
			KaplanTurbine k4 = new KaplanTurbine("Grundlsee",40f,100);
			KaplanTurbine k5 = new KaplanTurbine("Obertraun",30f,100);
			Windrad w1 = new Windrad("Sandling",1f);
			Windrad w2 = new Windrad("Traunstein",3f);
			Windrad w3 = new Windrad("Zinken",5f);
			Windrad w4 = new Windrad("Tauplitz",7f);
			
			ka.addGenerator(k5);
			ka.addGenerator(w2);
			ka.addGenerator(k2);
			ka.addGenerator(w4);
			ka.addGenerator(w1);
			ka.addGenerator(k3);
			ka.addGenerator(k1);
			ka.addGenerator(w3);
			ka.addGenerator(k4);
//			ka.saveGeneratoren(null);
//			ka.saveGeneratoren("x:\\scratch\\generatoren_ausgeschaltet.ser");
//			ka.saveGeneratoren("c:\\yxyxyxyx\\generatoren_ausgeschaltet.ser");
			ka.setRunning(true);
			ka.setWindstaerke(50);
			String filename = "d:\\generatoren.ser";
			ka.saveGeneratoren(filename);
			System.out.println("save ("+filename+") erledigt ...............");
		}
		catch (KraftwerkException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
