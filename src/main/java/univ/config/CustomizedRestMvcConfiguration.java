package univ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import univ.domain.Course;
import univ.domain.Student;
import univ.domain.Teacher;

@Configuration
public class CustomizedRestMvcConfiguration {

	@Bean
	public RepositoryRestConfigurerAdapter customRestConfigurerAdapter() {
		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				super.configureRepositoryRestConfiguration(config);
				config.setBasePath("/api");
				config.exposeIdsFor(Student.class, Course.class, Teacher.class);
			}
		};
	}

}