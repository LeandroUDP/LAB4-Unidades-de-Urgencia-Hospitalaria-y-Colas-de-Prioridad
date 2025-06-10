import java.util.*;

public class Hospital {
    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion;
    private List<Paciente> pacientesAtendidos;

    public Hospital() {
        this.pacientesTotales = new HashMap<>();
        this.colaAtencion = new PriorityQueue<>(
                Comparator.comparingInt(Paciente::getCategoria)
                        .thenComparingLong(Paciente::getTiempoLlegada)
        );

        this.areasAtencion = new HashMap<>();
        areasAtencion.put("SAPU", new AreaAtencion("SAPU", 100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto", 70));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 50));

        this.pacientesAtendidos = new ArrayList<>();
    }

    public void registrarPaciente(Paciente p) {
        colaAtencion.add(p);
        pacientesTotales.put(p.getId(), p);
        AreaAtencion area = areasAtencion.get(p.getArea());
        if (area != null) {
            area.ingresarPaciente(p);
        } else {
            System.out.println("Área no encontrada para el paciente: " + p.getId());
        }
        p.registrarCambio("Registro del paciente " + p.getEstado() + " con categoría " + p.getCategoria());
    }

    public void reasignarCategoria(String id, int nCategoria) {
        Paciente paciente = pacientesTotales.get(id);
        if (paciente != null) {
            colaAtencion.remove(paciente);
            AreaAtencion areaAtencion = areasAtencion.get(paciente.getArea());
            if (areaAtencion != null) {
                areaAtencion.getPacientesHeap().remove(paciente);
            }

            paciente.setCategoria(nCategoria);
            paciente.registrarCambio("Categoría actualizada a " + nCategoria);

            colaAtencion.add(paciente);
            if (areaAtencion != null) {
                areaAtencion.ingresarPaciente(paciente);
            }
        }
    }

    public Paciente atenderSiguiente() {
        Paciente paciente = colaAtencion.poll();
        if (paciente != null) {
            paciente.setEstado("atendido");
            paciente.registrarCambio("Paciente atendido");

            AreaAtencion areaAtencion = areasAtencion.get(paciente.getArea());
            if (areaAtencion != null) {
                areaAtencion.getPacientesHeap().remove(paciente);
            }

            pacientesAtendidos.add(paciente);
        }
        return paciente;
    }

    public List<Paciente> obtenerPacientesPorCategoria(int nCategoria) {
        List<Paciente> pacientes = new ArrayList<>();
        for (Paciente p : colaAtencion) {
            if (p.getCategoria() == nCategoria) {
                pacientes.add(p);
            }
        }
        return pacientes;
    }

    public AreaAtencion obtenerAreaAtencion(String nombre) {
        return areasAtencion.get(nombre);
    }

    public List<Paciente> getPacientesAtendidos() {
        return pacientesAtendidos;
    }

    public Map<String, Paciente> getPacientesTotales() {
        return pacientesTotales;
    }

    public PriorityQueue<Paciente> getColaAtencion() {
        return colaAtencion;
    }

    public Map<String, AreaAtencion> getAreasAtencion() {
        return areasAtencion;
    }
}