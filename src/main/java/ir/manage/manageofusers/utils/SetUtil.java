package ir.manage.manageofusers.utils;

import ir.manage.manageofusers.dto.response.PaginationResponse;
import ir.manage.manageofusers.mapper.Convert;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

@UtilityClass
public class SetUtil {

    public static <R, E> PaginationResponse<R> fillData(PaginationResponse<R> paginationResponse, Page<E> page, Convert<E, R> convert) {
        var data = page.stream().map(convert::convert).toList();
        paginationResponse.setData(data);
        paginationResponse.setSize(page.getSize());
        paginationResponse.setTotal(page.getTotalElements());
        paginationResponse.setPage(page.getNumber());
        return paginationResponse;
    }

}
