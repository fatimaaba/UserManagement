package ir.manage.manageofusers.service;

import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateManagerRequest;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
public interface ManagerService {


    void addManager(AddManagerRequest addManagerRequest);

    void updateManager(UpdateManagerRequest updateManagerRequest);

    void deleteManager(String externalId);
}
