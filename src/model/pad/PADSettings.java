package model.pad;

public class PADSettings
{
	public float p, a, d;
	
	public PADSettings(float p, float a, float d)
	{
		super();
		
		this.p = p;
		this.a = a;
		this.d = d;
	}
	
	public PADSettings clone()
	{
		return new PADSettings(p, a, d);
	}
}
