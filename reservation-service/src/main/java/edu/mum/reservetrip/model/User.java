package edu.mum.reservetrip.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;
}
