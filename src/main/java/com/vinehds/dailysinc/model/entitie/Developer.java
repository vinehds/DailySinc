package com.vinehds.dailysinc.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_developers")
public class Developer implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dev_name")
    private String name;

    @Column(name = "dev_password")
    private String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "dev_team", nullable = true)
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Daily> dailies = new ArrayList<>();

    @Column(name = "dev_userRole")
    private UserRole userRole;

    @Column(name = "dev_departament")
    private Department department;

    @Column(name = "dev_email")
    private String email;

    public Developer(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userRole == UserRole.ADMIN)
            return
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                            new SimpleGrantedAuthority("ROLE_TECHLEAD"),
                            new SimpleGrantedAuthority("ROLE_USER"));
        else if(this.userRole == UserRole.TECHLEAD)
            return
                    List.of(new SimpleGrantedAuthority("ROLE_TECHLEAD"),
                            new SimpleGrantedAuthority("ROLE_USER"));
        else return
                    List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
