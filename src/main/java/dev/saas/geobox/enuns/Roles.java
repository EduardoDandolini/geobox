package dev.saas.geobox.enuns;

public enum Roles {

    ADMIN(1L),
    USER(2L);

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Roles(Long id) {
        this.id = id;
    }
}
