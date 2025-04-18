package org.hospital_api.appointment_service.feing_client;

import org.hospital_api.appointment_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service", url = "http://localhost:8082/user")
public interface UserServiceFeignClient {
    @GetMapping("/fetch/{id}")
   Optional<UserDto> getUser(@PathVariable Long id);
}
