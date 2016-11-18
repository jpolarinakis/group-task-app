package gta;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


/*
 * Note: This is only to add information to the datastore. NOT to retrieve. fromDatabase will be used for the later
 * */
public class DatabaseWork {
	private static final int PROJECT_LIST = 0;
	private static final int PERSON_LIST = 1;
	private static int USER_NUM = 0;
	private static int NEXT_PROJECT = 3;
	private String type; // The type of query we are going to send to the database
	private DatastoreService ds;
	DatabaseWork()
	{
		ds = DatastoreServiceFactory.getDatastoreService();//where we might need to change
	}
	DatabaseWork(String type)
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
	/*
	 * NEEDS TO TEST
	 * */
	public boolean confirmUser(String username, String password)
	{
		//find out what kind of "key" you need to import so this doesn't happen
		for(int i = 0; i < USER_NUM;i++)
		{
		Key key = KeyFactory.createKey("users", i);
		try {
			Entity users = ds.get(key);
			if(username.equals(users.getProperty(username))){
					if(username.equals(users.getProperty(password)))
						/*
						 * basically: if username && password are the same return true;
						 * */
							return true;}
		} catch (EntityNotFoundException e) {
			return false;
		}
		}
		/*
		 * incremented through, and user was not found
		 * */
		return false;
	}
	/*
	 * returns generic Entity based on the type and number. Returns null 
	 * */
	public Entity getEntity(String typeName, int typeNum)
	{
		Key retKey = KeyFactory.createKey(typeName, typeNum);
		Entity ret;
		try {
			ret = ds.get(retKey);
		} catch (EntityNotFoundException e) {
			return null;
		}
		return ret;
	}
	public boolean addNewProject(ArrayList<String> dataType, ArrayList<String> data)
	{
		Entity e = new Entity(type.toLowerCase());
		e.setProperty("key",NEXT_PROJECT);
		try{
		for(int i =0; i< dataType.size();i++)
			e.setProperty(dataType.get(i), data.get(i));
		ds.put(e);
		NEXT_PROJECT++;
		addProjectToList(data.get(0));
		return true;
	}
		catch(ArrayIndexOutOfBoundsException exception) {
			return false;
	}
	}
	private void addProjectToList(String string) {
		Entity e = new Entity("projects");
		e.setProperty("project_number",NEXT_PROJECT-1);
		e.setProperty("name",string);
		ds.put(e);
		
	}
	public boolean sendToDatastore(ArrayList<String> dataType, ArrayList<String> data, int Key)
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
	/*
	 * TEST CODE
	 * */

	public boolean testInsert()
	{
		boolean ret = false;
		
		return ret;
	}

}
