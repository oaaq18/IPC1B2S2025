
package com.mycompany.practica01;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Practica01 {
    //vectores de pesonaje
    private static Scanner sc = new Scanner(System.in);
    private static String[] NombresPersonaje= new String[100];
    private static String[] ArmaDePersonaje= new String[100];
    private static String[][] HabilidadesDePersonaje = new String[100][5];
    private static int[] NivelePoder = new int[100];
    private static int ContadorPersonajes = 0;
    private static boolean[] personajesActivos = new boolean[100];
     
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
    
    private static String[] ListaArmas = {
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
            System.out.println("4. Ver Datos de un Personaje");
            System.out.println("5. Ver Listado de Personajes");
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
                    break;
                case 3:
                    // Eliminar personaje
                    break;
                case 4:
                    // Ver datos de un personaje
                    break;
                case 5:
                    // Ver listado de personajes
                    VerListadodePersonajes();
                    break;
                case 6:
                    // Realizar pelea entre personajes
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
    
    public static void agregarpresonaje(){
        String nombre;
        boolean ValidacionNombre = false;
        
        if (ContadorPersonajes >= 100) {
            System.out.println("Error: No se pueden agregar más personajes. Límite alcanzado.");
            return;
        }
       
        System.out.println("\n==== AGRAEGAR PERSONAJE ===");
        //Ingreso de nombre--------------------
        do{
            System.out.println("ingrese el nombre del personaj: ");
            nombre = sc.nextLine().trim();
            
           if (nombre.isEmpty()){
               System.out.println("el nombre no puede estar vacio");
               continue;
           }
           
           ValidacionNombre=true;//se valida hasta comprobar dubplicados
           //verificar que no exista
           for (int i=0;i<ContadorPersonajes;i++){
               if (personajesActivos[i] && nombre.equalsIgnoreCase(NombresPersonaje[i])){
                   System.out.println("Ya existe este nombre, itente con otro");
                   ValidacionNombre= false;
                   break;
               }
           }
        } while (!ValidacionNombre);
        
        //fin ingreso de nombre-------------------
        
        //inicio ingreso de arma------------------
        String Arma;
        int SeleccionArma;
        
        System.out.println("Sleccione un arma");
        for (int i=0;i<ListaArmas.length;i++){
            System.out.println(i+"." + ListaArmas[i]);
        }
        SeleccionArma = sc.nextInt();
        sc.nextLine(); // limpiar salto
        //fin ingreso de armas ---------------
        
        //ingreso de habilidades------
        String SeleccionHabilidad; //ingreso de habilidades
        int ContadorHabilidad=0; //control de cantidad de habilidades para cad personaje    
        boolean continuar=false;//verificador logico para continuar ingreando habilidades
       
        do{
            if(ContadorHabilidad>=5){
                System.out.println("limite de habilidades alcnazado");
                continuar=true;
            }else{
                System.out.println("ingrese la habilidad No. "+ContadorHabilidad+" dejar vacio para salir" );
                SeleccionHabilidad = sc.nextLine().trim();
                if(SeleccionHabilidad.isEmpty()){
                   continuar=true;
                }else{
                  HabilidadesDePersonaje[ContadorHabilidad][ContadorPersonajes]=SeleccionHabilidad;
                  ContadorHabilidad++;
                }
            }     
        }while(!continuar);
        
        //fin ingreso habilidad1es
        
        //inicio ingresar nivel
        int nivel=0;
        boolean validacionNivel=false;
        //sc.nextLine();
        
        do{
            System.out.println("ingrese el nivel de poder del personaje (0 a 100)");
             nivel = sc.nextInt();
             sc.nextLine();
        if(nivel<0 || nivel>100){
            System.out.println("nivel ingresado fuera de rango");
            validacionNivel=false;
        }else{
            validacionNivel=true;
        }
        
        }while(!validacionNivel);
        
        //final ingresar nivel
        //asignacion de datos al vector 
            NombresPersonaje[ContadorPersonajes]=nombre;
            ArmaDePersonaje[ContadorPersonajes]= ListaArmas[SeleccionArma];
            NivelePoder[ContadorPersonajes]= nivel;
            personajesActivos[ContadorPersonajes]=true;
            System.out.println("DEBUG -> Guardado: " 
             + NombresPersonaje[ContadorPersonajes] + ", " 
             + ArmaDePersonaje[ContadorPersonajes] + ", " 
             + NivelePoder[ContadorPersonajes]);
            ContadorPersonajes++;
            System.out.println("Personaje ingresado con exito");
            
        //fin asinacion
     }//finagregar personaje
    
    private static void VerListadodePersonajes(){
        for(int i=0;i<=ContadorPersonajes;i++){
            System.out.println("Personaje No."+i+"Nombre: "+NombresPersonaje[i]+" Aarma: "+ArmaDePersonaje[i]+" Habilidades: ");
        }
    }
    
    
    }
    
  
    
    
    

