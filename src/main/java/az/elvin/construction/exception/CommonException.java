package az.elvin.construction.exception;

import az.elvin.construction.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CommonException extends RuntimeException {

    private ResponseEnum responseEnum;
}
