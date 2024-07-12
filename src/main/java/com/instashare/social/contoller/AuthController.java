package com.instashare.social.contoller;

import com.instashare.social.config.JwtProvider;
import com.instashare.social.model.User;
import com.instashare.social.repository.UserRepository;
import com.instashare.social.request.LoginRequest;
import com.instashare.social.response.AuthResponse;
import com.instashare.social.service.CustomerUserDetailsService;
import com.instashare.social.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetails;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody @NotNull User user) throws Exception {
        User isExistingUser = userRepository.findByEmail(user.getEmail());
        if(isExistingUser!=null){
            throw new Exception("User with this email Id already exists");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(),saveUser.getEmail());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Register Success");
        return res;
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
