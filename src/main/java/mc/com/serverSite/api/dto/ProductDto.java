package mc.com.serverSite.api.dto;

import mc.com.serverSite.entity.Product;

public record ProductDto(
        String name,
        String description,
        String[] imagesURLs,
        Long priceInCents,
        String purchaseUrl,
        boolean available
) {
    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getImagesURLs(),
                product.getPriceInCents(),
                product.getPurchaseUrl(),
                product.isAvailable()
        );
    }
}
