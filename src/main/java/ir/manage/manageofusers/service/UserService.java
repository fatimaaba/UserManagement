package ir.manage.manageofusers.service;

import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
public interface UserService {


    void addUser(AddUserRequest addUserRequest);

    void updateUser(UpdateUserRequest updateUserRequest);

    void deleteUser(String externalId);
}
