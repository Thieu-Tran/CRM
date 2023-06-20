package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRole(){
        return roleRepository.findAllRole();
    }

    public RoleModel getRoleById(int id){
        return roleRepository.findRoleById(id);
    }

    public boolean insertRole(String name,String description){
        return roleRepository.insertRole(name,description);
    }

    public boolean deleteRoleById(int id){

        return roleRepository.deleteRoleById(id);
    }

    public boolean updateRoleById(String name, String description, int id){
        return roleRepository.updateRoleById(name,description,id);
    }

}
