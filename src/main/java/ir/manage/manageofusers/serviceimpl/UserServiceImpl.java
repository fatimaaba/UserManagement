package ir.manage.manageofusers.serviceimpl;

import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.entity.User;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.mapper.UserMapper;
import ir.manage.manageofusers.repository.UserRepository;
import ir.manage.manageofusers.service.UserService;
import ir.manage.manageofusers.utils.SetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(AddUserRequest addUserRequest) throws DuplicateNationalCodeException {

        var checkUser = userRepository.getUserByExternalId(addUserRequest.getNationalCode());
        if (checkUser.isPresent()) {
            throw new DuplicateNationalCodeException("User is already exist");
        }

        log.info("Adding user: '{}' to Database", addUserRequest);
        var user = new User();
        user.setEmail(addUserRequest.getEmail());
        user.setAddress(addUserRequest.getAddress());
        user.setName(addUserRequest.getName());
        user.setLastName(addUserRequest.getLastName());
        user.setNationalCode(addUserRequest.getNationalCode());
        user.setIsActive(addUserRequest.getIsActive());
        user.setExternalId(UUID.randomUUID().toString());

        log.info("User with this externalId: '{}' is added  to Database", user.getExternalId());
        userRepository.save(user);

    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException {

        log.info("Getting user from database for checking");
        var user = userRepository.getUserByExternalId(updateUserRequest.getExternalId()).
                orElseThrow(() -> new UserNotFoundException("User not found!!!"));

        user.setExternalId(updateUserRequest.getExternalId());
        user.setEmail(updateUserRequest.getEmail());
        user.setName(updateUserRequest.getName());
        user.setAddress(updateUserRequest.getAddress());
        user.setIsActive(updateUserRequest.getIsActive());
        user.setLastName(updateUserRequest.getLastName());
        user.setNationalCode(user.getNationalCode());

        log.info("User with this name: '{}' has been updated and saved in database", updateUserRequest.getName());
        userRepository.save(user);
    }

    @Override
    public UserListResponse findAllUsers(String name, String lastName, String email, String nationalCode, String password, Integer page, Integer size) {
        var dbResponse = userRepository.getByFilter(name, lastName, email, password, nationalCode, PageRequest.of(page, size));
        log.info("Getting UserList from DataBase: '{}'", dbResponse);
        return (UserListResponse) SetUtil.fillData(new UserListResponse(), dbResponse, userMapper);
    }


    @Override
    public void deleteUser(String externalId) throws UserNotFoundException {

        log.info("Deleting user with this externalId: '{}'", externalId);
        var user = userRepository.getUserByExternalId(externalId).
                orElseThrow(() -> new UserNotFoundException("User not found!!!"));

        log.info("User with this externalId:'{}' has been deleted successfully", externalId);
        userRepository.delete(user);
    }

    @Override
    public User findUserByNationalCode(String nationalCode) throws UserNotFoundException {

        log.info("Get user with this nationalCode: '{}'",nationalCode);
        var user = userRepository.getUserByNationalCode(nationalCode).
                orElseThrow(() -> new UserNotFoundException("User not found!!!"));
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        log.info("Get user with this email: '{}'",email);
        var user = userRepository.getUserByEmail(email).
                orElseThrow(() -> new UserNotFoundException("User not found!!!"));
        return user;
    }
}
