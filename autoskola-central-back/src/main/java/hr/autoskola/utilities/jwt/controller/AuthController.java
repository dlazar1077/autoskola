package hr.autoskola.utilities.jwt.controller;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.utilities.jwt.model.request.LoginRequest;
import hr.autoskola.utilities.jwt.model.request.SignupRequest;
import hr.autoskola.utilities.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		return ResponseEntity.ok(service.authenticateUser(loginRequest));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
		return service.registerUser(signupRequest);
	}
}
