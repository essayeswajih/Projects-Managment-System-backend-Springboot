package com.islem.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.islem.tasks.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    @JsonIgnore
    private List<ProjectDto> project;

    public static User toEntity(UserDto userDto) {

        final User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setProject(userDto.getProject() != null ? userDto.getProject().

                stream().map(ProjectDto::toEntity).collect(Collectors.toList()) : null
        );
        return user;
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .project(
                        user.getProject() != null ? user.getProject().stream().map(ProjectDto::fromEntity).collect(Collectors.toList()) : null)

                .build();
    }
}