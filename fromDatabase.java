package gta;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyContainer;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Entity;

public class fromDatabase {
	DatastoreService ds;
	public fromDatabase()
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();	
	}
	/*
	 *  getUser will return an ArrayList containing the particulars of a specific user that will be passed
	 *  in as a string argument
	 * */
	String getUser(String username)
	{
		Filter propertyFilter =
			    new Query.FilterPredicate("username", FilterOperator.EQUAL, username);
		Query q =
			    new Query("user").setFilter(propertyFilter);
		  PreparedQuery pq = ds.prepare(q);	
		  Entity result = pq.asSingleEntity();
		  ArrayList<String> ret = new ArrayList<String>();
		  //CHANGE TO APPROPRIATE RETURN TYPE
		return null;
	}
	/*
	 * getAbstract will return a 2D table (formatted as an ArrayList<ArrayList<String>>) that will be dependent on the
	 * Entity type that is passed in as an argument
	 * */
}
