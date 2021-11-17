package edu.miu.ars.service.email.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Email implements Serializable {
    private String subject;
    private String to;
    private String body;

}
