package pos.logic;

public class Usuario {
    private String ID;
    private String contrasena;
    public Usuario(String ID, String contrasena) {
        this.ID = ID;
        this.contrasena = contrasena;
    }
    public Usuario(){
        this.ID = "";
        this.contrasena = "";
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return ID;
    }
}
