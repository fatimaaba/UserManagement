package ir.manage.manageofusers.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class UserListResponse extends PaginationResponse<UserDto> {

    private List<UserDto> userDtos;

    @Override
    public void setData(List<UserDto> userDtos) {

    }
}
