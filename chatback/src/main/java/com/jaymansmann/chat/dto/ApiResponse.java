/**
 * 
 */
package com.jaymansmann.chat.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jay
 *
 */
@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}