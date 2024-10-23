package pos.logic;

import java.time.format.DateTimeParseException;

public class Fecha {
    private String codigo;

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;
        this.codigo = anio + "-" + mes + "-" + dia;
    }

    public Fecha(int anio, int mes, int dia) {
        try {
            this.dia = dia;
            this.mes = mes;
            this.anio = anio;
            this.codigo = anio + "-" + mes + "-" + dia;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha no válida");
        }
    }

    public String getCodigo(){ return codigo;}

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAnio() {
        return this.anio;
    }

    public String toString() {
        return codigo;
    }

}
