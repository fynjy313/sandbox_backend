package com.ckib.sandbox_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artifacts_info")
public class Artifact {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Column(name = "status", length = 64)
    private String status;
}
