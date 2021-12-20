package weisoon.hotels.supplier;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import weisoon.hotels.util.QueryInfo;

/**
 * Service class to help with consuming the external API from the supplier
 */
@Component
public class SupplierService {

    public List<SupplierHotel> getData(QueryInfo queryInfo) {
        String url = createUrl(queryInfo);

        RestTemplate restTemplate = new RestTemplate();
        Supplier result = restTemplate.getForObject(url, Supplier.class);
        List<SupplierHotel> data = result.getHotels();

        return data;
    }

    private String createUrl(QueryInfo queryInfo) {
        // method to build a URL based on query info from HotelController
        // assumed fixed, thus unimplemented

        return "http://www.mocky.io/v2/5d99f2ac310000720097da14";
    }

}
