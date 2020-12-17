package spring.security.jwt.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.UserRentForm;

import java.util.Date;
import java.util.List;

@Service
public interface UserRentFormService {
    @Transactional
    void deleteById(Long id)throws ServiceException;
    @Transactional
    void deleteByUserIdAndComputerStuffId(Long user_id, Long computerStuff_id)throws ServiceException, spring.security.jwt.exception.ServiceException;

    UserRentForm create(UserRentForm userRentForm)throws ServiceException, spring.security.jwt.exception.ServiceException;
    boolean existsByComputerStuffId(Long computerStuff_id)throws ServiceException, spring.security.jwt.exception.ServiceException;
    UserRentForm getById(Long id)throws ServiceException, spring.security.jwt.exception.ServiceException;
    List<UserRentForm> getAllByUserId(Long user_id)throws ServiceException, spring.security.jwt.exception.ServiceException;
    List<UserRentForm> getAllByRent(boolean rent)throws ServiceException, spring.security.jwt.exception.ServiceException;

    List<UserRentForm> getAllByComputerStuffExpirationDateLessThan(Date computerStuff_expirationDate)throws ServiceException, spring.security.jwt.exception.ServiceException;
    @Transactional
    void setUserRentFormById(Long id, boolean rent)throws ServiceException, spring.security.jwt.exception.ServiceException;
}
