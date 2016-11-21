package refactoredGTA;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ModifyDatastore {
	private DatastoreService ds;
	public ModifyDatastore(DatastoreService ds)
	{
		this.ds = ds;
	}
	public boolean modify(Update u)
	{
		
		Key retKey = KeyFactory.createKey(u.getTypeName(), u.getTypeNum());
		Entity mod;
		try {
			mod = ds.get(retKey);
		} catch (EntityNotFoundException e) {
			return false;
		}
		for(int i =0; i < u.getUpdateData().size();i++)
			mod.setProperty(u.getUpdateFields().get(i), u.getUpdateData().get(i));
		ds.put(mod);
				
		return true;
	}
}
