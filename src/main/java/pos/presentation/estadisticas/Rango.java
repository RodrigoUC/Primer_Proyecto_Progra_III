package pos.presentation.estadisticas;

public class Rango {
    int anioDesde;
    int mesDesde;
    int anioHasta;
    int mesHasta;

    public Rango(){
        this.anioDesde = 0;
        this.mesDesde = 0;
        this.anioHasta = 0;
        this.mesHasta = 0;
    }

    public Rango(int anioDesde, int mesDesde, int anioHasta, int mesHasta) {
        this.anioDesde = anioDesde;
        this.mesDesde = mesDesde;
        this.anioHasta = anioHasta;
        this.mesHasta = mesHasta;
    }

    public int cantidadDeMeses(){
        return (anioHasta - anioDesde) * 12 + (mesHasta - mesDesde) + 1;
    }

    public int getAnio(){
        return anioDesde + (anioHasta - anioDesde);
    }

    public int getMes(){
        return (mesHasta - mesDesde);
    }

    public String getAnioMes(int m){
        int a = 0;
        while(mesDesde + m > 12){
            m -= 12;  // Mes
            a++;     // Año
        }
        return String.valueOf(anioDesde + a) + "-" + String.format("%02d", mesDesde + m);
    }

}
