package ir.manage.manageofusers.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class PaginationResponse<T> {

    protected Integer page;

    protected Integer size;
    protected Long total;

    public abstract void setData(List<T> tList);
}
