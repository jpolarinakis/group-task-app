package com.gaedatastore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class FinalProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		DatastoreHome home = new DatastoreHome();
		
		resp.setContentType("text/plain");
		resp.getWriter().println("This is Cardinal, making a plan come together.");
		
		resp.setIntHeader("Refresh", 5);
		String functionID = "id";
		
		// Add New User
		if (req.getParameter(functionID) == "0"){
			ArrayList<String> userData = new ArrayList<String>();
			ArrayList<String> data = new ArrayList<String>();
			userData.add("username");
			userData.add("password");
			data.add(req.getParameter("username"));
			data.add(req.getParameter("password"));
			if(home.addNewUser(userData, data)){
				// Returns true if process was successful
			}
		}
		
		// Confirm User
		if (req.getParameter(functionID) == "1") {
			if (home.confirmUser(req.getParameter("username"), req.getParameter("password"))){
				
			}
		}
		
		// Make New Group
		if (req.getParameter(functionID) == "2"){
			ArrayList<String> projDetails = new ArrayList<String>();
			ArrayList<String> data = new ArrayList<String>();
			projDetails.add("group_description");
			data.add(req.getParameter("group_description"));
			data.addAll((ArrayList<String>)req.getAttribute("group_members"));
			for (int i = projDetails.size(); i <= data.size(); i++){
				projDetails.add("group_member");
			}
			if(home.addNewProject(projDetails, data)){
				// Returns true if process was successful
			}
		}
		
		// Edit Group
		if (req.getParameter(functionID) == "3"){
			ArrayList<String> projDetails = new ArrayList<String>();
			ArrayList<String> data = new ArrayList<String>();
			projDetails.add("group_description");
			data.add(req.getParameter("group_description"));
			data.addAll((ArrayList<String>)req.getAttribute("group_members"));
			for (int i = projDetails.size(); i <= data.size(); i++){
				projDetails.add("group_member");
			}
			Update groupEdit = new Update(req.getParameter("group_name"), 0, projDetails, data);
			if (home.modify.modify(groupEdit)){
				// Returns true if process was successful
			}
		}
		
		// Add New Task
		if (req.getParameter(functionID) == "4"){
			ArrayList<String> taskDetails = new ArrayList<String>();
			ArrayList<String> data = new ArrayList<String>();
			taskDetails.add("group_name");
			taskDetails.add("task_name");
			taskDetails.add("task_description");
			taskDetails.add("due_date");
			taskDetails.add("due_time");
			taskDetails.add("notifications");
			data.add(req.getParameter("group_name"));
			data.add(req.getParameter("task_name"));
			data.add(req.getParameter("task_description"));
			data.add(req.getParameter("due_date"));
			data.add(req.getParameter("group_name"));
			data.add(req.getParameter("group_name"));
			data.addAll((ArrayList<String>)req.getAttribute("assigned_users"));
			for (int i = taskDetails.size(); i <= data.size(); i++){
				taskDetails.add("assigned_user");
			}
			/*
			if (home.addNewTask("task", (ArrayList<String>)req.getAttribute("taskDetails"), (ArrayList<String>)req.getAttribute("data"))){
				// Returns true if process was successful
			}
			*/
		}
		
		// Edit Task
		if (req.getParameter(functionID) == "5"){
			ArrayList<String> taskDetails = new ArrayList<String>();
			ArrayList<String> data = new ArrayList<String>();
			taskDetails.add("group_name");
			taskDetails.add("task_name");
			taskDetails.add("task_description");
			taskDetails.add("due_date");
			taskDetails.add("due_time");
			taskDetails.add("notifications");
			data.add(req.getParameter("group_name"));
			data.add(req.getParameter("task_name"));
			data.add(req.getParameter("task_description"));
			data.add(req.getParameter("due_date"));
			data.add(req.getParameter("group_name"));
			data.add(req.getParameter("group_name"));
			data.addAll((ArrayList<String>)req.getAttribute("assigned_users"));
			for (int i = taskDetails.size(); i <= data.size(); i++){
				taskDetails.add("assigned_user");
			}
			Update taskEdit = new Update(req.getParameter("group_name"), 0, taskDetails, data);
			if (home.modify.modify(taskEdit)){
				// Returns true if process was successful
			}
		}
		
		// Update Groups
		if (req.getParameter(functionID) == "6") {
			ArrayList<String> ret = new ArrayList<String>();
			for(int i = 0; i < home.NUM_GROUPS; i++){
				Key key = KeyFactory.createKey("group_name", i);
			}
			// Currently incomplete functionality, should return ArrayList of groups
		}
		//test code below
		TestCode tc = new TestCode(ds, home);
		tc.testAddTaskToGroupTrue();
		tc.testAddTaskToGroupFalse();
		tc.testGetTrue();
		tc.testsGetFalse();
		tc.deleteGroupFalse();
		tc.deleteTaskFalse();
		tc.deleteTaskTrue();
		tc.deleteGroupTrue();
		System.out.println("end of tests");
		//end test code
	}	 
			
}
