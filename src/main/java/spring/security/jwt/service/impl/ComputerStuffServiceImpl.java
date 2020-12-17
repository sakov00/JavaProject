package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.ComputerStuff;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.ComputerStuffRepository;
import spring.security.jwt.service.ComputerStuffService;

import java.util.List;

@Service
public class ComputerStuffServiceImpl implements ComputerStuffService {

    @Autowired
    private ComputerStuffRepository computerStuffRepository;
    @Override
    public void deleteById(Long id) {
        computerStuffRepository.deleteById(id);
    }

    @Override
    public ComputerStuff create(ComputerStuff computerStuff)throws ServiceException {
        return computerStuffRepository.save(computerStuff);
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        try {
            return computerStuffRepository.existsByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ComputerStuff> getAll()throws ServiceException {
        return computerStuffRepository.findAll();
    }

    @Override
    public void deleteByName(String name)throws ServiceException {
        try {
            computerStuffRepository.deleteByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public ComputerStuff getById(Long id)throws ServiceException {
        try {
            return computerStuffRepository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public ComputerStuff getByName(String name)throws ServiceException {
        try {
            return computerStuffRepository.getByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void updateComputerStuffById(Long id, String name, String description, int cost ) throws ServiceException{
        try {
            computerStuffRepository.updateComputerStuffById(id, name, description, cost);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
