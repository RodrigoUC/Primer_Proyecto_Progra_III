//package pos.logic;
//
//import java.util.Objects;
//
//public class Categoria {
//    String id;
//    String nombre;
//
//    public Categoria() {this("");}
//
//    public Categoria(String nombre){
//        this.nombre = nombre;
//        this.id = getId();
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getId() {
//        return switch (nombre) {
//            case "Aguas" -> "001";
//            case "Dulces" -> "002";
//            case "Aceites" -> "003";
//            case "Vinos" -> "004";
//            default -> "000";
//        };
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String toString() {
//        return nombre;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Categoria categoria = (Categoria) obj;
//        return nombre.equals(categoria.nombre);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(nombre);
//    }
//}
