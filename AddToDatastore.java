package com.gaedatastore;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

public class AddToDatastore {
	private DatastoreService ds;
	public AddToDatastore(DatastoreService ds)
	{
		this.ds = ds;
	}
	 boolean add(String type, ArrayList<String> dataType, ArrayList<String> data, int num)
	{
		Entity e = new Entity(type.toLowerCase(),num);
		try{
		for(int i =0; i< dataType.size();i++)
			e.setProperty(dataType.get(i), data.get(i));
		ds.put(e);
		return true;}
		catch(IndexOutOfBoundsException exception) {
			return false;
		}
	}
	boolean addTaskToGroup(String name, ArrayList<String> dataType, ArrayList<String> data, int numGroups)
	{
		GetFromDatastore  g = new GetFromDatastore(ds);
		for(int i = 1; i < numGroups; i++){
		Entity e = g.get("group_list", i);
		String gName = (String) e.getProperty("name");
		if(gName.equals(name))
		{
			String newTaskNum = (String) e.getProperty("num_tasks");
			int taskNum = Integer.valueOf(newTaskNum);
			taskNum++;
			e.setProperty("num_tasks", String.valueOf(taskNum));
			ds.put(e);
			return add(name,dataType,data, taskNum);
		}
		}
		return false;
	}
	
			
}