package service;

import model.RoleModel;
import model.StatusModel;
import model.TaskModel;
import repository.TaskRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();

    public List<TaskModel> getAllTask(){
        return taskRepository.findAllTask();
    }

    public TaskModel getTaskById(int id){
        return taskRepository.findTaskById(id);
    }

    public List<TaskModel> getTaskByEmail(String email){
        return taskRepository.findTaskByEmail(email);
    }

    public boolean insertTask(String name, int jobId, int userId, Date startDate, Date endDate, int statusId){
        return taskRepository.insertTask(name,jobId,userId,startDate,endDate,statusId);
    }

    public boolean deleteTask(int id){
        return taskRepository.deleteTaskById(id);
    }

    public boolean updateTask(String name,Date startDate,Date endDate,int userId,int jobId,int statusId,int id){
        return taskRepository.updateTaskById(name,startDate,endDate,userId,jobId,statusId,id);
    }

    //Tổng hợp theo trạng thái của task, áp dụng vào chức năng detail task,job
    public List<Integer> countByStatusName(List<StatusModel> statusModelList, List<TaskModel> taskModelList) {
        List<Integer> countStatusList = new ArrayList<>();

        for (StatusModel statusModel : statusModelList) {
            int count = 0;
            for (TaskModel taskModel : taskModelList) {
                if (taskModel.getStatusName().equalsIgnoreCase(statusModel.getName())) {
                    count++;
                }
            }
            countStatusList.add(count);
        }
        return countStatusList;
    }
}
