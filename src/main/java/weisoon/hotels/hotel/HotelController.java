package weisoon.hotels.hotel;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weisoon.hotels.supplier.SupplierHotel;
import weisoon.hotels.supplier.SupplierService;
import weisoon.hotels.util.PriceCalculator;
import weisoon.hotels.util.QueryInfo;

@Controller
public class HotelController {

    private SupplierService supplierService;
    private PriceCalculator priceCalculator;

    public HotelController(SupplierService supplierService, PriceCalculator priceCalculator) {
        this.supplierService = supplierService;
        this.priceCalculator = priceCalculator;
    }

    /**
     * Get all hotels satisfying requirements of tenant
     * @param destinationId
     * @param checkIn
     * @param checkOut
     * @param guests
     * @param tenantId
     * @return list of custom hotel pricings for said tenant
     */
    @GetMapping("/hotels/price")
    @ResponseBody
    public List<Hotel> getHotelPricing(@RequestParam("destination_id") String destinationId,
            @RequestParam("check_in") String checkIn, @RequestParam("check_out") String checkOut,
            @RequestParam("guests") int guests, @RequestParam("tenant_id") String tenantId) {
        QueryInfo queryInfo = new QueryInfo(destinationId, checkIn, checkOut, guests, tenantId);

        // every call is different due to a change in parameters
        List<SupplierHotel> supplierList = supplierService.getData(queryInfo);

        return priceCalculator.classification(supplierList, tenantId);
    }
}
