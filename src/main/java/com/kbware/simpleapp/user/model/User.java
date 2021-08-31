package com.kbware.simpleapp.user.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Container(containerName = "Users")
public class User {
    @Id
    private Long id;
    private String name;

    @PartitionKey
    private String departament;

    private Selfie selfie;
}
