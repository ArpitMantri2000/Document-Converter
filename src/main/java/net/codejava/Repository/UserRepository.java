package net.codejava.Repository;

import java.util.List;

import javax.print.Doc;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmailAndPassword(String email,String password);
}
