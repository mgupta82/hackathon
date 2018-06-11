package com.hcl.core;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/core")
public class AccountResource {
	
	@Autowired
	AccountRepository accountRepository;
	
	  @GetMapping("/accounts")
	  public List<Account> retrieveAllAccounts() {
	    return accountRepository.findAll();
	  }

	  @GetMapping("/accounts/{id}")
	  public Account retrieveAccount(@PathVariable long id) {
	    Optional<Account> account = accountRepository.findById(id);

	    if (!account.isPresent())
	      throw new AccountNotFoundException("id-" + id);

	    return account.get();
	  }

	  @DeleteMapping("/accounts/{id}")
	  public void deleteAccount(@PathVariable long id) {
	    accountRepository.deleteById(id);
	  }

	  @PostMapping("/accounts")
	  public ResponseEntity<Object> createAccount(@RequestBody Account account) {
	    Account savedAccount = accountRepository.save(account);

	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	        .buildAndExpand(savedAccount.getId()).toUri();

	    return ResponseEntity.created(location).build();

	  }
	  
	  @PutMapping("/accounts/{id}")
	  public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable long id) {

	    Optional<Account> accountOptional = accountRepository.findById(id);

	    if (!accountOptional.isPresent())
	      return ResponseEntity.notFound().build();

	    account.setId(id);
	    
	    accountRepository.save(account);

	    return ResponseEntity.noContent().build();
	  }
}
