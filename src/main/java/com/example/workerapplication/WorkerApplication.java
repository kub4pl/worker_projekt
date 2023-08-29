package com.example.workerapplication;

import com.example.workerapplication.model.Role;
import com.example.workerapplication.model.RoleType;
import com.example.workerapplication.model.User;
import com.example.workerapplication.repository.RoleRepository;
import com.example.workerapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Optional;

@EnableFeignClients
@SpringBootApplication
public class WorkerApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
	}
	@PostConstruct
	public void init()
	{
		User user = new User();
		user.setUsername("Kuba200");
		user.setPassword(bCryptPasswordEncoder.encode("123456"));

		Role role = new Role();
		role.setDescription("USER");
		role.setType(RoleType.USER);

		if(!roleRepository.existsByType(RoleType.USER))
		{
			roleRepository.save(role);
		}
		Optional<Role> roleOptional = roleRepository.findByType(RoleType.USER);
		if (roleOptional.isPresent()){
			role = roleOptional.get();
		}else {
			throw new RuntimeException(" Can not found role");
		}
		user.setRole(role);
		if(!userRepository.existsByUsername(user.getUsername())){

			userRepository.save(user);

		}
	}

}
