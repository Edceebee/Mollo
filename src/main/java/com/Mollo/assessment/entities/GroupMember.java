package com.Mollo.assessment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String occupation;

    private String nameOfGroup;

    private int amountToSave;

    private int totalAmountSaved;

    @ManyToOne
    @JoinColumn(name = "esusu_group_id")
    private EsusuGroup esusuGroup;

}
