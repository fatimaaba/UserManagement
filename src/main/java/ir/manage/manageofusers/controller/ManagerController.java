package ir.manage.manageofusers.controller;


import ir.manage.manageofusers.dto.request.AddManagerRequest;
import ir.manage.manageofusers.dto.request.AddUserRequest;
import ir.manage.manageofusers.dto.response.ManagerListResponse;
import ir.manage.manageofusers.dto.response.UserListResponse;
import ir.manage.manageofusers.exceptions.DuplicateEmailException;
import ir.manage.manageofusers.exceptions.DuplicateNationalCodeException;
import ir.manage.manageofusers.exceptions.ManagerNotFoundException;
import ir.manage.manageofusers.service.ManagerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/managers")
@AllArgsConstructor
public class ManagerController {

    private ManagerService managerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated
    public void addUser(@Valid @RequestBody AddManagerRequest request) throws DuplicateEmailException, ManagerNotFoundException {
        managerService.addManager(request);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public ManagerListResponse getByCriteria(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                             @RequestParam(required = false, defaultValue = "20") @Min(1) @Max(100) Integer size,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) String email) {
        return managerService.findAllManagers(name,lastName, password, email, page, size);
    }
}
