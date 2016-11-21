package refactoredGTA;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

public class AddToDatastore {
	private DatastoreService ds;
	public AddToDatastore(DatastoreService ds)
	{
		this.ds = ds;
	}
	boolean add(String type, ArrayList<String> dataType, ArrayList<String> data)
	{
		Entity e = new Entity(type.toLowerCase());
		try{
		for(int i =0; i< dataType.size();i++)
			e.setProperty(dataType.get(i), data.get(i));
		ds.put(e);
		return true;}
		catch(ArrayIndexOutOfBoundsException exception) {
			return false;
		}
	}
			
}