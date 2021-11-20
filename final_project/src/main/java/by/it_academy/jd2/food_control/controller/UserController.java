package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.LoginDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.model.api.ERoleUser;
import by.it_academy.jd2.food_control.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Value("${redirect.front.url}")
    private String redirectFront;

    private final UserService userService;
    private Authentication auth;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "page", required = false) long page,
                                                  @RequestParam(value = "size", required = false) long size,
                                                  @RequestParam(value = "name", required = false) String name) {
        SearchFilter filter = new SearchFilter();
        filter.setName(name);
        filter.setSize(size);
        filter.setPage(page);

        this.auth = SecurityContextHolder.getContext().getAuthentication();
        User userRole = this.userService.findByLogin(auth.getName());

        if (userRole.getRole() == ERoleUser.ROLE_ADMIN) {
            List<User> users = this.userService.findAll(filter);
            // убираю пароли
            List<User> usersNoPassword = new ArrayList<>();
            for (User user : users) {
                user.setPassword("");
                usersNoPassword.add(user);
            }
            if (users != null) {
                return new ResponseEntity<>(usersNoPassword, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // не по тз. для удобства фронта
    @RequestMapping(value = "/allpage", method = RequestMethod.GET)
    public ResponseEntity<Long> getAllUsersPage () {

        SearchFilter filter = new SearchFilter();
        filter.setName("");
        filter.setSize(Integer.MAX_VALUE);
        filter.setPage(0);

        List<User> users = this.userService.findAll(filter);
        if (users != null) {
            return new ResponseEntity<Long>(Long.valueOf(users.size()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> logIn(@RequestBody @Valid LoginDto loginDto,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoginDto newLoginDto = this.userService.authentication(loginDto);
        if (newLoginDto != null) {
            return new ResponseEntity<>(newLoginDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody @Valid LoginDto loginDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            this.userService.findByLogin(loginDto.getLogin());
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (Exception e) {
            LoginDto newLoginDto = this.userService.registration(loginDto);
            if (newLoginDto != null) {
                return new ResponseEntity<>(newLoginDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> confirmationRegistration(@RequestParam(name = "login", required = false) String login,
                                                      HttpServletResponse resp) {
        System.out.println(login);
        try {
            this.userService.confirmationRegistration(login);
            resp.sendRedirect(redirectFront);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Что-то пошло не так " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
