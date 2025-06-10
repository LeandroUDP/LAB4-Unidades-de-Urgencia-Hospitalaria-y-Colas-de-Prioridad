import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class SimuladorUrgencia {
    private Hospital hospital;
    private List<Paciente> p24;
    private int pIngreso;
    private int pAtencion;
    private Map<Integer,Integer> pCategoria;
    private Map<Integer,Long> tiempoCategoria;
    private List<Paciente> pfueradetiempo;
    public SimuladorUrgencia(List<Paciente>p24) {
        this.p24 = p24;
        this.pIngreso = 0;
        this.pAtencion = 0;
        this.pCategoria = new HashMap<>();
        this.tiempoCategoria = new HashMap<>();
        this.pfueradetiempo = new ArrayList<>();
        this.hospital = new Hospital();
    }
    public void simular(){
        int m=0;
        int contador=0;
        while(m<24*60){
            if(m%10==0 && pIngreso<p24.size()){
                Paciente p = p24.get(pIngreso);
                hospital.registrarPaciente(p);
                pIngreso++;
                contador++;
            }
            if(m%15 == 0 && !hospital.getColaAtencion().isEmpty()){
                atenderPaciente(m);
                pAtencion++;
            }
            m++;
        }
        imprimirEstadisticasFinales();
    }
    private void atenderPaciente(long tiempo) {
        Paciente p = hospital.atenderSiguiente();
        if (p != null) {
            long tiempoEspera = (tiempo - (p.getTiempoLlegada() / 60));
            int categoria = p.getCategoria();


            if (p.getNombre().equals("Andrés") && p.getApellido().equals("Pérez")) {
                System.out.println("Segumiento... " + p.getNombre() + " " + p.getApellido() +
                        " fue atendido en el minuto " + tiempo +
                        " con espera de " + tiempoEspera + " min (Categoría C" + categoria + ")");
            }


            tiempoCategoria.put(categoria, tiempoCategoria.getOrDefault(categoria, 0L) + tiempoEspera);
            pCategoria.put(categoria, pCategoria.getOrDefault(categoria, 0) + 1);

            if (tiempoEspera > getTiempoMpermitido(categoria)) {
                pfueradetiempo.add(p);
            }
        }
    }

    private int getTiempoMpermitido(int categoria){
        switch(categoria){
            case 1: return 0;
            case 2: return 30;
            case 3: return 90;
            case 4: return 180;
            case 5: return Integer.MAX_VALUE;
            default: return Integer.MAX_VALUE;

        }
    }
    private void imprimirEstadisticasFinales(){
        System.out.println("\n Estadisticas finales  ");
        System.out.println("Total de pacientes atendidos: "+pAtencion);
        for(int categoria:pCategoria.keySet()){
            int cuantos = pCategoria.get(categoria);
            long totaldetiempo = tiempoCategoria.getOrDefault(categoria,0L);
            long promedio = 0;
            if(cuantos !=0){
                promedio = totaldetiempo/cuantos;
            }
            System.out.println("Categoria C"+categoria+": atendidos = "+cuantos+", promedio de espera = "+ promedio+" minutos");

        }
        System.out.println("\n Pacientes que excedieron el tiempo de espera: ");
        for(Paciente p:pfueradetiempo){
            System.out.println("- "+p.getNombre()+" "+p.getApellido()+" (Categoria: "+p.getCategoria()+")");

        }
    }
    public Map<Integer, Double> obtenerPromediosPorCategoria() {
        Map<Integer, Double> promedios = new HashMap<>();
        for (int cat : pCategoria.keySet()) {
            int cantidad = pCategoria.get(cat);
            long total = tiempoCategoria.getOrDefault(cat, 0L);
            double promedio = cantidad == 0 ? 0 : (double) total / cantidad;
            promedios.put(cat, promedio);
        }
        return promedios;
    }



}