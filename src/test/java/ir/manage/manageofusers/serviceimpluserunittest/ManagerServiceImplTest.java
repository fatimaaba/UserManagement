package ir.manage.manageofusers.serviceimpluserunittest;

import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.entity.Manager;
import ir.manage.manageofusers.entity.User;
import ir.manage.manageofusers.exceptions.DuplicateEmailException;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.ManagerNotFoundException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.mapper.ManagerMapper;
import ir.manage.manageofusers.mapper.UserMapper;
import ir.manage.manageofusers.repository.ManagerRepository;
import ir.manage.manageofusers.repository.UserRepository;
import ir.manage.manageofusers.serviceimpl.ManagerServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("user Unit test")
class ManagerServiceImplTest {


    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private ManagerMapper managerMapper;

    @InjectMocks
    private ManagerServiceImpl managerService;

    @Test
    void addManager() {

        var addManagerRequest = new AddManagerRequest();
        addManagerRequest.setName(RandomStringUtils.random(6));
        addManagerRequest.setLastName(RandomStringUtils.random(8));
        addManagerRequest.setPassword(RandomStringUtils.random(9));
        addManagerRequest.setEmail(TestUtility.randomEmail());
        addManagerRequest.setAddress(TestUtility.randomEmail());
        try {
            managerService.addManager(addManagerRequest);
        } catch (DuplicateEmailException e) {
            e.getMessage();
        }
        verify(managerRepository, times(1)).save(any(Manager.class));
    }

    @Test
    void updateManager() {

        var manager = new Manager();
        var updateManagerRequest = new UpdateManagerRequest();
        updateManagerRequest.setName(RandomStringUtils.random(5));
        updateManagerRequest.setLastName(RandomStringUtils.random(5));
        updateManagerRequest.setPassword(RandomStringUtils.random(8));
        updateManagerRequest.setAddress(RandomStringUtils.random(20));
        updateManagerRequest.setEmail(TestUtility.randomEmail());
        updateManagerRequest.setExternalId(RandomStringUtils.random(10));
        when(managerRepository.getManagerByExternalId(updateManagerRequest.getExternalId())).thenReturn(Optional.of(manager));
        try {
            managerService.updateManager(updateManagerRequest);
        } catch (ManagerNotFoundException e) {
            throw new RuntimeException(e);
        }
        verify(managerRepository, times(1)).getManagerByExternalId(updateManagerRequest.getExternalId());
    }

    @Test
    void deleteManager() {

        var manager = new Manager();
        manager.setExternalId(UUID.randomUUID().toString());
        manager.setName(RandomStringUtils.random(5));
        manager.setLastName(RandomStringUtils.random(11));
        manager.setPassword(RandomStringUtils.random(8));
        manager.setAddress(RandomStringUtils.random(20));
        manager.setEmail(TestUtility.randomEmail());

        when(managerRepository.getManagerByExternalId(manager.getExternalId())).thenReturn(Optional.of(manager));

        try {
            managerService.deleteManager(manager.getExternalId());
        } catch (ManagerNotFoundException e) {
            e.getMessage();
        }

        verify(managerRepository, times(1)).getManagerByExternalId(manager.getExternalId());
    }
}
