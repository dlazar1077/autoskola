package hr.autoskola.utilities.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Klasa za exception koji bacamo kad ne prode provjera tokena
 *
 */
public class JwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -6386804162652869698L;
	
	public JwtAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

}
