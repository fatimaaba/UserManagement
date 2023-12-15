package ir.manage.manageofusers.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class AddUserRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String address;

    @NotEmpty
    private Boolean isActive;

    @NotEmpty
    private String nationalCode;

}
