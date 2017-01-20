/**
 * 
 */
package org.tutorial.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chejerla Karthik
 *
 */
@XmlRootElement(name="TaskStatus")
public class TaskStatusResponse {
	
	private Task task;
	private String message;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
