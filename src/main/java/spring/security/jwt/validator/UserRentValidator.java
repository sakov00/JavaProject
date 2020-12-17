package spring.security.jwt.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.security.jwt.bean.ComputerStuff;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.UserRentForm;

@Component
public class UserRentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRentForm computerStuff =(UserRentForm)o;
        if(computerStuff.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}
