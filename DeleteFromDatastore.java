package com.gaedatastore;

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
		int numTasks = -1;
		GetFromDatastore g = new GetFromDatastore(ds);
		for(int i = 1; i < numGroups; i++){
			Entity e = g.get("group_list", i);
			String gName = (String) e.getProperty("name");
			if(gName.equals(groupName))
			{
				String numTasksString = (String) e.getProperty("num_tasks");
				numTasks = Integer.valueOf(numTasksString);
			}
			}
		if(numTasks == -1)
			return false;
		for(int i =1; i < numTasks; i++)
		{
			Key key = KeyFactory.createKey(groupName, i);
			ds.delete(key);
		}
		return ret;
	}
	public boolean deleteTask(String task, String group)
	{
		boolean stop = false;
		int j =1;
		while(!stop)
		{
			Key key = KeyFactory.createKey(group, j);
			Entity del;
			try {
				del = ds.get(key);
				String delName = (String) del.getProperty("task");
				if(delName.equals(task))
				{
					ds.delete(key);
					return true;
				}
				j++;
			} catch (EntityNotFoundException e) {
				
				return false;
			}
			
		}
		return true;
	}
}
