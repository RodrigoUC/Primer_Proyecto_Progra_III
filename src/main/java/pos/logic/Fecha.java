package pos.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Fecha {
    @XmlID
    private LocalDate fecha;

    public Fecha() {
        this.fecha = LocalDate.now(); // establece la fecha actual
    }

    public Fecha(int dia, int mes, int anio) {
        try {
            this.fecha = LocalDate.of(anio, mes, dia);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha no válida");
        }
    }

    // Constructor con argumento de cadena (formato dd/MM/yyyy)
    public Fecha(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.fecha = LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha no válido");
        }
    }

    public int getDia() {
        return fecha.getDayOfMonth();
    }

    public int getMes() {
        return fecha.getMonthValue();
    }

    public int getAnio() {
        return fecha.getYear();
    }

    public String toString() {
        return String.format("%.2f-&.2f", getAnio(), getMes());
    }

    // Método para obtener la fecha como cadena
    public String getFechaComoCadena() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    // Método para verificar si la fecha es anterior a otra fecha
    public boolean esAnteriorA(Fecha otraFecha) {
        return this.fecha.isBefore(otraFecha.fecha);
    }

    // Método para verificar si la fecha es posterior a otra fecha
    public boolean esPosteriorA(Fecha otraFecha) {
        return this.fecha.isAfter(otraFecha.fecha);
    }

    // Método para verificar si la fecha es igual a otra fecha
    public boolean esIgualA(Fecha otraFecha) {
        return this.fecha.isEqual(otraFecha.fecha);
    }

}
