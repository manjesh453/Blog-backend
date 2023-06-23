package com.manjesh.blog;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.manjesh.blog.config.AppConstant;
import com.manjesh.blog.entities.Role;
import com.manjesh.blog.repositories.RoleRepo;


@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
      @Autowired
	private RoleRepo roleRepo;
 
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}
	@Override
	public void run(String... args) throws Exception {
		try {
			Role role=new Role();
			role.setId(AppConstant.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1=new Role();
			role1.setId(AppConstant.Normal_USER);
			role1.setName("NORMAL_USER");
			
          List<Role>roles=new ArrayList<>();
          roles.add(role);
          roles.add(role1);
          
          List<Role>result=this.roleRepo.saveAll(roles);
          
          result.forEach(r->{
        	  System.out.println(r.getName());
          });
          
			
		}catch (Exception e) {
			e.printStackTrace();		}
		
	}

}
