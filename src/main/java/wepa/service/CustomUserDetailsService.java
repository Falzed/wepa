package wepa.service;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KayttajaRepository accountRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kayttaja account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority(account.getAuthority())));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (accountRepository.findByUsername("admin") == null) {
            Kayttaja testAdmin = new Kayttaja();
            testAdmin.setAuthority("ADMIN");
            testAdmin.setPassword(passwordEncoder.encode("admin"));
            testAdmin.setUsername("admin");
            accountRepository.save(testAdmin);
        }

        if (accountRepository.findByUsername("user") == null) {
            Kayttaja testUser = new Kayttaja();
            testUser.setAuthority("USER");
            testUser.setPassword(passwordEncoder.encode("user"));
            testUser.setUsername("user");
            accountRepository.save(testUser);
        }
    }
}
