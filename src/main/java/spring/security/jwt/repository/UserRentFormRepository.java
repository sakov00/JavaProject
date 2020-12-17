package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.UserRentForm;
import spring.security.jwt.exception.RepositoryException;

import java.util.Date;
import java.util.List;

public interface UserRentFormRepository extends JpaRepository<UserRentForm , Long> {
    @Modifying
    void deleteById(Long id);
    @Modifying
    void deleteByUserIdAndComputerStuffId(Long user_id, Long computerStuff_id)throws RepositoryException;

    @Modifying
    @Transactional
    void deleteByUserName(String userName)throws RepositoryException;

    UserRentForm getById(Long id)throws RepositoryException;
    List<UserRentForm> getAllByUserId(Long user_id)throws RepositoryException;
    boolean existsByComputerStuffId(Long computerStuff_id)throws RepositoryException;
    boolean existsByUserId(Long user_id)throws RepositoryException;
    List<UserRentForm> getAllByRent(boolean rent)throws RepositoryException;
    List<UserRentForm> getAllByComputerStuffExpirationDateLessThan(Date computerStuff_expirationDate)throws RepositoryException;
    @Modifying
    @Query("update UserRentForm urf set urf.rent =:rent  where urf.id =:id ")
    void setUserRentFormById(@Param("id") Long id, @Param("rent") boolean rent)throws RepositoryException;
}
