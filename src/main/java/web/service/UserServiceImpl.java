package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.repository.UserRep;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    UserRep rep;

    public void saveUser(User user) {
        rep.save(user);
    }

    public void removeUser(int id) {
        rep.deleteById(id);
    }

    public User getUserById(int id) {
        return rep.findById(id).get();
    }

    public List<User> listUser() {
        return (List<User>) rep.findAll();
    }
}
