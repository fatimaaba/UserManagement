package ir.manage.manageofusers.controller;


import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateManagerRequest;
import ir.manage.manageofusers.dto.request.UpdateUserRequest;
import ir.manage.manageofusers.dto.response.ManagerListResponse;
import ir.manage.manageofusers.exceptions.DuplicateEmailException;
import ir.manage.manageofusers.exceptions.ManagerNotFoundException;
import ir.manage.manageofusers.exceptions.UserNotFoundException;
import ir.manage.manageofusers.service.ManagerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/managers")
@AllArgsConstructor
public class ManagerController {

    private ManagerService managerService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated
    public void addUser(@Valid @RequestBody AddManagerRequest request) throws DuplicateEmailException, ManagerNotFoundException {
        managerService.addManager(request);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Validated
    public ManagerListResponse getByCriteria(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                             @RequestParam(required = false, defaultValue = "20") @Min(1) @Max(100) Integer size,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) String email) {
        return managerService.findAllManagers(name, lastName, password, email, page, size);
    }

    @GetMapping("/getmanager/{nationalCode}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void getManager(@PathVariable String email) throws ManagerNotFoundException {
        managerService.getManagerByEmail(email);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client_admin')")
    public void updateUser(UpdateManagerRequest request) throws UserNotFoundException, ManagerNotFoundException {
        managerService.updateManager(request);
    }
}
