package com.luckvicky.blur.domain.channel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="tags")
public class Tags {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true,nullable = false, length = 20)
    private String name;


    @Builder
    public Tags(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
