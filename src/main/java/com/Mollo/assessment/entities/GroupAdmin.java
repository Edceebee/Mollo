package com.Mollo.assessment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupAdminId;

    private String adminFullName;

    @OneToOne
//    @JsonIgnore
    private EsusuGroup esusuGroup;


}
