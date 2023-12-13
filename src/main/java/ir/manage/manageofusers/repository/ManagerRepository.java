package ir.manage.manageofusers.repository;

import ir.manage.manageofusers.entity.Manager;
import ir.manage.manageofusers.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author F_Babaei
 * @Date 12/13/2023
 */


@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {


    Optional<Manager> getUserByEmail(String email);

    Optional<Manager> getUserByExternalId(String externalId);


    @Query("select m from Manager m where " +
            " ( :name is null or m.name like %:name% ) and  " +
            " ( :lastName is null or m.lastName like %:lastName% ) and  " +
            " ( :email is null or m.email like %:email% ) and " +
            " ( :password is null or m.password = :password)")
    Page<User> getByFilter(@Param("name") String name,
                           @Param("lastName") String lastName,
                           @Param("email") String email,
                           @Param("password") String password,
                           Pageable pageable);
}
