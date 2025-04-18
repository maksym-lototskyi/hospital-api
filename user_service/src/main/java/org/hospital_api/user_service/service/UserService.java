package org.hospital_api.user_service.service;

import org.hospital_api.user_service.dto.UserReqDto;
import org.hospital_api.user_service.dto.UserRespDto;
import org.hospital_api.user_service.mapper.UserMapper;
import org.hospital_api.user_service.model.Role;
import org.hospital_api.user_service.model.User;
import org.hospital_api.user_service.repository.RoleRepository;
import org.hospital_api.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserRespDto createUser(UserReqDto userReqDto){
        Role role = roleRepository.findByName("USER").orElseThrow();
        User saved = userRepository.save(UserMapper.userReqDtoToUser(userReqDto, role));

        return UserMapper.userToUserRespDto(saved);
    }

    public UserRespDto getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        return UserMapper.userToUserRespDto(user);
    }


    public UserRespDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return UserMapper.userToUserRespDto(user);
    }
}
