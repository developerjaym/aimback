/**
 * 
 */
package com.jaymansmann.chat.dto.auth;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Jay
 *
 */
@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 32)
    private String username;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;

}