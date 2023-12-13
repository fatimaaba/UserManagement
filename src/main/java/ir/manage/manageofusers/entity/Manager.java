package ir.manage.manageofusers.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */

@Data
@Entity
@Table(name = "manager_tbl")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "external_id", nullable = false)
    private String externalId;

}
