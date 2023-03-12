package Services;
import Model.Task;
import java.util.List;

import java.sql.Date;

public interface TaskService {
	public void CreateNewTask(int idTask, String title, String description, int assignee, Date dueDate);
	public Task ReadTask(int idTask);
	public void UpdateTask(int idTask, String title, String description, int assigne, Date dueDate);
	public void DeleteTask(int idTask);
	public List<Task> FindAllUnfinishedTasks();
	public List<Task> FindFinishedTasksInPastMonth();
	public List<Task> listTasks();
}
