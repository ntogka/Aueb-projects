import java.util.*;

public class ServiceCollection
{
	private ArrayList <AllServices> services = new ArrayList <AllServices>();
	
	// addService() adds a service in services list.
	public void addService(AllServices s)
	{
		services.add(s);
	}

	// listDisplay() displays a list of services.
	public void listDisplay()
	{
		for(AllServices s: services)
		{
			System.out.println("\n" + s.toString());		
		}
	}

	// Getters.
	public AllServices getService(int x)
	{
		return services.get(x);	
	}
}


