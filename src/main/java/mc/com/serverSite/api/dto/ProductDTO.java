package mc.com.serverSite.api.dto;

import mc.com.serverSite.entity.Product;

public record ProductDTO(
        String name,
        String description,
        String[] imagesURLs,
        Long priceInCents,
        String purchaseUrl,
        boolean available
) {
    public static ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getImagesURLs(),
                product.getPriceInCents(),
                product.getPurchaseUrl(),
                product.isAvailable()
        );
    }
}
