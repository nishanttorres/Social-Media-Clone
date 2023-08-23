package com.diyo.smc.service;

import com.diyo.smc.entity.Post;
import com.diyo.smc.entity.User;
import com.diyo.smc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;
    public void addUser(User user) {
        userRepository.save(user);
    }
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return null;
        }
    }
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
    public String deleteUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            userRepository.deleteById(userId);
            return "Successfully deleted the user";
        }
        return "User not found!";
    }

    public String editUser(User user){
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()){
            User old = userOptional.get();
            if(user.getFirstName() != null){
                old.setFirstName(user.getFirstName());
            }if(user.getLastName() != null){
                old.setLastName(user.getLastName());
            }if(user.getDob() != null){
                old.setDob(user.getDob());
            }if(user.getEmail() != null){
                old.setEmail(user.getEmail());
            }if(user.getPassword() != null){
                old.setPassword(user.getPassword());
            }if(user.getLikedPostsId() != null){
                old.setLikedPostsId(user.getLikedPostsId());
            }if(user.getFollowersUsersId() != null){
                old.setFollowersUsersId(user.getFollowersUsersId());
            }if(user.getFollowingUsersId() != null){
                old.setFollowingUsersId(user.getFollowingUsersId());
            }
            userRepository.save(old);
            return "User Modified Successfully.";
        }
        return "User not found!";
    }
    public User validateUser(User user) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            return null;
        }
    }
}
