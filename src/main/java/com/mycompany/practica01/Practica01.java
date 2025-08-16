
package com.mycompany.practica01;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Practica01 {
    //vectores de pesonaje
    private static Scanner sc = new Scanner(System.in);
    private static String[] NombresPersonaje= new String[100]; //almacendel nombrede personaje
    private static String[] ArmaDePersonaje= new String[100];  //almacena el arma del personaje 1 de 5 opciones
    private static String[][] HabilidadesDePersonaje = new String[100][5];//almacena las habilidades ingresadas de 0 hasta un maximo de 5
    private static int[] NivelePoder = new int[100];//almacena el nivel del personaje 1-100
    private static int ContadorPersonajes = 0;//verifica cuantos personajes existen
    private static boolean[] personajesActivos = new boolean[100];//verifica que la la posicion i del vector este logamente activado, se activa al ingresar uno nuevo y se desactiva al eliminar
    //vectores de registro de pelea 
    private static boolean PeleasActivas[]= new boolean[500];//verifica haya historial de peleas
    private static int ContadorPelea=0;            // almacena el id/numero de la pelea
     private static int[] IDPeleador1 = new int[500]; // IDs de personajes que pelean
    private static int[] IDPeleador2 = new int[500];      //almacena los ids de los personajes involucrados en la pelea
    private static String[] PeleadorGanador= new String[500];         //almacena el id del peleador ganador
    private static String[] fechasPeleas = new String[500]; //almacena la fcha y hora de las peleas
    
   /*
    private static String[] ListaHabilidades ={
    "Fuerza",       //0
    "Resistencia",  //1
    "Rapidez",      //2
    "Inteligencia", //3
    "precisión",    //4
    "Regeneracion", //5
    "Espinas"       //6
    
    };
    /*/
    
    private static String[] ListaArmas = {//lista de armas disponibles por agregar
    "Espada de obsidiana ", //0
    "Arco de fuego",        //1
    "Lanza",                //2
    "Baston de hielo",      //3     
    "Ballesta",             //4
    "Martillo de lava",     //5 
    "Lanza electrica"       //6
     
};
    
    public static void main(String[] args) {
        Menu();
    }
    
    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Agregar Personaje");
            System.out.println("2. Modificar Personaje");
            System.out.println("3. Eliminar Personaje");
            System.out.println("4. Buscar un Personaje");
            System.out.println("5. Ver Listado de todos Personajes");
            System.out.println("6. Realizar Pelea entre Personajes");
            System.out.println("7. Ver Historial de Peleas");
            System.out.println("8. Ver Datos de Estudiante");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    agregarpresonaje();
                    break;
                case 2:
                    // Modificar personaje
                    modificarpersonaje();
                    break;
                case 3:
                    eliminarpersonaje();
                    // Eliminar personaje
                    break;
                case 4:
                    // buscar un personaje
                    BuscarPersonaje();
                    break;
                case 5:
                    // Ver listado de personajes
                    VerListadodePersonajes();
                    break;
                case 6:
                    // Realizar pelea entre personajes
                    Realizarpeleadepersonajes();
                    break;
                case 7:
                    // Ver historial de peleas
                    break;
                case 8:
                    // Ver datos de estudiante
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 9);

       
    } 
    
public static void agregarpresonaje() {
     String nombre;
     boolean ValidacionNombre = false;

    if (ContadorPersonajes >= 100) {
        System.out.println("Error: No se pueden agregar más personajes. Límite alcanzado.");
        return;
     }

    System.out.println("\n==== AGREGAR PERSONAJE ===");

    // Ingreso de nombre --------------------
    do {
        System.out.println("Ingrese el nombre del personaje: ");
        nombre = sc.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
            continue;
        }

        ValidacionNombre = true; // se valida hasta comprobar duplicados

        // Verificar que no exista
        for (int i = 0; i < ContadorPersonajes; i++) {
            if (personajesActivos[i] && nombre.equalsIgnoreCase(NombresPersonaje[i])) {
                System.out.println("Ya existe este nombre, intente con otro");
                ValidacionNombre = false;
                break;
            }
        }
    } while (!ValidacionNombre);
    // Fin ingreso de nombre -------------------

    // Inicio ingreso de arma ------------------
    String Arma;
    int SeleccionArma;//variable que selecciona el id del arma en listaarmas[]

    System.out.println("Seleccione un arma");
    for (int i = 0; i < ListaArmas.length; i++) {
        System.out.println(i + ". " + ListaArmas[i]);
    }
    SeleccionArma = sc.nextInt();
    if(SeleccionArma<0 || SeleccionArma>6){
        System.out.println("ingrese una opcion valida");        
    }
    sc.nextLine(); // limpiar salto
    // Fin ingreso de armas -------------------

    // Ingreso de habilidades -----------------
    String SeleccionHabilidad;
    int ContadorHabilidad = 0; // control de cantidad de habilidades para cada personaje    
    boolean continuar = false; // verificador lógico para continuar ingresando habilidades

    do {
        if (ContadorHabilidad >= 5) {
            System.out.println("Límite de habilidades alcanzado");
            continuar = true;
        } else {
            System.out.println("Ingrese la habilidad No. " + ContadorHabilidad + " (dejar vacío para salir):");
            SeleccionHabilidad = sc.nextLine().trim();
            if (SeleccionHabilidad.isEmpty()) {
                continuar = true;
            } else {
                HabilidadesDePersonaje[ContadorHabilidad][ContadorPersonajes] = SeleccionHabilidad;
                ContadorHabilidad++;
            }
        }
    } while (!continuar);
    // Fin ingreso habilidades ----------------

    // Inicio ingresar nivel ------------------
    int nivel = 0;
    boolean validacionNivel = false;

    do {
        System.out.println("Ingrese el nivel de poder del personaje (0 a 100):");
        nivel = sc.nextInt();
        sc.nextLine();
        if (nivel < 0 || nivel > 100) {
            System.out.println("Nivel ingresado fuera de rango");
            validacionNivel = false;
        } else {
            validacionNivel = true;
        }
    } while (!validacionNivel);
    // Final ingresar nivel -------------------

    // Asignación de datos al vector
    NombresPersonaje[ContadorPersonajes] = nombre;
    ArmaDePersonaje[ContadorPersonajes] = ListaArmas[SeleccionArma];
    NivelePoder[ContadorPersonajes] = nivel;
    personajesActivos[ContadorPersonajes] = true;

    System.out.println("DEBUG -> Guardado: " 
        + NombresPersonaje[ContadorPersonajes] + ", " 
        + ArmaDePersonaje[ContadorPersonajes] + ", " 
        + NivelePoder[ContadorPersonajes]);

    ContadorPersonajes++;
    System.out.println("Personaje ingresado con éxito");
    // Fin asignación -------------------------
} // fin agregar personaje
private static void modificarpersonaje(){// op 2 modificar personaje
     int BusquedaModificar;
     boolean verifcarbusqueda=false;
     String ModificarNombre;
     int op;
        if (ContadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
       System.out.println("Ingrese el id del personaje");
        BusquedaModificar= sc.nextInt();
       
       if(personajesActivos[BusquedaModificar]=false){
           System.out.println("El personaje no existe o fue eliminado");
           return;
       }else{
           System.out.println("--Personaje econtrado--/n");
           System.out.println("ID: "+ BusquedaModificar+" Nombre: "+
                                "|Nombre:"+NombresPersonaje[BusquedaModificar]+
                                "|Arma:"+ArmaDePersonaje[BusquedaModificar]+
                                "|Nivel de poder: "+NivelePoder[BusquedaModificar]);
            for(int j=0;j<HabilidadesDePersonaje[j].length;j++){ // for de ciclo habilidades de personaje en posicion i
                if(HabilidadesDePersonaje[j][BusquedaModificar]!=null){// ingora los epacios vacios
                    System.out.println("| Habilidad: "+ HabilidadesDePersonaje[j][BusquedaModificar]); 
                } 
            }
          
           
       }
       
   
   }//fin op 2 modificar
private static void eliminarpersonaje(){// op 3 eliminar personaje
   int BusquedaEliminar;
        if (ContadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("Ingrese el id del personaje a eliminar");
        BusquedaEliminar= sc.nextInt();
        if(personajesActivos[BusquedaEliminar]=false){
           System.out.println("El personaje no existe o ya fue eliminado");
           return;
       }else{
           System.out.println("--Personaje econtrado--/n");
           System.out.println("ID: "+ BusquedaEliminar+" Nombre: "+
                                "|Nombre:"+NombresPersonaje[BusquedaEliminar]+
                                "|Arma:"+ArmaDePersonaje[BusquedaEliminar]+
                                "|Nivel de poder: "+NivelePoder[BusquedaEliminar]);
            for(int j=0;j<HabilidadesDePersonaje[j].length;j++){ // for de ciclo habilidades de personaje en posicion i
                if(HabilidadesDePersonaje[j][BusquedaEliminar]!=null){// ingora los epacios vacios
                    System.out.println("| Habilidad: "+ HabilidadesDePersonaje[j][BusquedaEliminar]); 
                } 
            }
            ///proceso de eliminacion
           personajesActivos[BusquedaEliminar]=false;
           NombresPersonaje[BusquedaEliminar]=null;
           ArmaDePersonaje[BusquedaEliminar]=null;
           NivelePoder[BusquedaEliminar]=0;
           for(int i=0;i<=5;i++){
               HabilidadesDePersonaje[i][BusquedaEliminar]=null;
           }
           ContadorPersonajes--;
           System.out.println("Personaje eliminado con exito");
           //finrpocesoeliminar
           
           
       }
        
   }// fin op 3 eliminar personaje
private static void BuscarPersonaje(){//inicio buscar personaje
   int Busqueda;
   int op;
        if (ContadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
       System.out.println("Ingrese el id del personaje");
        Busqueda= sc.nextInt();
       
       if(personajesActivos[Busqueda]=false){
           System.out.println("El personaje no existe o fue eliminado");
           return;
       }else{
           System.out.println("--Personaje econtrado--/n");
           System.out.println("ID: "+ Busqueda+" Nombre: "+
                                "|Nombre:"+NombresPersonaje[Busqueda]+
                                "|Arma:"+ArmaDePersonaje[Busqueda]+
                                "|Nivel de poder: "+NivelePoder[Busqueda]);
            for(int j=0;j<HabilidadesDePersonaje[j].length;j++){ // for de ciclo habilidades de personaje en posicion i
                if(HabilidadesDePersonaje[j][Busqueda]!=null){// ingora los epacios vacios
                    System.out.println("| Habilidad: "+ HabilidadesDePersonaje[j][Busqueda]); 
                } 
            }
       }
   }// op 4 buscar personaje
private static void Realizarpeleadepersonajes(){// op 6 realizar pelea
    LocalDateTime ahora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    int IDp1;//almacena el id del peleador 1
    int IDp2;//almacena el id del pleador 2
    //String Ganador;//almacena temporalmente el nombre del jugador ganador
    //verificaciones  que haya espacio para peleas y que hayan jugadores
    if (ContadorPelea >= 500) {
        System.out.println("Error: No se pueden agregar más peleas Límite alcanzado.");
        return;
    }
    if(ContadorPersonajes==0){
        System.out.println("Error: No hay peleadores ingresados");
        return;
    }
    if(ContadorPersonajes<=1){
        System.out.println("Error: no hay mas de dos peleadores ingresados");
        return;
    }
    System.out.println("Ingrese el id del peleador N1");
    IDp1= sc.nextInt();
    System.out.println("Ingrese el id del peleador N2");
    IDp2= sc.nextInt();
    //verificacion
    if(personajesActivos[IDp1]==false){
        System.out.println("ID no econtrado, el personaje no existe o ya fue eliminado");
        return;
    }
    
    if(personajesActivos[IDp2]==false){
        System.out.println("ID no econtrado, el personaje no existe o ya fue eliminado");
        return;
    }
    if(IDp1==IDp2){
        System.out.println("Error: el peleador no puede pelear contra si mismo");
        return;
    }
    //fin de verificaciones
    if(NivelePoder[IDp1]>NivelePoder[IDp2]){
        System.out.println("Peleador 1:"+NombresPersonaje[IDp1]+" ha sido el Ganador" );
        PeleadorGanador[ContadorPelea]=NombresPersonaje[IDp1];
        IDPeleador1[ContadorPelea]=IDp1;
        IDPeleador2[ContadorPelea]=IDp2;
        PeleasActivas[ContadorPelea]=true;
        ContadorPelea++;
        return;
    }else if(NivelePoder[IDp1]<NivelePoder[IDp2]){
        System.out.println("Peleador 2:"+NombresPersonaje[IDp1]+" ha sido el Ganador" );
        PeleadorGanador[ContadorPelea]=NombresPersonaje[IDp1];
        IDPeleador1[ContadorPelea]=IDp1;
        IDPeleador2[ContadorPelea]=IDp2;
        fechasPeleas[ContadorPelea]=ahora.format(formato);
        PeleasActivas[ContadorPelea]=true;
        ContadorPelea++;
        return;
    }else{
        System.out.println("Ha sido un empate");
        PeleadorGanador[ContadorPelea]="EMPATE";
        IDPeleador1[ContadorPelea]=IDp1;
        IDPeleador2[ContadorPelea]=IDp2;
        fechasPeleas[ContadorPelea]=ahora.format(formato);
        PeleasActivas[ContadorPelea]=true;
        ContadorPelea++;
    }
    
    
}// fin realizar pelea
private static void VerListadodePersonajes(){ // op 5 ver lista de todos los personajes
        if (ContadorPersonajes == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        for(int i=0;i<ContadorPersonajes;i++){ //for de cliclo de personajes
            
            if (personajesActivos[i] = false) {//verifica si hay espacios con personajes eliminados
                    System.out.println("PERSONAJE ELIMINADO");
            }else{
            System.out.println("| ID de personaje:"+i+
                    "| Nombre: "+NombresPersonaje[i]+
                    "| Arma: "+ArmaDePersonaje[i]+
                    "| Nivel de poder: "+NivelePoder[i]);
            for(int j=0;j<HabilidadesDePersonaje[j].length;j++){ // for de ciclo habilidades de personaje en posicion i
                if(HabilidadesDePersonaje[j][i]!=null){// ingora los epacios vacios
                    System.out.println("| Habilidad: "+ HabilidadesDePersonaje[j][i]); 
                }
                
            }
            
            System.out.println("-----------------------------------------------------------------");
        }
        }
    }//fin op 5
}
    
  
    
    
    

