package Implementation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.Employee;
import Model.Task;
import Services.TaskService;

public class TaskServiceImplementation implements TaskService {
	
	private EntityManager em;
	public TaskServiceImplementation()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimeIntern");
		em=emf.createEntityManager();
	}
	
	public void CreateNewTask(int idTask, String title, String description, int assignee, Date dueDate) {
		Task newTask = new Task(idTask, title, description, assignee, dueDate);
		em.getTransaction().begin();
		em.persist(newTask);
		em.getTransaction().commit();

	}

	public Task ReadTask(int idTask) {
		Task readTask = em.find(Task.class, idTask);
		return readTask;

	}

	public void UpdateTask(int idTask, String title, String description, int assigne, Date dueDate) {
		Task readTask = em.find(Task.class, idTask);
		if(readTask != null) {
		em.getTransaction().begin();
		readTask.setTitle(title);
		readTask.setDescription(description);
		readTask.setAssignee(assigne);
		readTask.setDueDate(dueDate);
		
		em.getTransaction().commit();	
		}else {
			System.out.println("Task does not exist! Updating is not possible!");
		}

	}

	public void DeleteTask(int idTask) {
		Task readTask = em.find(Task.class, idTask);
		if(readTask != null) {
			em.getTransaction().begin();
			em.remove(readTask);
			em.getTransaction().commit();
		}else {
			System.out.println("Task does not exist! Delete is not possible!");
		}

	}

	public List<Task> listTasks() {
		TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t", Task.class);
		List<Task> allTasks = query.getResultList();
		return allTasks;
	}
//only smaller functionality :( I can do it better I promise, but when my brain is not tired like now!
	public List<Task> FindAllUnfinishedTasks() {

		Date todayIs = new Date(System.currentTimeMillis());
		List<Task> unfinishedTasks = new ArrayList<Task>();
		List<Task> allTasks=listTasks();
		
		  for (Task task : allTasks) {
		        if (task.getDueDate().after(todayIs)) {
		            unfinishedTasks.add(task);
		        }
		    }
		return unfinishedTasks;
	}



	public List<Task> FindFinishedTasksInPastMonth() {
		Date todayIs = new Date(System.currentTimeMillis());
		Calendar monthAgo = Calendar.getInstance();
		monthAgo.add(Calendar.MONTH, -1);
		Date lastMonth = new Date(monthAgo.getTimeInMillis());
		
		List<Task> allTasks=listTasks();
		List<Task> tasksInPastMonth = new ArrayList<Task>();;
		for (Task task : allTasks) {
		     Date dueDate = task.getDueDate();
		     if (dueDate.before(todayIs) && dueDate.after(lastMonth)) {
		    	 tasksInPastMonth.add(task);
		     }
		}
		return tasksInPastMonth;
	}
	
}
