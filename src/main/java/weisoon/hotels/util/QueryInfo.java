package weisoon.hotels.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class QueryInfo {
    
    private String destinationId;
    private String checkIn;
    private String checkOut;
    private int guests;
    private String tenantId;
    
}
