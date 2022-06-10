package com.softserve.service.Implementation;

import com.softserve.dao.ReaderDao;
import com.softserve.dao.RoleDao;
import com.softserve.dao.UserDao;
import com.softserve.entity.Book;
import com.softserve.entity.User;
import com.softserve.entity.UserRole;
import com.softserve.service.UserService;
import com.softserve.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static Long id;

    @Autowired
    ReaderDao readerDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Long getId() { return id; }

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getReaders() {
        return readerDao.getReaders();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<String> getStat() {
        return readerDao.getStat();
    }

    @Override
    public List<User> getDebtors() {
        return readerDao.getDebtors();
    }

    @Override
    public List<Book> getStatByReader(String action, long id) {
        return readerDao.getStatByReader(action, id);
    }

    @Override
    public Integer timeWithLibrary(long id) {
        return readerDao.timeWithLibrary(id);
    }

    @Override
    @Transactional
    public User findByUserEmail(String userEmail) { return userDao.findByUserEmail(userEmail); }

    @Override
    @Transactional
    public void save(UserValidator userValidator) {
        User user = new User();
        user.setFirstName(userValidator.getFirstName());
        user.setLastName(userValidator.getLastName());
        user.setAge(userValidator.getAge());
        user.setPhone(userValidator.getPhone());
        user.setEmail(userValidator.getEmail());
        user.setPassword(passwordEncoder.encode(userValidator.getPassword()));
        user.setRegDate(Date.valueOf(LocalDate.now()));
        user.setRole(roleDao.findRoleByName("ROLE_Reader"));
        userDao.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userDao.findByUserEmail(userEmail);
        if (user == null) {
            id = null;
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        id = user.getId();
        Collection<UserRole> roles = new ArrayList<>();
        roles.add(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
