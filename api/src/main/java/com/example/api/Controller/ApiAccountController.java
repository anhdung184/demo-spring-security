/*package com.example.api.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.Model.Account;
import com.example.api.Repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class ApiAccountController {
    @Autowired
    private AccountRepository accountRepository;

	@GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") long id){
        Optional<Account> accounts = accountRepository.findById(id);

		if (accounts.isPresent()) {
        Account account = accounts.get();
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		try {
			Account _account = accountRepository
					.save(new Account(account.getAccusername(), account.getAccpassword(), account.getAccpermissions()));
			return new ResponseEntity<>(_account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
		Optional<Account> accountid = accountRepository.findById(id);

		if (accountid.isPresent()) {
			Account _account = accountid.get();
			_account.setAccusername(account.getAccusername());
			_account.setAccpassword(account.getAccpassword());
			_account.setAccpermissions(account.getAccpermissions());
			return new ResponseEntity<>(accountRepository.save(_account), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    @DeleteMapping("/accounts/{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
		try {
			accountRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/accounts")
	public ResponseEntity<HttpStatus> deleteAllAccounts() {
		try {
			accountRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
*/