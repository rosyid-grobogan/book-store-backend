package com.rosyid.book.store.controller;

import com.rosyid.book.store.entity.EnumRole;
import com.rosyid.book.store.entity.Role;
import com.rosyid.book.store.entity.User;
import com.rosyid.book.store.payload.request.LoginRequest;
import com.rosyid.book.store.payload.request.SignupRequest;
import com.rosyid.book.store.payload.response.JwtResponse;
import com.rosyid.book.store.payload.response.MessageResponse;
import com.rosyid.book.store.repository.RoleRepository;
import com.rosyid.book.store.repository.UserRepository;
import com.rosyid.book.store.service.implement.UserDetailsImpl;
import com.rosyid.book.store.util.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Api
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;




    /**
     *
     * @param loginRequest
     * @return
     *
     * @author Rosyid Grobogan
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), loginRequest.getPassword()) );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map( item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    /**
     * Check usernama or email are already taken
     * Create New User
     * @param signupRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest)
    {
        // Check username or email
        if ( userRepository.existsByUsername(signupRequest.getUsername()) )
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Kesalahan: Username sudah digunakan :("));
        }

        if ( userRepository.existsByEmail( signupRequest.getEmail())  )
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Kesalahan: Email sudah digunakan :("));
        }

        // Create New user
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
                );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
        {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(()-> new RuntimeException("Kesalahan: Role tidak ditemukan :("));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow( ()-> new RuntimeException("Kesalahan: Role tidak ditemukan :("));
                        roles.add(adminRole);

                        break;
                    case "client":
                        Role clientRole = roleRepository.findByName(EnumRole.ROLE_CLIENT)
                                .orElseThrow( ()-> new RuntimeException("Kesalahan: Role tidak ditemukan :("));
                        roles.add(clientRole);

                        break;
                    default:
                        Role usertRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow( ()-> new RuntimeException("Kesalahan: Role tidak ditemukan :("));
                        roles.add(usertRole);
                        break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Selamat Anda berhasil mendaftar :)") );
    }


//    @GetMapping("/")
//    //public List<String> welcome()
//    public User welcome()
//    {
//        //find by email
////        if (userRepository.existsByEmail(loginRequest.getUsername() ))
////        {
////            String emailku =  userRepository.findByUsername();
////        }
//
//
////        List<String> dataku = new ArrayList<>();
////        dataku.add("Data: "+ userRepository.getUsernameByEmail("rosyid@abc.com"));
//
//
//        return userRepository.getUsernameByEmail("rosyid@abc.com");
//    }

}
