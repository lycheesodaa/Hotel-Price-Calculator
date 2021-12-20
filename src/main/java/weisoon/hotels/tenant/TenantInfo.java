package weisoon.hotels.tenant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TenantInfo {

    private String type;
    private double markup;
    private long serviceFee;
    private long maxCap;
}
