package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.UserRentForm;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.UserRentFormRepository;
import spring.security.jwt.service.UserRentFormService;

import java.util.Date;
import java.util.List;
@Service
public class UserRentFormServiceImpl implements UserRentFormService {
    @Autowired
    private UserRentFormRepository userRentFormRepository;
    @Override
    public void deleteById(Long id) {
        userRentFormRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndComputerStuffId(Long user_id, Long computerStuff_id) throws ServiceException {
        try {
            userRentFormRepository.deleteByUserIdAndComputerStuffId(user_id, computerStuff_id);
        } catch (RepositoryException e) {

            throw new ServiceException(e);
        }
    }

    @Override
    public UserRentForm create(UserRentForm userRentForm) throws ServiceException{
        return userRentFormRepository.save(userRentForm);
    }

    @Override
    public boolean existsByComputerStuffId(Long computerStuff_id)throws ServiceException {
        try {
            return userRentFormRepository.existsByComputerStuffId(computerStuff_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public UserRentForm getById(Long id)throws ServiceException {
        try {
            return userRentFormRepository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<UserRentForm> getAllByUserId(Long user_id)throws ServiceException {
        try {
            return userRentFormRepository.getAllByUserId(user_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<UserRentForm> getAllByRent(boolean rent)throws ServiceException {
        try {
            return userRentFormRepository.getAllByRent(rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<UserRentForm> getAllByComputerStuffExpirationDateLessThan(Date computerStuff_expirationDate) throws ServiceException{
        try {
            return userRentFormRepository.getAllByComputerStuffExpirationDateLessThan(computerStuff_expirationDate);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void setUserRentFormById(Long id, boolean rent) throws ServiceException{
        try {
            userRentFormRepository.setUserRentFormById(id, rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
