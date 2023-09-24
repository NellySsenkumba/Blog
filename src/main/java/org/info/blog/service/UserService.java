package org.info.blog.service;

import org.info.blog.models.User;
import org.info.blog.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<List<User>> addUser(List<User> user) {
        for (User users : user) {
//            if (checkNullFields(users)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            if (usersRepository.existsByEmail(users.getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }

//        user = user.stream().map(users -> {
//                    users.setDateCreated(new Timestamp(System.currentTimeMillis()));
//                    return users;
//                }
//        ).toList();


        return new ResponseEntity<>(usersRepository.saveAllAndFlush(user), HttpStatus.CREATED);
    }

    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<User> oneUsers(long id) {
        return new ResponseEntity<>(usersRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }


//    public boolean checkNullFields(User user) {
//        return user.getFirstName() == null || user.getLastName() == null || user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getDateOfBirth() == null;
//
//    }

    public ResponseEntity<User> editUsers(User user) {
        return new ResponseEntity<>(usersRepository.save(user), HttpStatus.CREATED);

    }
}
