package com.kbware.simpleapp.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Selfie {
    private String url;
    private boolean verified;
}
