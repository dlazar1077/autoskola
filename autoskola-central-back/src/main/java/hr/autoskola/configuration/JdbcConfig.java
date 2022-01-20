package hr.autoskola.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.Data;

@Data
@Configuration
public class JdbcConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
		
	/**
	 * Create oracle data source
	 * @param dbUser
	 * @param dbPassword
	 * @param dbUrl
	 * @return DataSource
	 * @throws SQLException
	 */
	private DataSource createOracleDataSourceFromConnParams() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
		
		return dataSource;
	}

	/**
	 * Connects to Database using properties on application servers
	 * 
	 * @return DataSource instance
	 * @throws SQLException 
	 */
	@Bean(name = "primaryDS")
	public DataSource dataSourcePrimaryLocal() {
		try {
			return createOracleDataSourceFromConnParams();
		} catch (Exception e) {
			return null;
		}
	}
		
	@Bean(name = "primaryJdbcTemplate")
	@Primary
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDS") DataSource ds) {
		return new JdbcTemplate(ds);
	}	
	
	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("primaryDS") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}	
}
