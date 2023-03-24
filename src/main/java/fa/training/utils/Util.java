package fa.training.utils;

import fa.training.dto.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

public class Util {

    public  static void mapper(List<EmployeeDTO> employeeDTOS,List<Object[]> objects){
        EmployeeDTO employeeDTO;
        for (Object[] object : objects) {
            employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(Integer.parseInt(object[0].toString()));
            employeeDTO.setFirstName(object[1].toString());
            employeeDTO.setLastName(object[2].toString());
            employeeDTO.setDateOfBirth((LocalDate) object[3]);
            employeeDTO.setPhone(object[4].toString());
            employeeDTO.setAddress(object[5].toString());
            employeeDTO.setDepartmentName(object[6].toString());
            employeeDTOS.add(employeeDTO);
        }
    }
}
