package hr.autoskola.utilities.jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import hr.autoskola.utilities.jwt.auth.JwtUtils;
import hr.autoskola.utilities.jwt.model.User;
import hr.autoskola.utilities.jwt.model.request.LoginRequest;
import hr.autoskola.utilities.jwt.model.request.SignupRequest;
import hr.autoskola.utilities.jwt.model.response.JwtResponse;
import hr.autoskola.utilities.jwt.model.response.MessageResponse;
import hr.autoskola.utilities.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		System.out.println(encoder.encode(loginRequest.getPassword()));

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, 
							   userDetails.getId(), 
							   userDetails.getUsername(), 
							   userDetails.getEmail(), 
							   roles.get(0));
	}
	
	public ResponseEntity<?> registerUser(SignupRequest signupRequest){
		if (userRepository.getEntity(signupRequest.getKorIme()) != null) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Korisničko ime je zauzeto!"));
			return ResponseEntity.ok(new MessageResponse("Error: Korisničko ime je zauzeto!"));
		}

		if (userRepository.existsByEmail(signupRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Email se već koristi!"));
			return ResponseEntity.ok(new MessageResponse("Error: Email se već koristi!"));
		}
		
		User user = new User();
		user.setIme(signupRequest.getIme());
		user.setPrezime(signupRequest.getPrezime());
		user.setEmail(signupRequest.getEmail());
		user.setKorIme(signupRequest.getKorIme());
		user.setLozinka(encoder.encode(signupRequest.getLozinka()));
		user.setUlogaId(signupRequest.getUlogaId());
		
		userRepository.insertEntity(user);
		
		return ResponseEntity.ok(new MessageResponse("Uspješna registracija!"));
	}

}
