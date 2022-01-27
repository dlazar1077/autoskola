package hr.autoskola.utilities.jwt.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	private String korIme;
	private String ime;
	private String prezime;
	private String lozinka;
	private String email;
	private Long ulogaId;
}
