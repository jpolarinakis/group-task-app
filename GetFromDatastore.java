package com.gaedatastore;

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
			ret = null;
		}
		return  ret;
	}

}
