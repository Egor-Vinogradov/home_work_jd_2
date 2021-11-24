package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.LoginDto;
import by.it_academy.jd2.food_control.dto.ProfileDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.model.api.ERoleUser;
import by.it_academy.jd2.food_control.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Value("${redirect.front.url}")
    private String redirectFront;

    private final UserService userService;
    private final ProfileService profileService;
    private final WeighingJournalService weighingService;
    private final ActiveJournalService activeService;
    private final JournalFoodService journalFoodService;
    private Authentication auth;

    public UserController(UserService userService,
                          ProfileService profileService,
                          WeighingJournalService weighingService,
                          ActiveJournalService activeService,
                          JournalFoodService journalFoodService) {
        this.userService = userService;
        this.profileService = profileService;
        this.weighingService = weighingService;
        this.activeService = activeService;
        this.journalFoodService = journalFoodService;
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
            if (users != null) {
                return new ResponseEntity<>(users, HttpStatus.OK);
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
        filter.setSize((long) Integer.MAX_VALUE);
        filter.setPage(0L);

        List<User> users = this.userService.findAll(filter);
        if (users != null) {
            return new ResponseEntity<Long>(Long.valueOf(users.size()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> logIn(@RequestBody @Valid LoginDto loginDto,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoginDto newLoginDto = this.userService.authentication(loginDto);

        // только для проверки
        this.profileService.startInit();
        this.weighingService.startInit();
        this.activeService.startInit();
        this.journalFoodService.startInit();

        if (newLoginDto != null) {
            return new ResponseEntity<>(newLoginDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody LoginDto loginDto,
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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ResponseEntity<?> confirmationRegistration(@RequestParam(name = "login", required = false) String login,
                                                      HttpServletResponse resp) {
        try {
            this.userService.confirmationRegistration(login);
            // создаем дефолтный профиль
            Profile profile = new Profile();
            profile.setUser(this.userService.findByLogin(login));
            this.profileService.addItem(profile);
            resp.sendRedirect(redirectFront);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Что-то пошло не так " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/profile")
    public ResponseEntity<?> getProfile(@RequestParam(name = "login", required = false) String login) {
        try {
            Profile profile = this.profileService.findByLogin(login);
            ProfileDto dto = this.profileService.adapterProfile(profile);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile) {
        Profile updateProfile = null;
        try {
            updateProfile = this.profileService.updateItem(profile.getId(), profile, profile.getVersion());
            return new ResponseEntity<>(updateProfile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody LoginDto loginDto) {
        User user = new User();
        user.setStatus(loginDto.getStatus());
        User userUpdate = null;
        try {
            userUpdate = this.userService.updateItem(loginDto.getId(), user, null);
            return new ResponseEntity<>(userUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
