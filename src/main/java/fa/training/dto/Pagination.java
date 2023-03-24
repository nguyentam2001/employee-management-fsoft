package fa.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagination {
    private Integer totalPage;
    private Integer totalRecords;
    private  List<EmployeeDTO> data;
}
