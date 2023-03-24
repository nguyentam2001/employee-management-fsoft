package fa.training.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private Integer gender;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(name="phone",length = 20)
    private String phone;
    @Column(name = "address",nullable = false)
    private String address;

    private String departmentName;
    @Column(name = "remark",nullable = false,length = 1000)
    private String remark;

    @OneToOne(mappedBy = "employee" ,orphanRemoval = true,cascade = CascadeType.ALL)
    private  Account account;
}
