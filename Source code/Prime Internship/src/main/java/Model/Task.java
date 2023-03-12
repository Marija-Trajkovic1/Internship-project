package Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Task")
public class Task {
	
	@Id
	@Column(name = "idTask")
	private int idTask;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "assignee")
	private int assignee;
	
	@Column(name = "dueDate")
	private Date dueDate;
	
	public Task()
	{
		
	}
	
	public Task(int idTask, String title, String description, int assignee, Date dueDate)
	{
		this.idTask = idTask;
		this.title = title;
		this.description = description;
		this.assignee = assignee;
		this.dueDate = dueDate;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAssignee() {
		return assignee;
	}

	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
}
