package refactoredGTA;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DatastoreHome {
	DatastoreService ds;
	AddToDatastore add;
	ModifyDatastore modify;
	DeleteFromDatastore delete;
	private static int USER_NUM = 0;
	private static int NUM_GROUPS = 0;
	/*
	 * Constructor: makes the class
	 * */
	DatastoreHome()
	{
		ds = DatastoreServiceFactory.getDatastoreService();
		add = new AddToDatastore(ds);
		modify = new ModifyDatastore(ds);
		delete = new DeleteFromDatastore(ds);
	}
	public int getGroupNums()
	{
		return NUM_GROUPS;
	}
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
	public boolean addNewTask(String project, ArrayList<String> entries, ArrayList<String> data)
	{
		return add.add(project, entries, data);
	}
	public boolean addNewUser(ArrayList<String> userData, ArrayList<String> data)
	{
		userData.add("user_ID");
		String nextUser = Integer.toString(USER_NUM);
		data.add(nextUser);
		USER_NUM++;
		return add.add("user", userData, data);
	}
	public boolean addNewProject(String projName, ArrayList<String> projDetails, ArrayList<String> data)
	{
		return add.add(projName, projDetails, data);
	}
	public ArrayList<String> getAllUsers()
	{
		ArrayList<String> ret = new ArrayList<String>();
		for(int i = 0; i <USER_NUM; i++){
			Key key = KeyFactory.createKey("users", i);
			try {
				Entity users = ds.get(key);
				String un = (String) users.getProperty("username");
				ret.add(un);
				
			} catch (EntityNotFoundException e) {
				return null;
			}
		}
		return ret;
	}
	

}
	
	
//end of class
