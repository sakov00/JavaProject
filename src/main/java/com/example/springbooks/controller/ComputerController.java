package com.example.springbooks.controller;

import com.example.springbooks.forms.ComputerForm;
import com.example.springbooks.model.Computer;
import com.example.springbooks.model.RentUsers;
import com.example.springbooks.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class ComputerController {
    private static List<User> users = new ArrayList<User>();
    static {
        users.add(new User(users.size()+1,"admin","admin"));
        users.add(new User(users.size()+1,"helich","1234"));
        users.add(new User(users.size()+1,"nagib","1234"));
    }
    private static List<Computer> computers = new ArrayList<Computer>();
    static {
        computers.add(new Computer(computers.size()+1,"globoc","windows 10"));
        computers.add(new Computer(computers.size()+1,"idelus","linux"));
        computers.add(new Computer(computers.size()+1,"pikapius","dos"));
    }
    private static List<Computer> findcomputers = new ArrayList<Computer>();
    private static List<Computer> Choosecomputersforuser = new ArrayList<Computer>();

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;
    @Value("${error.message.autorisation}")
    private String errorMessageAutorisation;
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        model.addAttribute("user", user);
        modelAndView.setViewName("index");
        log.info("/index was called");
        return modelAndView;
    }
    @GetMapping(value = {"/mainUser"})
    public ModelAndView mainUser(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainUser");
        model.addAttribute("users", users);
        model.addAttribute("Choosecomputersforuser",Choosecomputersforuser);
        Computer computer =new Computer();
        model.addAttribute("computer", computer);
        log.info("/userList was called");
        return modelAndView;
    }

    @GetMapping(value = {"/registration"})
    public ModelAndView showAddComputerPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        User user = new User();
        model.addAttribute("user", user);
        log.info("/registration was called");
        return modelAndView;
    }
    @PostMapping(value = {"/registration"})
    public ModelAndView saveComputer(Model model, //
                                     @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainUser");
        String Name = user.getLogin();
        String Password = user.getPassword();
        if (Name != null && Name.length() > 0 && Password != null && Password.length() > 0) {
            User newuser = new User(users.size()+1,Name,Password);
            users.add(newuser);
            model.addAttribute("computers",computers);
            log.info("/registration" + newuser.toString());
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = {"/autorization"})
    public ModelAndView autorization(Model model, //
                                     @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        String Login = user.getLogin();
        String Password = user.getPassword();
        for (User user1 : users) {
            if (users.get(0).getId()==user1.getId() && Login.equals(user1.getLogin()) && Password.equals(user1.getPassword())) {
                model.addAttribute("users",users);
                model.addAttribute("computers",computers);
                modelAndView.setViewName("mainAdmin");
                log.info("/autorization" + user1.toString());
                break;
            }
            if (Login.equals(user1.getLogin()) && Password.equals(user1.getPassword())) {
                model.addAttribute("computers",computers);
                Computer computer =new Computer();
                model.addAttribute("computer", computer);
                modelAndView.setViewName("mainUser");
                log.info("/autorization" + user1.toString());
                break;
            }
            if (users.get(users.size()-1).getId()==user1.getId()) {
                modelAndView.setViewName("index");
                model.addAttribute("errorMessageAutorisation", errorMessageAutorisation);
                log.info("/errorMessageAutorisation");
                break;
            }
        }
        return modelAndView;
    }
    @GetMapping(value = {"/addComputer"})
    public ModelAndView ShowaddComputer(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addComputer");
        Computer comp=new Computer();
        model.addAttribute("computer", comp);
        log.info("/addComputer was called");
        return modelAndView;
    }
    @PostMapping(value = {"/addComputer"})
    public ModelAndView addComputer(Model model, //
                                     @ModelAttribute("computer") Computer computer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainAdmin");
        model.addAttribute("users",users);
        model.addAttribute("computers",computers);
        String Name = computer.getName();
        String OS = computer.getOS();
        if (Name != null && Name.length() > 0 && OS != null && OS.length() > 0) {
            Computer newcomp = new Computer(users.size()+1,Name,OS);
            computers.add(newcomp);
            log.info("/addComputer" + newcomp.toString());
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @GetMapping(value = {"/updateComputer"})
    public ModelAndView showUpdateComputerPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("updatedeleteComputer");
        Computer comp = new Computer();
        model.addAttribute("computer", comp);
        //log.info("/updateDocument was called");
        return modelAndView;
    }
    @PostMapping(value = {"/updateComputer"})
    public ModelAndView updateComputer(Model model, //
                                       @ModelAttribute("computer") Computer computer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainAdmin");
        Computer updateComp = null;
        int id = computer.getId();
        for (Computer comp:computers) {
            if(comp.getId() == id){
                updateComp = comp;
                break;
            }
        }
        if(updateComp == null){
            model.addAttribute("users",users);
            model.addAttribute("computers",computers);
            return modelAndView;
        }
        if(!computer.getName().isEmpty()){
            updateComp.setName(computer.getName());
        }
        if(!computer.getOS().isEmpty()){
            updateComp.setOS(computer.getOS());
        }
        model.addAttribute("computers",computers);
        model.addAttribute("users",users);
        //log.info("/updateDocument"+updateDoc.toString());
        return modelAndView;
    }

    @PostMapping(value = {"/deleteComputer"})
    public ModelAndView deleteComputer(Model model, //
                                       @ModelAttribute("computer") Computer computer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainAdmin");
        int id = computer.getId();
        for (Computer comp:computers) {
            if(comp.getId() == id){
                computers.remove(comp);
                log.info("/deleteComputer" + comp.toString());
                break;
            }
        }
        model.addAttribute("users",users);
        model.addAttribute("computers",computers);
        return modelAndView;
    }
    @PostMapping(value = {"/findComputerForField"})
    public ModelAndView FindComputerForField(Model model, //
                                       @ModelAttribute("computer") Computer computer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainUser");
        String Name = computer.getName();
        String OS = computer.getOS();
        findcomputers=new ArrayList<Computer>();
        for (Computer comp:computers) {
            if(Name.equals(comp.getName()) || OS.equals(comp.getOS())){
                findcomputers.add(comp);
                log.info("/findComputer" + comp.toString());
            }
        }
        model.addAttribute("users",users);
        model.addAttribute("computers",computers);
        model.addAttribute("findcomputers",findcomputers);
        model.addAttribute("Choosecomputersforuser",Choosecomputersforuser);
        return modelAndView;
    }
    @PostMapping(value = {"/chooseComputer"})
    public ModelAndView chooseComputer(Model model, //
                                             @ModelAttribute("computer") Computer computer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rentUser");
        int Id = computer.getId();

        for (Computer comp:computers) {
            if(Id==comp.getId()){
                Choosecomputersforuser.add(comp);
                computers.remove(comp);
                log.info("/ChooseComputer" + comp.toString());
            }
        }
        RentUsers rentuser = new RentUsers();
        model.addAttribute("rentuser", rentuser);
        model.addAttribute("users",users);
        model.addAttribute("computers",computers);
        model.addAttribute("findcomputers",findcomputers);
        model.addAttribute("Choosecomputersforuser",Choosecomputersforuser);
        return modelAndView;
    }
    @GetMapping(value = {"/ShowRentUser"})
    public ModelAndView ShowRentUser(Model model) {
        ModelAndView modelAndView = new ModelAndView("rentUser");
        RentUsers rentuser = new RentUsers();
        model.addAttribute("rentuser", rentuser);
        log.info("/rentUser was called");
        return modelAndView;
    }
    @PostMapping(value = {"/completeform"})
    public ModelAndView completeform(Model model, //
                                       @ModelAttribute("rentuser") RentUsers rentuser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainUser");
/*        int Id = computer.getId();

        for (Computer comp:computers) {
            if(Id==comp.getId()){
                Choosecomputersforuser.add(comp);
                computers.remove(comp);
                log.info("/ChooseComputer" + comp.toString());
            }
        }*/
        model.addAttribute("users",users);
        model.addAttribute("computers",computers);
        model.addAttribute("findcomputers",findcomputers);
        model.addAttribute("Choosecomputersforuser",Choosecomputersforuser);
        return modelAndView;
    }
}
