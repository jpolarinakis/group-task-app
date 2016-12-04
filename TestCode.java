package com.gaedatastore;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class TestCode {
	DatastoreService ds;
	DatastoreHome home;
	public TestCode(DatastoreService ds, DatastoreHome home)
	{
		this.ds = ds;
		this.home = home;
	}
	
	public void test()
	{
		
		//tests the abillity to add a user
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		//adds traits
		traits.add("email");
		traits.add("username");
		traits.add("password");
		//add data
		data.add("jpolarinakis@utexas.edu");
		data.add("jpolar");
		data.add("password");
		// these two arraylists are what would be given by the server. 
		home.addNewUser(traits, data);
		ArrayList<String> allUsers = home.getAllUsers();
		for(int i =0; i < allUsers.size(); i++){
			System.out.print(allUsers.get(i));
			System.out.println(" " + Integer.toString(i));}
			
	}
	public void testAddTaskToGroupTrue()
	{
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		traits.add("num_tasks");
		data.add("0");
		traits.add("name");
		data.add("name of group");
		home.addNewProject(traits, data);
		traits.clear();
		data.clear();
		traits.add("test unit");
		data.add("test val");
		if(home.add.addTaskToGroup("name of group", traits, data, home.NUM_GROUPS))
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}
	public void testAddTaskToGroupFalse()
	{
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		if(home.add.addTaskToGroup("Test does not exist", traits, data, home.NUM_GROUPS))
			System.out.println("test failed");
		else{ System.out.println("test passed");}
	}
	public void testGetTrue()
	{
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		traits.add("test trait");
		data.add("test data");
		home.add.add("get test type", traits, data, 1);
		Entity e = home.get.get("get test type", 1);
		String test = (String) e.getProperty("test trait");
		if(test.equals("test data"))
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}
	public void testsGetFalse()
	{
		Entity e = home.get.get("get test type false", 1);
		if(e != null)
			System.out.println("test failed");
		else{System.out.println("test passed");}
	}
	public void deleteGroupTrue()
	{
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		traits.add("num_tasks");
		data.add("0");
		traits.add("name");
		data.add("name of group for final test");
		home.addNewProject(traits, data);
		if(home.delete.deleteGroup("name of group for final test", home.NUM_GROUPS))
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}
	public void deleteGroupFalse()
	{
		if(!home.delete.deleteGroup("name of group that doesn't exist", home.NUM_GROUPS))
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}
	public void deleteTaskTrue()
	{
		ArrayList<String> traits = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		traits.add("task");
		data.add("test_val");
		home.add.add("name of group for delete".toLowerCase(), traits, data, 1);
		boolean pass = home.delete.deleteTask("test_val", "name of group for delete");
		if(pass)
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}
	public void deleteTaskFalse()
	{
		if(!home.delete.deleteTask("test_val", "name of group that doesn't exist"))
			System.out.println("test passed");
		else{System.out.println("test failed");}
	}

}
