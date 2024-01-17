package com.brainwired.User.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {

    @NotBlank(message = "userId shouldn't be null or blank")
    private String userId;
    @NotBlank(message = "firstName shouldn't be null or blank")
    private String firstName;
    @NotBlank(message = "lastName shouldn't be null or blank")
    private String lastName;
    @NotBlank(message = "dateOfBirth shouldn't be null or blank")
    private String dateOfBirth;
    @NotBlank(message = "address shouldn't be null or blank")
    private String address;

}
