package fa.training.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String departmentName;


}
