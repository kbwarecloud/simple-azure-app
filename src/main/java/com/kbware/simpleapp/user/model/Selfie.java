package com.kbware.simpleapp.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Selfie {
    private String url;
    private boolean verified;
}
