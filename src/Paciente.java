import java.util.Stack;

public class Paciente{
    private String nombre;
    private String apellido;
    private String id;
    private int categoria;
    private long tiempoLlegada;
    private String estado;
    private String area;
    private Stack<String> historialCambios;

    public Paciente(String nombre, String apellido, String id, int categoria, long tiempoLlegada, String area){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = categoria;
        this.tiempoLlegada = tiempoLlegada;
        this.estado = "en_espera";
        this.area = area;
        this.historialCambios = new Stack<>();
    }

    public int getCategoria() {
        return categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getId() {
        return id;
    }
    public long getTiempoLlegada() {
        return tiempoLlegada;
    }
    public String getEstado() {
        return estado;
    }
    public String getArea() {
        return area;
    }
    public Stack<String> getHistorialCambios() {
        return historialCambios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public void setTiempoLlegada(long tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setHistorialCambios(Stack<String> historialCambios) {
        this.historialCambios = historialCambios;
    }

    public long tiempoEsperaActual(long tiempo){
        return (tiempo - tiempoLlegada) / 60000;
    }

    public void registrarCambio(String descripcion){
        historialCambios.push(descripcion);
    }

    public String obtenerUltimoCambio(){
        if(historialCambios.isEmpty()){
            return "No hay cambios";
        }
        else{
            return historialCambios.pop();
        }
    }
}