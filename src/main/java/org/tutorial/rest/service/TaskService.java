/**
 * 
 */
package org.tutorial.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.tutorial.rest.model.Task;
import org.tutorial.rest.model.Task.TaskStatus;
import org.tutorial.rest.model.TaskStatusResponse;


/**
 * @author Chejerla Karthik
 *
 */
@Path("/taskservice/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

	Map<Integer, Task> listOfTasks = new HashMap<Integer, Task>();

	public void init() {
		Task dockersTask = new Task("Dockers Poc", "Chejerla Karthik", TaskStatus.NOT_STARTED);
		Task jmoTask = new Task("JMO_VDERP", "Narender", TaskStatus.DEFERED);

		listOfTasks.put(1, dockersTask);
		listOfTasks.put(2, jmoTask);
	}

	@GET
	@Path("/tasks/{taskname}")
	public Response getTaskStatus(@PathParam("taskname") Integer taskId) {

		TaskStatusResponse taskStatusResponse = new TaskStatusResponse();
		Task requestedTask;

		// If requested task is found, respond with 200 with the task entity
		if (!((requestedTask = listOfTasks.get(taskId)) == null)) {
			taskStatusResponse.setTask(requestedTask);
			taskStatusResponse.setMessage("Task found and returned!!");
			return Response.status(Status.OK).entity(taskStatusResponse).build();
		}
		// Task not found, So 404.
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/tasks")
	public List<Task> getAllTasks() {
		List<Task> returnList = new ArrayList<Task>();
		for (Map.Entry<Integer, Task> entry : listOfTasks.entrySet()) {
			returnList.add(entry.getValue());
		}
		return returnList;
	}

	@POST
	@Path("/tasks")
	public TaskStatusResponse addNewTask(Task newTask) {
		TaskStatusResponse response = new TaskStatusResponse();

		int size = listOfTasks.size();
		listOfTasks.put(size + 1, newTask);

		response.setTask(newTask);
		response.setMessage("New task has been saved");
		return response;
	}

	@DELETE
	@Path("/tasks/{taskid}")
	public Response closeTask(@PathParam("taskid") Integer taskid) {

		TaskStatusResponse response = new TaskStatusResponse();
		Task requestedTask = listOfTasks.get(taskid);

		// If the requested task exists, delete the task & respond with 200
		if (!(requestedTask == null)) {
			listOfTasks.remove(taskid);
			response.setTask(requestedTask);
			response.setMessage("task has been deleted from the list");
			return Response.status(Status.OK).entity(response).build();
		}

		// If the requested task does not exist, respond with Status 404
		return Response.status(Status.NOT_FOUND).build();
	}
}
