package model;
import java.io.Serializable;
import java.util.*;

public abstract class Generator implements Serializable
{
	private static final long serialVersionUID = 1L;
	private double nennLeistung;
	private boolean running;
	private String standort;
	
	public Generator(String standort) throws KraftwerkException
	{
		setStandort(standort);
		setRunning(false);
	}
	//---------------------------- getter ------------------
	public double getNennLeistung()
	{
		return nennLeistung;
	}
	public boolean isRunning()
	{
		return running;
	}
	public String getStandort()
	{
		return standort;
	}
	//---------------------------- setter ------------------
	public void setNennLeistung(double nennLeistung) throws KraftwerkException
	{   //Plausibilitätsprüfung erfolgt bei den jeweiligen Sub-Klassen
		this.nennLeistung = nennLeistung;
	}
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	public void setStandort(String standort) throws KraftwerkException
	{
		if (standort != null)
			this.standort = standort;
		else 
			throw new KraftwerkException("Null-Referenz für Generator.setStandort");
	}
	//---------------------------- others ------------------
	public abstract double berechneLeistung();
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder(50);
		if (standort.length() >= 4)
			sb.append(standort.substring(0, 4));
		else
			sb.append(standort);
		sb.append(", Nenn-Leistung ").append(nennLeistung).append(" MW, ");
		if (running)
			sb.append("läuft");
		else
			sb.append("steht");
		return sb.toString();
	}
	//----------------------------- Comparatoren -----------------------------
	public static class StandortComparator implements Comparator<Generator>
	{
		public int compare(Generator gen1, Generator gen2)
		{
				return gen1.getStandort().compareTo(gen2.getStandort());
		}
	}
	public static class LeistungComparator implements Comparator<Generator>
	{
		boolean aktLeistung;
		public LeistungComparator(boolean aktLeistung)
		{
			this.aktLeistung = aktLeistung;
		}
		public int compare(Generator g1, Generator g2)
		{
			if (aktLeistung)
				return Double.compare(g2.berechneLeistung(), g1.berechneLeistung());
			else
				return Double.compare(g2.getNennLeistung(), g1.getNennLeistung());
		}
	}

}
