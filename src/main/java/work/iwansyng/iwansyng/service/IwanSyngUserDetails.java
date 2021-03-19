package work.iwansyng.iwansyng.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import work.iwansyng.iwansyng.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class IwanSyngUserDetails implements UserDetails {
    private String userName;
    private String FirstName;
    private String LastName;
    private String passWord;
    private Boolean isActive;
    private List<GrantedAuthority> authorities;

    public IwanSyngUserDetails(User user) {
        this.userName = user.getUserName();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.passWord = user.getPassword();
        this.isActive = user.getActive();
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
    }

    /* TODO: These values are hardcoded will need to refactor */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
