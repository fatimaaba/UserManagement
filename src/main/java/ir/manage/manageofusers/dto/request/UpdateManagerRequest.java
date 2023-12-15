package ir.manage.manageofusers.dto.request;

import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class UpdateManagerRequest {


    private String name;
    private String lastName;
    private String email;
    private String address;
    private String externalId;
    private String password;
}
