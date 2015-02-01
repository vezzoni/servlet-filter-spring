package vezzoni.servlet.filter.spring.services.impl;

import org.springframework.stereotype.Service;
import vezzoni.servlet.filter.spring.services.GreetingsService;

@Service(value=GreetingsService.COMP_NAME)
public class GreetingsServiceImpl implements GreetingsService {

    @Override
    public String sayHello() {
        return "hi there!";
    }

}
