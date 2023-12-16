package ir.manage.manageofusers.service;

import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateManagerRequest;
import ir.manage.manageofusers.dto.response.ManagerListResponse;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.entity.Manager;
import ir.manage.manageofusers.exceptions.DuplicateEmailException;
import ir.manage.manageofusers.exceptions.ManagerNotFoundException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
public interface ManagerService {


    void addManager(AddManagerRequest addManagerRequest) throws ManagerNotFoundException, DuplicateEmailException;

    void updateManager(UpdateManagerRequest updateManagerRequest) throws UserNotFoundException, ManagerNotFoundException;

    ManagerListResponse findAllManagers(String name, String lastName, String email, String password, Integer page, Integer size);

    Manager getManagerByEmail(String email) throws ManagerNotFoundException;

    void deleteManager(String externalId) throws UserNotFoundException, ManagerNotFoundException;
}
