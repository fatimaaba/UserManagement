package ir.manage.manageofusers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @GetMapping()
    public String hello(){
        return "Hello1";
    }

    @GetMapping("/hello-2")
    public String hello2(){
        return "Hello2";
    }
}
