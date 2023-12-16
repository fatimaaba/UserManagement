package ir.manage.manageofusers.serviceimpluserunittest;

import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.entity.User;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.mapper.UserMapper;
import ir.manage.manageofusers.repository.UserRepository;
import ir.manage.manageofusers.serviceimpl.UserServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("user Unit test")
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser() {

        var addUserRequest = new AddUserRequest();
        addUserRequest.setName(RandomStringUtils.random(6));
        addUserRequest.setLastName(RandomStringUtils.random(8));
        addUserRequest.setPassword(RandomStringUtils.random(9));
        addUserRequest.setEmail(TestUtility.randomEmail());
        addUserRequest.setIsActive(true);
        addUserRequest.setAddress(TestUtility.randomEmail());
        addUserRequest.setNationalCode(RandomStringUtils.random(10));
        try {
            userService.addUser(addUserRequest);
        } catch (DuplicateNationalCodeException e) {
            e.getMessage();
        }
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser() {

        var user = new User();
        var updateUser = new UpdateUserRequest();
        updateUser.setName(RandomStringUtils.random(5));
        updateUser.setLastName(RandomStringUtils.random(5));
        updateUser.setPassword(RandomStringUtils.random(8));
        updateUser.setAddress(RandomStringUtils.random(20));
        updateUser.setEmail(TestUtility.randomEmail());
        updateUser.setNationalCode(RandomStringUtils.random(10));
        updateUser.setIsActive(true);
        updateUser.setExternalId(RandomStringUtils.random(10));
        when(userRepository.getUserByExternalId(updateUser.getExternalId())).thenReturn(Optional.of(user));
        try {
            userService.updateUser(updateUser);
        } catch (UserNotFoundException e) {
            e.getMessage();
        }
        verify(userRepository, times(1)).getUserByExternalId(updateUser.getExternalId());
    }

    @Test
    void deleteUser() {

        var user = new User();
        user.setExternalId(UUID.randomUUID().toString());
        user.setName(RandomStringUtils.random(5));
        user.setLastName(RandomStringUtils.random(11));
        user.setIsActive(true);
        user.setNationalCode(RandomStringUtils.random(10));
        user.setPassword(RandomStringUtils.random(8));
        user.setAddress(RandomStringUtils.random(20));
        user.setEmail(TestUtility.randomEmail());

        when(userRepository.getUserByExternalId(user.getExternalId())).thenReturn(Optional.of(user));

        try {
            userService.deleteUser(user.getExternalId());
        } catch (UserNotFoundException e) {
            e.getMessage();
        }

        verify(userRepository, times(1)).getUserByExternalId(user.getExternalId());
    }
}