package com.idi.controllers;

import com.idi.dao.Account;
import com.idi.dao.AccountRepo;
import com.idi.dao.PersonRepo;
import com.idi.errors.AppException;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepo accountRepo;
    private final PersonRepo personRepo;
    private final Javers javers;

    @Autowired
    public AccountController(AccountRepo accountRepo ,PersonRepo personRepo ,Javers javers) {
        this.accountRepo = accountRepo;
       this.personRepo = personRepo;
        this.javers = javers;
    }

    @GetMapping("/")
    public Iterable<Account> getAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        return accountRepo.findOne(id);
    }


    @PutMapping("/")
    public Account update(@RequestBody Account account) throws AppException {
       if (validatePasswordHistory(account)) {
          throw new AppException("password used before");
       }

        personRepo.save(account.getPerson());
        return accountRepo.save(account);
    }

   private boolean validatePasswordHistory(@RequestBody Account account) {
      String password = account.getPassword();
      List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstanceId(account.getId(), Account.class).limit(5).build());

      for (final CdoSnapshot snapshot : snapshots) {
         if (snapshot.getPropertyValue("password").equals(password)) {
            return true;
         }
      }

      return false;
   }
}