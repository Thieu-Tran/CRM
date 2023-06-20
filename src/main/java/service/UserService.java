package service;

import model.RoleModel;
import model.TaskModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepository();
    public List<UserModel> getAllUser(){
        return userRepository.findAllUser();
    }

    public List<UserModel> getUserByEmailAndPassword(String email,String password){
        return userRepository.findByEmailAndPassword(email,password);
    }

    public UserModel getById(int id){
        return userRepository.findById(id);
    }

    public boolean insertUser(String email,String password,String fullname,int roleId){
        return userRepository.insertUser(fullname, email,password,roleId);
    }

    //Delete user
    public boolean deleteUser(int id){
        return userRepository.deleteUserById(id);
    }

    public boolean updateUserById(String email,String password,String fullname,int roleId,int id){
        return userRepository.updateUserById(email,password,fullname,roleId,id);
    }

    public List<TaskModel> getTaskByUserId(int id){
        return userRepository.findTaskByUserId(id);
    }




}
