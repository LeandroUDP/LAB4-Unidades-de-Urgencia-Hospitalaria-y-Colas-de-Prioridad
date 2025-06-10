import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneradorPacientes {

    private static final String [] Nombres = {"Juan", "María", "Pedro", "Lucía", "Carlos", "Ana", "Javier", "Sofía", "Andrés", "Camila", "Diego", "Valentina", "Fernando", "Paula", "Luis", "Isidora", "Tomás", "Josefina", "Matías", "Antonia"};
    private static final String [] Apellidos = {"Pérez", "González", "Ramírez", "Fernández", "Soto", "Rojas", "Morales", "Castillo", "Vega", "Herrera", "Navarro", "Campos", "Reyes", "Fuentes", "Medina", "Salazar", "Cabrera", "Pizarro", "Loyola", "Araya"};
    private static final String [] Areas =  {"SAPU", "urgencia_adulto", "infantil"};

    private static int generarCategoria(){
        int random = ThreadLocalRandom.current().nextInt(100);
        if (random < 10) return 1;
        if (random < 25) return 2;
        if (random < 43) return 3;
        if (random < 70) return 4;
        return 5;
    }

    public static List<Paciente> generarListaPacientes(int cantidad, long timestampInicio){
        List<Paciente> pacientes = new ArrayList<>();

        int id = 1000;

        for(int i = 0; i < cantidad; i++){
            String nombre = Nombres [ThreadLocalRandom.current().nextInt(Nombres.length)];
            String apellido = Apellidos [ThreadLocalRandom.current().nextInt(Apellidos.length)];
            String ID = String.valueOf(++id);
            long llegada_i = timestampInicio + (i * 600);
            int Categoria = generarCategoria();
            String Area = Areas [ThreadLocalRandom.current().nextInt(Areas.length)];

            Paciente paciente = new Paciente (nombre, apellido, ID, Categoria, llegada_i, Area);
            pacientes.add (paciente);
        }
        return pacientes;
    }


}
