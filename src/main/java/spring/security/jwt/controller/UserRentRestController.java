package spring.security.jwt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.UserRentForm;
import spring.security.jwt.controller.dto.DateRequest;
import spring.security.jwt.controller.dto.IdRentRequest;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.controller.dto.UserRentRequestNoId;
import spring.security.jwt.exception.ControllerException;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.UserRentFormRepository;
import spring.security.jwt.service.impl.ComputerStuffServiceImpl;
import spring.security.jwt.service.impl.UserRentFormServiceImpl;
import spring.security.jwt.validator.UserRentValidator;

import java.util.List;

@RestController
public class UserRentRestController {
    @Autowired
    private ComputerStuffServiceImpl computerStuffService;
    @Autowired
    private UserRentFormServiceImpl userRentFormService;
    @Autowired
    private UserRentFormRepository rentFormRepository;
    @Autowired
    private UserRentValidator userRentValidator;

    private static final Logger logger = Logger.getLogger(ComputerStuffRestController.class);

    @DeleteMapping("/admin/deleteByUserIdAndComputerStuffId")
    public ResponseEntity<?> deleteByUserIdAndComputerStuffId() {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/admin/setUserRentFormById")
    public ResponseEntity<?> setUserRentFormById(@RequestBody IdRentRequest idRentRequest)throws ControllerException {
        try {
            userRentFormService.setUserRentFormById(idRentRequest.getId(),idRentRequest.isRent());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : setUserRentFormById");
            throw new ControllerException(e);
        }

    }

    @GetMapping("/user/getAllByUserId/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "id")Long id) throws ControllerException{

        try {
            return new ResponseEntity<>(userRentFormService.getAllByUserId(id),HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : getAllByUserId");
            throw new ControllerException(e);
        }

    }

    @GetMapping("/doctor/getAllByRent/{data}")
    public ResponseEntity<?> getAllByRent(@PathVariable(name = "data")boolean data) throws ControllerException{

        try {
            return new ResponseEntity<>(userRentFormService.getAllByRent(data),HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : getAllByRent");
            throw new ControllerException(e);
        }

    }

    @PostMapping("/admin/isUserRentExistByComputerStuffId")
    public ResponseEntity<?> isUserRentExistByComputerStuffId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(userRentFormService.existsByComputerStuffId(idRequest.getId())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            logger.error(" error : isUserRentExistByComputerStuffId");
            throw new ControllerException(e);
        }

    }

    @PostMapping("/user/isUserRentExistByUserId")
    public ResponseEntity<?> isUserRentExistByUserId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(!rentFormRepository.existsByUserId(idRequest.getId())){
                return  new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (RepositoryException e) {
            logger.error(" error : isUserRentExistByUserId");
            throw new ControllerException(e);
        }
    }

    @PostMapping("/user/createUserRent")
    public ResponseEntity<?> createUserRent(@RequestBody @Validated UserRentRequestNoId userRentRequestNoId , BindingResult bindingResult)throws ControllerException {
        if(!bindingResult.hasErrors()) {
            UserRentForm userRentForm = new UserRentForm(
                    userRentRequestNoId.getUser(),
                    userRentRequestNoId.getName(),
                    userRentRequestNoId.getSurname(),
                    userRentRequestNoId.getComputerStuff()
            );
            try {
                //userRentValidator.validate(userRentForm,bindingResult);
                //if(!bindingResult.hasErrors()) throw new ControllerException("not correct data");
                userRentFormService.create(userRentForm);
                return new ResponseEntity<>(userRentForm, HttpStatus.OK);
            } catch (ServiceException e) {
                logger.error(" error : createUserRent");
                throw new ControllerException(e);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/admin/getAllByComputerStuffExpirationDateLessThan")
    public ResponseEntity<?> getAllByComputerStuffExpirationDateLessThan(@RequestBody DateRequest dateRequest) throws ControllerException{
        try {
            return new ResponseEntity<>(userRentFormService.getAllByComputerStuffExpirationDateLessThan(dateRequest.getDate()),HttpStatus.FOUND);
        } catch (ServiceException e) {
            logger.error(" error : getAllByComputerStuffExpirationDateLessThan");
            throw new ControllerException(e);
        }

    }
}
