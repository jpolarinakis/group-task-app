package gta;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/*
 * Note: This is only to add information to the datastore. NOT to retrieve. fromDatabase will be used for the later
 * */
public class toDatabase {
	private String type; // The type of query we are going to send to the database
	private DatastoreService ds;
	toDatabase()
	{
		ds = DatastoreServiceFactory.getDatastoreService();
	}
	toDatabase(String type)
	{
		this.type = type;
		ds = DatastoreServiceFactory.getDatastoreService();
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public boolean sendToDatastore(ArrayList<String> dataType, ArrayList<String> data, String queryType)
	{
		Entity e = new Entity(queryType.toLowerCase());
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
