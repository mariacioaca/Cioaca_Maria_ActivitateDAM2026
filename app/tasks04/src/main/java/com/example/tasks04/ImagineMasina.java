package com.example.tasks04;


public class ImagineMasina {
    private String urlImagine;
    private String descriere;
    private String urlPagina;

    public ImagineMasina(String urlImagine,
                         String descriere,
                         String urlPagina) {
        this.urlImagine = urlImagine;
        this.descriere = descriere;
        this.urlPagina = urlPagina;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getUrlPagina() {
        return urlPagina;
    }
}
