package com.diyo.smc.model;

import com.diyo.smc.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    private final ModelMapper modelMapper;

    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public UserDTO convertToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}
