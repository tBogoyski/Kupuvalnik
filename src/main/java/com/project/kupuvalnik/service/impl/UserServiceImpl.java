package com.project.kupuvalnik.service.impl;

import com.project.kupuvalnik.models.entity.UserEntity;
import com.project.kupuvalnik.models.entity.UserRoleEntity;
import com.project.kupuvalnik.models.entity.enums.UserRoleEnum;
import com.project.kupuvalnik.models.service.UserRegisterServiceModel;
import com.project.kupuvalnik.repository.UserRepository;
import com.project.kupuvalnik.repository.UserRoleRepository;
import com.project.kupuvalnik.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    //20 minutes = 20 * 60000
    //3 sec = 3000
    private static final long MAX_INACTIVE_SESSION_TIME = 20 * 60000;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final KupuvalnikUserServiceImpl kupuvalnikUserService;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, KupuvalnikUserServiceImpl kupuvalnikUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.kupuvalnikUserService = kupuvalnikUserService;
    }

    @Override
    public boolean isUsernameFree(String userName) {

        return userRepository.findByUsernameIgnoreCase(userName).isEmpty();
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.setUsername(userRegisterServiceModel.getUsername());
        newUser.setFirstName(userRegisterServiceModel.getUsername());
        newUser.setLastName(userRegisterServiceModel.getLastName());
        newUser.setActive(true);
        newUser.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        newUser.setRoles(Set.of(userRole));
        newUser.setPhone(userRegisterServiceModel.getPhone());

        newUser = userRepository.save(newUser);

        //Spring representation of a user
        UserDetails principal = kupuvalnikUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, newUser.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();


    }

    @Override
    public void logOutInactiveUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {

            session = request.getSession();

            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {

                SecurityContextHolder.clearContext();

                request.logout();

                response.sendRedirect("/users/login");
            }
        }

    }


    private void initializeUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole((UserRoleEnum.USER));

            UserEntity admin = new UserEntity();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345678"));
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setActive(true);
            admin.setPhone("0123456789");

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity dummyUser = new UserEntity();

            dummyUser.setUsername("dummy");
            dummyUser.setPassword(passwordEncoder.encode("12345678"));
            dummyUser.setFirstName("Dummy");
            dummyUser.setLastName("Dummyev");
            dummyUser.setActive(true);
            dummyUser.setPhone("0123456789");

            dummyUser.setRoles(Set.of(userRole));
            userRepository.save(dummyUser);
        }
    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
