package ir.manage.manageofusers.service;

import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
public interface UserService {


    void addUser(AddUserRequest addUserRequest) throws DuplicateNationalCodeException;

    void updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException;

    UserListResponse findAllUsers(String name,String lastName,String email,String nationalCode,String password,Integer page, Integer size);
    void deleteUser(String externalId) throws UserNotFoundException;
}
