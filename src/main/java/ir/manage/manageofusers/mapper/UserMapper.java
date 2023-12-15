package ir.manage.manageofusers.mapper;

import ir.manage.manageofusers.dto.response.UserDto;
import ir.manage.manageofusers.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Convert<User, UserDto> {


    UserDto convert(User user);
}
