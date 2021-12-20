package weisoon.hotels.tenant;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class TenantService {

    private HashMap<String, TenantInfo> tenants = new HashMap<>();

    public TenantService() {
        tenants.put("A", new TenantInfo("A", 0.15, 10, 1000));
        tenants.put("B", new TenantInfo("B", 0, 25, 500));
    }

    public TenantInfo getTenant(String type) {
        return tenants.get(type);
    }

    public void addTenant(TenantInfo tenantInfo) {
        tenants.put(tenantInfo.getType(), tenantInfo);
    }

    public void removeTenant(TenantInfo tenantInfo) {
        tenants.remove(tenantInfo.getType());
    }

}
