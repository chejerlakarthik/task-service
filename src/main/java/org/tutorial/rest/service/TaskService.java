/**
 * 
 */
package org.tutorial.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tutorial.rest.model.Task;
import org.tutorial.rest.model.Task.Status;


/**
 * @author H158574
 *
 */
@Path("/taskservice/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

	Map<Integer,Task> listOfTasks = new HashMap<Integer,Task>();
	
	public void init(){
		Task dockersTask = new Task("Dockers Poc","Chejerla Karthik", Status.NOT_STARTED);
		Task jmoTask = new Task("JMO_VDERP","Narender", Status.DEFERED);
		
		listOfTasks.put(1, dockersTask);
		listOfTasks.put(2, jmoTask);
	}
	
	@POST
	@Path("/tasks/{taskname}")
	public String getTaskStatus(@PathParam("taskname") Integer taskId) {
		String returnStatus = "";
		try {
			returnStatus = listOfTasks.get(taskId).getTaskStatus().toString();
		} catch (Exception e) {
			returnStatus = "ERROR!!!";
		}
		return returnStatus;
	}
	
	@GET
	@Path("/tasks")
	public List<Task> getAllTasks(){
		List<Task> returnList = new ArrayList<Task>();
		for(Map.Entry<Integer, Task> entry: listOfTasks.entrySet()){
			returnList.add(entry.getValue());
		}
		return returnList;
	}
}
