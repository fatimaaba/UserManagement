package ir.manage.manageofusers.controller;

import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('client_admin')")
    @Validated
    public void addUser(@Valid @RequestBody AddUserRequest request) throws DuplicateNationalCodeException {
        userService.addUser(request);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('client_user','client_admin')")
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public UserListResponse getByCriteria(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                          @RequestParam(required = false, defaultValue = "20") @Min(1) @Max(100) Integer size,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) String lastName,
                                          @RequestParam(required = false) String password,
                                          @RequestParam(required = false) String nationalCode,
                                          @RequestParam(required = false) String email) {
        return userService.findAllUsers(name,lastName, password,nationalCode , email, page, size);
    }

    @GetMapping("/getuser/{email}")
    @PreAuthorize("hasAnyAuthority('client_user','client_admin')")
    @ResponseStatus(HttpStatus.OK)
    public void getUser(@PathVariable String email) throws UserNotFoundException {
        userService.findUserByEmail(email);
    }

    @GetMapping("/getuser/{nationalCode}")
    @PreAuthorize("hasAnyAuthority('client_user','client_admin')")
    @ResponseStatus(HttpStatus.OK)
    public void getUserByNationalCode(@PathVariable String nationalCode) throws UserNotFoundException {
        userService.findUserByNationalCode(nationalCode);
    }

    @DeleteMapping("/delete/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client_admin')")
    public void deleteUser(@PathVariable String externalId) throws UserNotFoundException {
       userService.deleteUser(externalId);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client_admin')")
    public void updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        userService.updateUser(updateUserRequest);
    }

}
