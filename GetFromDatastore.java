package refactoredGTA;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class GetFromDatastore {
	DatastoreService ds;
	public GetFromDatastore(DatastoreService ds)
	{
		this.ds = ds;
	}
	public Entity get(String typeName, int typeNum)
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
	public ArrayList<String> get(Entity e, ArrayList<String> dataTypes)
	{
		ArrayList<String> ret = new ArrayList<String>();
		for(int i = 0; i < dataTypes.size(); i++)
			ret.add((String) e.getProperty(dataTypes.get(i)));
		return ret;
	}
	public ArrayList<String> get(String typeName, int typeNum, ArrayList<String> dataTypes)
	{
		Key retKey = KeyFactory.createKey(typeName, typeNum);
		Entity ret;
		try {
			ret = ds.get(retKey);
		} catch (EntityNotFoundException e) {
			return null;
		}
		return this.get(ret, dataTypes);
	}

}
