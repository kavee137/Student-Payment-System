package lk.ijse.studentpaymentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {
    private String nic;
    private String name;
    private String arrived;

    public StudentDTO(String name, String arrived) {
        this.name = name;
        this.arrived = arrived;
    }
}
