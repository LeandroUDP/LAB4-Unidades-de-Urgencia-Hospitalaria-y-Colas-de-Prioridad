import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class AreaAtencion{
    private String nombre;
    private PriorityQueue<Paciente> pacientesHeap;
    private int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.pacientesHeap = new PriorityQueue<>(Comparator.comparingInt(Paciente::getCategoria)
                                                .thenComparingLong(Paciente::getTiempoLlegada));
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public PriorityQueue<Paciente> getPacientesHeap() {
        return pacientesHeap;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setPacientesHeap(PriorityQueue<Paciente> pacientesHeap) {
        this.pacientesHeap = pacientesHeap;
    }


    public Paciente atenderPaciente() {
        Paciente p = pacientesHeap.poll();
        return p;
    }

    public boolean estaSaturada() {
        if (pacientesHeap.size() >= capacidadMaxima) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ingresarPaciente(Paciente p){
        if(p.getCategoria() == 1){
            pacientesHeap.add(p);
            return true;
        }
        else if (pacientesHeap.size() >= capacidadMaxima) {
            return false;
        } else {
            pacientesHeap.add(p);
            return true;
        }
    }

    public List<Paciente> obtenerPacientesPorHeapSort() {
        List<Paciente> listaOrdenada = new ArrayList<>();
        PriorityQueue<Paciente> copia = new PriorityQueue<>(pacientesHeap);

        while (!copia.isEmpty()) {
            listaOrdenada.add(copia.poll());
        }
        return listaOrdenada;
    }
}