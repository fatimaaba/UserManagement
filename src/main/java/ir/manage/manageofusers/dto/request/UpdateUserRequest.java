package ir.manage.manageofusers.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class UpdateUserRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Boolean isActive;
    private String nationalCode;
    private String externalId;
}
