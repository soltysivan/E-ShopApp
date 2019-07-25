package org.shop.dao.repository;

import org.shop.dao.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByLogin(String login);

    Optional<ApplicationUser> findById(Long id);
}
