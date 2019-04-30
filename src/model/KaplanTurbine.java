package model;

public class KaplanTurbine extends Generator
{
	private static final long serialVersionUID = 1L;
	private int nennDrehzahl;
	
	public KaplanTurbine(String standort, double nennLeistung, int nennDrehzahl) throws KraftwerkException
	{
		super(standort);
		setNennLeistung(nennLeistung);
		setNennDrehzahl(nennDrehzahl);
	}

	//---------------------------- getter ------------------
	public int getNennDrehzahl()
	{
		return nennDrehzahl;
	}
	//---------------------------- setter ------------------
	public void setNennDrehzahl(int nennDrehzahl) throws KraftwerkException
	{
		if (nennDrehzahl >= 50 && nennDrehzahl <= 150 )
			this.nennDrehzahl = nennDrehzahl;
		else
			throw new KraftwerkException("Falscher Wert für KaplanTurbine.setNennDrehzahl("+nennDrehzahl+") !!");
	}
	public void setNennLeistung(double nennLeistung) throws KraftwerkException
	{
		if (nennLeistung >= 5 && nennLeistung <= 100 )
			super.setNennLeistung(nennLeistung);
		else
			throw new KraftwerkException("Falscher Wert für KaplanTurbine.setNennLeistung("+nennLeistung+") !!");
	}
	//---------------------------- others ------------------
	public double berechneLeistung()
	{
		if (isRunning())
			return getNennLeistung();
		else
			return 0;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("Kaplan: ").append(super.toString()).append(" -> derzeit ")
		  .append(berechneLeistung()).append(" MW");
		return sb.toString();
	}
}
