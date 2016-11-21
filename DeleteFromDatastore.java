package refactoredGTA;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class DeleteFromDatastore {
	DatastoreService ds;
	public DeleteFromDatastore(DatastoreService ds)
	{
		this.ds = ds;
	}
	public boolean deleteGroup(String groupName, int numGroups)
	{
		boolean ret = true;
		int toDelete = -1;
		for(int j= 0; j< numGroups; j++)
		{
			Key key = KeyFactory.createKey("group_list", j);
			Entity del;
			try {
				del = ds.get(key);
				String delName = (String) del.getProperty("name");
				if(delName.equals(groupName))
				{
					toDelete = (int) del.getProperty("num");
				}
			} catch (EntityNotFoundException e) {
				return false;
			}
		}
		if(toDelete == -1)
			return false;
		for(int i =0; i < toDelete; i++)
		{
			Key key = KeyFactory.createKey(groupName, i);
			ds.delete(key);
		}
		return ret;
	}
	public boolean deleteUserFromGroup(String user,String group, int numGroups)
	{
		
		for(int j= 0; j< numGroups; j++)
		{
			Key key = KeyFactory.createKey("group_list", j);
			Entity del;
			try {
				del = ds.get(key);
				String delName = (String) del.getProperty("name");
				if(delName.equals(group))
				{
					ArrayList<String> toDel = (ArrayList<String>) del.getProperty("users");
					toDel.remove(user);
					del.setIndexedProperty("users", toDel);
					ds.put(del);
					return true;
				}
			} catch (EntityNotFoundException e) {
				return false;
			}
		}
		return true;
	}
	public boolean deleteTask(String task, String group, int numGroups)
	{
		for(int j= 0; j< numGroups; j++)
		{
			Key key = KeyFactory.createKey(group, j);
			Entity del;
			try {
				del = ds.get(key);
				String delName = (String) del.getProperty("task");
				if(delName.equals(task))
				{
					ds.delete(key);
				}
			} catch (EntityNotFoundException e) {
				return false;
			}
		}
		return true;
	}
	public int getNum(String type, String identifier)
	{
		int ret =0;
		
		return ret;
	}
}
