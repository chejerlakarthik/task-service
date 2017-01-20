/**
 * 
 */
package org.tutorial.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chejerla Karthik
 *
 */
@XmlRootElement(name="UserTask")
public class Task {
	
	public enum Status {
		NOT_STARTED, IN_PROGRESS, COMPLETED, DEFERED, OVERDUE
	}
	
	private String taskName;
	private String taskOwner;
	private Status taskStatus;
	
	public Task(){
		//No-args constructor
	}
	
	/**
	 * Parameterized Constructor 
	 */
	public Task(String taskName, String taskOwner, Status taskStatus){
		this.taskName = taskName;
		this.taskOwner = taskOwner;
		this.taskStatus = taskStatus;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskOwner() {
		return taskOwner;
	}
	
	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	
	public Status getTaskStatus() {
		return taskStatus;
	}
	
	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}
}
