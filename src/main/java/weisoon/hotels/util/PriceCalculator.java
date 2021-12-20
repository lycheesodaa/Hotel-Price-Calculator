package weisoon.hotels.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import weisoon.hotels.hotel.Hotel;
import weisoon.hotels.supplier.SupplierHotel;
import weisoon.hotels.tenant.TenantInfo;
import weisoon.hotels.tenant.TenantService;

@Component
public class PriceCalculator {

    @Autowired
    private TenantService tenantService;

    @Autowired
    public PriceCalculator(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    public List<Hotel> classification(List<SupplierHotel> supplierList, String tenantId) {
        if (tenantId == "C") {
            return tenantC(supplierList);
        } else {
            return tenant(supplierList, tenantId);
        }
    }

    public List<Hotel> tenant(List<SupplierHotel> supplierList, String tenantId) {
        List<Hotel> result = new ArrayList<>();
        TenantInfo tenantInfo = tenantService.getTenant(tenantId);

        for (SupplierHotel hotel : supplierList) {
            double cost = hotel.getPrice() * (1 + tenantInfo.getMarkup()) + tenantInfo.getServiceFee();

            if (cost < tenantInfo.getMaxCap()) {
                result.add(new Hotel(hotel.getId(), round(cost)));
            }
        }

        return result;
    }

    public List<Hotel> tenantC(List<SupplierHotel> supplierList) {
        List<Hotel> result = new ArrayList<>();

        for (SupplierHotel hotel : supplierList) {
            if (hotel.getPrice() >= 0 && hotel.getPrice() <= 200) {
                result.add(new Hotel(hotel.getId(), round(250)));
            } else if (hotel.getPrice() > 200 && hotel.getPrice() <= 500) {
                result.add(new Hotel(hotel.getId(), round(800)));
            } else if (hotel.getPrice() > 500 && hotel.getPrice() <= 1000) {
                result.add(new Hotel(hotel.getId(), round(1500)));
            }
        }

        return result;
    }

    public static BigDecimal round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}
