package spring.security.jwt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.ComputerStuff;
import spring.security.jwt.controller.dto.ComputerStuffRequestNoIdRent;
import spring.security.jwt.controller.dto.ComputerStuffRequestNoRent;
import spring.security.jwt.controller.dto.NameRequest;
import spring.security.jwt.exception.ControllerException;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.UserRentFormRepository;
import spring.security.jwt.service.impl.ComputerStuffServiceImpl;
import spring.security.jwt.service.impl.UserRentFormServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ComputerStuffRestController {
    @Autowired
    private ComputerStuffServiceImpl computerStuffService;
    @Autowired
    private UserRentFormServiceImpl userRentFormService;
    @Autowired
    private UserRentFormRepository userRentFormRepository;
    private static final Logger logger = Logger.getLogger(ComputerStuffRestController.class);

    @DeleteMapping("/admin/deleteComputerStuffByNameA")
    public ResponseEntity<?> deleteComputerStuffByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            computerStuffService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : deleteComputerStuffByNameA");
           throw new ControllerException(e);
        }
    }

    @DeleteMapping("/user/deleteComputerStuffByNameU")
    public ResponseEntity<?> deleteComputerStuffByNameU(@RequestBody NameRequest nameRequest)throws ControllerException {

        try {
            userRentFormRepository.deleteByUserName(nameRequest.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryException e) {
            logger.error(" error : deleteComputerStuffByNameU");
            throw new ControllerException(e);

        }


    }

    @PutMapping("/admin/updateComputerStuff")
    public ResponseEntity<?> updateComputerStuff(@RequestBody ComputerStuffRequestNoRent computerStuffRequestNoRent)throws ControllerException {
        try {
            computerStuffService.updateComputerStuffById(
                    computerStuffRequestNoRent.getId(),
                    computerStuffRequestNoRent.getName(),
                    computerStuffRequestNoRent.getDescription(),
                    computerStuffRequestNoRent.getCost()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : updateComputerStuff");
            throw new ControllerException(e);

        }
    }


    @GetMapping("/user/userGetComputerStuffByName/{name}")
    public ResponseEntity<?> userGetComputerStuffByName(@PathVariable(name = "name")String name)throws ControllerException {
        ComputerStuff stuff = null;
        try {
            stuff = computerStuffService.getByName(name);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : userGetComputerStuffByName");
            throw new ControllerException(e);

        }
    }

    @GetMapping("admin/adminGetComputerStuffByName/{name}")
    public ResponseEntity<?> adminGetComputerStuffByName(@PathVariable(name = "name")String name) throws ParseException, ControllerException {
        ComputerStuff stuff = null;
        try {
            stuff = computerStuffService.getByName(name);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(stuff.getExpirationDate());
            System.out.println(date);
            stuff.setExpirationDate(simpleDateFormat.parse(date));
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error :adminGetComputerStuffByName ");
            throw new ControllerException(e);

        }

    }

    @PostMapping("/admin/isComputerStuffExistByName")
    public ResponseEntity<?> isComputerStuffExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!computerStuffService.existsByName(nameRequest.getName())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            logger.error(" error : isComputerStuffExistByName");
            throw new ControllerException(e);

        }

    }

    public ResponseEntity<?> isComputerStuffExistByName() {

            return new ResponseEntity<>(HttpStatus.FOUND);

    }

    @PostMapping("/admin/createComputerStuff")
    public ResponseEntity<?> createComputerStuff(@RequestBody ComputerStuffRequestNoIdRent computerStuffRequestNoIdRent) throws ControllerException{
        ComputerStuff stuff = new ComputerStuff(
                computerStuffRequestNoIdRent.getName(),
                computerStuffRequestNoIdRent.getDescription(),
                computerStuffRequestNoIdRent.getCost(),
                computerStuffRequestNoIdRent.getExpirationDate()
        );
        try {
            computerStuffService.create(stuff);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : createComputerStuff");

            throw new ControllerException(e);

        }
    }

    @GetMapping("/admin/getAllCompsForAdmin")
    public ResponseEntity<?> getAllCompsForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(computerStuffService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : getAllCompsForAdmin");

            throw new ControllerException(e);

        }
    }

    @GetMapping("/user/getAllCompsForUser")
    public ResponseEntity<?> getAllCompsForUser()throws ControllerException {
        try {
            return new ResponseEntity<>(computerStuffService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(" error : getAllCompsForUser");

            throw new ControllerException(e);

        }
    }
}
