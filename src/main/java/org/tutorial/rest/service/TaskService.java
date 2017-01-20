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
import org.tutorial.rest.model.TaskStatusResponse;


/**
 * @author Chejerla Karthik
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
	
	@GET
	@Path("/tasks/{taskname}")
	public TaskStatusResponse getTaskStatus(@PathParam("taskname") Integer taskId) {
		TaskStatusResponse response = new TaskStatusResponse();
		Task requestedTask;
		if (!((requestedTask = listOfTasks.get(taskId)) == null)) {
			response.setTask(requestedTask);
			response.setMessage("Task found and returned!!");
		} else {
			response.setTask(null);
			response.setMessage("Task not found. Please check your request!!");
		}
		return response;
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
	
	@POST
	@Path("/tasks")
	public TaskStatusResponse addNewTask(Task newTask){
		TaskStatusResponse response = new TaskStatusResponse();
		
		int size = listOfTasks.size();
		listOfTasks.put(size+1, newTask);
		
		response.setTask(newTask);
		response.setMessage("New task has been saved");
		return response;
	}
}
