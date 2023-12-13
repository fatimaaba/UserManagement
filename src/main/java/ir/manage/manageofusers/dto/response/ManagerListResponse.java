package ir.manage.manageofusers.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
public class ManagerListResponse extends PaginationResponse<ManagerDto> {


    private List<ManagerDto> managerDtos;

    @Override
    public void setData(List<ManagerDto> managerDtos) {

    }
}
