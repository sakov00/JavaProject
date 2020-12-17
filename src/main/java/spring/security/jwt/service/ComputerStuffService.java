package spring.security.jwt.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.ComputerStuff;

import java.util.Date;
import java.util.List;

@Service
public interface ComputerStuffService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    ComputerStuff create(ComputerStuff computerStuff)throws ServiceException, spring.security.jwt.exception.ServiceException;

    boolean existsByName(String name) throws ServiceException, spring.security.jwt.exception.ServiceException;

    List<ComputerStuff> getAll()throws ServiceException, spring.security.jwt.exception.ServiceException;
    @Transactional
    void deleteByName(String name)throws ServiceException, spring.security.jwt.exception.ServiceException;

    ComputerStuff getById(Long id)throws ServiceException, spring.security.jwt.exception.ServiceException;

    ComputerStuff getByName(String name)throws ServiceException, spring.security.jwt.exception.ServiceException;

    @Transactional
    void updateComputerStuffById(
            Long id,
            String name,
            String description,
            int cost)throws ServiceException, spring.security.jwt.exception.ServiceException;
}
