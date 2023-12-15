package ir.manage.manageofusers.serviceimpl;

import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateManagerRequest;
import ir.manage.manageofusers.dto.response.ManagerListResponse;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.entity.Manager;
import ir.manage.manageofusers.entity.User;
import ir.manage.manageofusers.exceptions.DuplicateEmailException;
import ir.manage.manageofusers.exceptions.ManagerNotFoundException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.mapper.ManagerMapper;
import ir.manage.manageofusers.mapper.UserMapper;
import ir.manage.manageofusers.repository.ManagerRepository;
import ir.manage.manageofusers.repository.UserRepository;
import ir.manage.manageofusers.service.ManagerService;
import ir.manage.manageofusers.utils.SetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
@Slf4j
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public void addManager(AddManagerRequest addManagerRequest) throws DuplicateEmailException {

        var checkManager = managerRepository.getManagerByEmail(addManagerRequest.getEmail());
        if (checkManager.isPresent()) {
            throw new DuplicateEmailException();
        }

        log.info("Adding manager: '{}' to Database", addManagerRequest);
        var manager = new Manager();
        manager.setAddress(addManagerRequest.getAddress());
        manager.setName(addManagerRequest.getName());
        manager.setEmail(addManagerRequest.getEmail());
        manager.setPassword(addManagerRequest.getPassword());
        manager.setLastName(addManagerRequest.getLastName());
        manager.setExternalId(UUID.randomUUID().toString());

        log.info("Save this manager with this email: '{}' to Database", addManagerRequest.getEmail());

        managerRepository.save(manager);

    }

    @Override
    public void updateManager(UpdateManagerRequest updateManagerRequest) throws ManagerNotFoundException {

        log.info("Getting manager from database for checking");
        var manager = managerRepository.getManagerByExternalId(updateManagerRequest.getExternalId()).
                orElseThrow(() -> new ManagerNotFoundException("Manager not found!!!"));

        manager.setExternalId(updateManagerRequest.getExternalId());
        manager.setAddress(updateManagerRequest.getAddress());
        manager.setName(updateManagerRequest.getName());
        manager.setEmail(updateManagerRequest.getEmail());
        manager.setPassword(updateManagerRequest.getPassword());

        log.info("Manager with this name: '{}' updated", updateManagerRequest.getName());
        managerRepository.save(manager);
    }

    @Override
    public ManagerListResponse findAllManagers(String name, String lastName, String email, String password, Integer page, Integer size) {
        var dbResponse = managerRepository.getByFilter(name, lastName, email, password, PageRequest.of(page, size));
        log.info("Getting UserList from DataBase: '{}'", dbResponse);
        return (ManagerListResponse) SetUtil.fillData(new ManagerListResponse(), dbResponse, managerMapper);
    }

    @Override
    public void deleteManager(String externalId) throws ManagerNotFoundException {

        log.info("Deleting user with this externalId: '{}'", externalId);
        var manager = managerRepository.getManagerByExternalId(externalId).
                orElseThrow(() -> new ManagerNotFoundException("Manager not found!!!"));

        log.info("Manager with this externalId:'{}' has been deleted successfully", externalId);
        managerRepository.delete(manager);

    }
}
