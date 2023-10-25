package org.jsp.api.repository;

import java.util.Optional;

import org.jsp.api.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.token=?1")
	Merchant findMerchantByToken(String token);
	
	@Query("select m from Merchant m where m.email=?1")
	Merchant findMerchantByEmail(String email);
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	public Optional<Merchant> loginVerifyByMerchant(String email, String password);
}
