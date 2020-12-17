package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.security.jwt.bean.ComputerStuff;
import spring.security.jwt.exception.RepositoryException;

public interface ComputerStuffRepository extends JpaRepository<ComputerStuff, Long> {
    @Modifying
    void deleteById(Long id);
    @Modifying
    void deleteByName(String name)throws RepositoryException;

    ComputerStuff getById(Long id)throws RepositoryException;

    ComputerStuff getByName(String name)throws RepositoryException;

    boolean existsByName(String name)throws RepositoryException;


    @Modifying
    @Query("update ComputerStuff с set с.name =:name, с.description=:description, с.cost =:cost where с.id =:id ")
    void updateComputerStuffById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("cost") int cost
    )throws RepositoryException;
}
