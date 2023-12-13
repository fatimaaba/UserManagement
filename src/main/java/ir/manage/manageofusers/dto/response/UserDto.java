package ir.manage.manageofusers.dto.response;

import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class UserDto {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Boolean isActive;
    private Boolean nationalCode;
    private String externalId;
}
