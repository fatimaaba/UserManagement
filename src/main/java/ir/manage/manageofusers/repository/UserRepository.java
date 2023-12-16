package ir.manage.manageofusers.repository;

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
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByNationalCode(String nationalCode);

    Optional<User> getUserByExternalId(String externalId);


    @Query("select u from User u where " +
            " ( :name is null or u.name like %:name% ) and  " +
            " ( :lastName is null or u.lastName like %:lastName% ) and  " +
            " ( :email is null or u.email like %:email% ) and " +
            " ( :email is null or u.nationalCode = :nationalCode ) and " +
            " ( :password is null or u.password = :password)")
    Page<User> getByFilter(@Param("name") String name,
                           @Param("lastName") String lastName,
                           @Param("email") String email,
                           @Param("nationalCode") String nationalCode,
                           @Param("password") String password,
                           Pageable pageable);

}
