package dev.saas.geobox.enuns;

public enum DeliveryStatus {

    ENTREGA(1L, "Entrega"),
    RETIRADA(2L, "Retirada");

    private Long id;
    private String description;

    DeliveryStatus(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
