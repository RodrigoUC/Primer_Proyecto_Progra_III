package pos.presentation.estadisticas;
public class Rango {
    int anioDesde;
    int mesDesde;
    int anioHasta;
    int mesHasta;

    public Rango(int anioDesde, int mesDesde, int anioHasta, int mesHasta) {
        this.anioDesde = anioDesde;
        this.mesDesde = mesDesde;
        this.anioHasta = anioHasta;
        this.mesHasta = mesHasta;
    }

    public Boolean esValido(){
        if(anioDesde <= anioHasta)
            if(mesDesde <= mesHasta)
                return true;
        return false;
    }

    public int cantidadDeMeses(){
        return (anioHasta - anioDesde) * 12 + (mesHasta - mesDesde) + 1;
    }

    public String getAnioMes(int c){
        return String.valueOf(anioDesde) + "-" + String.valueOf(mesDesde+c)  ;
    }

    public String toString(){
        return String.valueOf(anioDesde) + "-" + String.valueOf(mesDesde) ;
    }
}
