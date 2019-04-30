package model;

public class Windrad extends Generator
{
	private static final long serialVersionUID = 1L;
	private Kraftwerksanlage zentrale;
	
	public Windrad(String standort, double nennLeistung) throws KraftwerkException
	{
		super(standort);
		setNennLeistung(nennLeistung);
	}
	public Kraftwerksanlage getZentrale()
	{
		return zentrale;
	}

	public void setNennLeistung(double nennLeistung) throws KraftwerkException
	{
		if (nennLeistung >= .1f && nennLeistung <= 10 )
			super.setNennLeistung(nennLeistung);
		else
			throw new KraftwerkException("Falscher Wert für Windrad.setNennLeistung("+nennLeistung+") !!");

	}
	public void setZentrale(Kraftwerksanlage zentrale) throws KraftwerkException
	{
		if (zentrale != null)
			this.zentrale = zentrale;
		else
			throw new KraftwerkException("Null-Referenz für Windrad.setZentrale");
	}

	public double berechneLeistung()
	{
		if (isRunning())
		{
			double diff = zentrale.getWindstaerke()-50f;
			double proz = diff * 2;
			return (getNennLeistung()*(100+proz))/100;
		}
		else
			return 0.;
	}
	public void emergencyDown()
	{
		zentrale.setAlleWindraederRunning(false);
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("Windrad: " ).append(super.toString()).append(" -> derzeit ")
		  .append(zentrale!=null?berechneLeistung():"keine Zentrale -> 0").append(" MW");
		return sb.toString();
	}
}
