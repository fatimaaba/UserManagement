package ir.manage.manageofusers.dto.response;

import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class ManagerDto {

    private String name;
    private String lastName;
    private String email;
    private String address;
    private String externalId;
}
