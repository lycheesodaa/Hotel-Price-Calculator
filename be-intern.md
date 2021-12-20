Ascenda's travel platform is a multi-tenant platform, which means that in 1 single platform, we support multiple travel sites. Each travel site has a different look and feel, but pricing is done through a central hotel pricing API.

Within the central hotel pricing API, we have the ability to configure pricing parameters (e.g. markup) for each tenant, and a standard calculation is applied (e.g. cost price \* (1+markup) = selling price). In some extreme cases, a tenant may require a price calculation logic that is completely different from our standard templatized format, and we would need the ability to specify a custom pricing logic for this tenant.

The goal of this task would be to build a simple hotel pricing API which supports the configuration of pricing calculation for 3 tenants, A, B and C. The calculation logic for each tenant is provided below.

The API looks like this:

GET /hotels/price?destination_id=1&check_in=YYYY-MM-DD&check_out=YYYY-MM-DD&guests=2tenant_id=A

Note: In reality, we will not allow tenant_id to be specified as a query string. It is usually detected from the request host, or some kind of tenant ID sent in the request headers. For the purpose of this exercise, we will assume that the tenant_id is specified in the query string.

The general workflow of the api looks like this:

1. Make API call to hotel supplier, for the provided destination, check in/out dates and guest count. This retrieves a list of hotel IDs and the corresponding cost price.

2. Determine the current tenant of the hotel pricing call.

3. Apply the correct pricing calculation to the hotels

4. Format the response and send back to the client.

For the purpose of this exercise, we can assume that the API call to the supplier is fixed, and can be called at http://www.mocky.io/v2/5d99f2ac310000720097da14 (you dont need to provide params)

The output of the hotel pricing API should look like this:

    [
      {
        "hotel_id": "iJhz",
        "price": 256.30
      },
      {
        "hotel_id": "f8c9",
        "price": 100.90
      },
      {
        "hotel_id": "SjyX",
        "price": 1044.20
      },
    ]

The calculation logic for each tenant are as follows:

**Tenant A:**

Markup to apply: 15%
Service fee to add to booking: $10
Maximum price of hotel (after calculation): $1000 (any hotel that prices above this is filtered out)

**Tenant B:**

Markup to apply: 0%
Service fee to add to booking: $25
Maximum price of hotel (after calculation): $500 (any hotel that prices above this is filtered out)

**Tenant C:**

Tenant C has a slightly more complicated logic. Hotel prices for Tenant C follow this model:

If cost price is between 0 and 200 (inclusive), sell the hotel at $250
If cost price is between 201 and 500 (inclusive), sell the hotel at $800
If the cost price is between 501 and 1000 (inclusive), sell the hotel at $1500
If the cost price is above $1000, filter the hotel out.
