/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Gerson
 */
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import javax.swing.*;



public class compiladores {

    static Vector<String> listado = new Vector<String>();
    
/*static String muestraContenido(String archivo) throws FileNotFoundException, IOException { //funcion para mostar datos cargados
      String cadena,palabra;
      palabra="";
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      //System.out.println(cadena = b.readLine());
      while((cadena = b.readLine())!=null) {
          
      palabra+=cadena;
      v.add(cadena);
      }
       return palabra;
}*/

/*************************************************************************/
JFileChooser seleccionar = new JFileChooser();
File archivo;
FileInputStream entrada;
FileOutputStream salida;

/*************************************************************************/


public String AbrirArchivo(File archivo){//funcion para busqueda de archivo txt
        String documento="";
        try {
           
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci=entrada.read())!=-1) {
                char caracter=(char)ascci;
                documento+=caracter;
                
            }
        } catch (Exception e) {
        }
  return documento;
    }
    
    public String Guardartxt(File archivo, String documento){//funcion para guardado del txt
    String mensaje=null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt=documento.getBytes();
            salida.write(bytxt);
            mensaje="Archivo guardado";
        } catch (Exception e) {
        }
    return mensaje;
    }
 
public String abrir(){//funcion para verificar txt y cargar en jtexarea
 String documento="";
       if(seleccionar.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
       archivo=seleccionar.getSelectedFile();
       if(archivo.canRead()){
       if(archivo.getName().endsWith("txt")){
           Compilador.dirNuevo=archivo.getAbsolutePath();
           //JOptionPane.showMessageDialog(null, archivo.getAbsolutePath());
        documento= AbrirArchivo(archivo);
      // jTextArea1.setText(documento);
       //System.out.println(archivo.getAbsoluteFile());
      // palabra=jTextArea1.getText();
           //System.out.print(palabra);
       }else{
       JOptionPane.showMessageDialog(null, "Archivo no compatible");
       }
       }
       
       }
       return documento;
}


      
public void guardar(String documento){//funcion para guaradar txt contenido en jtexarea
    String documentos="";
    if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
        archivo=seleccionar.getSelectedFile();
        if(archivo.getName().endsWith("txt")){
       documentos=documento;
        String mensaje=Guardartxt(archivo, documentos);
        if(mensaje!=null){
        JOptionPane.showMessageDialog(null, mensaje);
        }else{
        JOptionPane.showConfirmDialog(null, "Archivo no colmpatible");
        }
        }else{
        JOptionPane.showConfirmDialog(null, "Guardar documento de texto");
        }
        
        }
}
//******************************************************************************///analizador lexico

public void Alexico(String P_Evaluar){

        String patron ="(while|if|else|foreach|bool|int|double|char|string|do|Array|While|for|class|public|static|vacio|String|args|System|out|printf)|([_a-z_A-Z|]+)|([>|<|=>|+|*|%]+)|([0-9]+)|([(|)]+)|([{|}]+)|(\\[|\\]+)|([;|.])|(\\\\t|\\\\n|\\\\s|\\\\|\\\")";
        Pattern p = Pattern.compile(patron);
    Matcher matcher = p.matcher(P_Evaluar);
    String variables="", palabrasReser  ="", Operadores="",Numeros="",parentesis="",llaves="",puntoycoma="", corchetes="",SecuenciaEspcape="";
    //System.out.println("Guia 2 compiladores");
    int i=1;
        while (matcher.find()) {
           
            String tokenTipo1 = matcher.group(1);
            if(tokenTipo1 !=null){
                 palabrasReser+=tokenTipo1+"<->"+i+"\n";
                listado.add("Palabra reservada");
                listado.add(tokenTipo1);
               i++;
                //System.out.println("Palabra reservada: "+tokenTipo1);
            }
            
            String tokenTipo2 = matcher.group(2);
            if(tokenTipo2 !=null){
                 variables+=tokenTipo2+"\n";
                 listado.add("Variable");
                listado.add(tokenTipo2);
           // System.out.println("Varibles: "+tokenTipo2);
            }
            
            String tokenTipo3 = matcher.group(3);
            if(tokenTipo3 != null){
                  if(tokenTipo3.equals(">")||tokenTipo3.equals("<")||tokenTipo3.equals("<=")||tokenTipo3.equals("!=")||tokenTipo3.equals("==")||tokenTipo3.equals(">=")){
                  Operadores+=tokenTipo3+": Relacional \n";
                  listado.add("Relacion");
                listado.add(tokenTipo3);
                  }
            
                    if(tokenTipo3.equals("+")||tokenTipo3.equals("-")||tokenTipo3.equals("*")||tokenTipo3.equals("/")||tokenTipo3.equals("++")||tokenTipo3.equals("=")||tokenTipo3.equals("%")){
                  Operadores+=tokenTipo3+": Aritmetico \n";
                    listado.add("Aritmetico");
                listado.add(tokenTipo3);
                    }
                    
        
                    if(tokenTipo3.equals("||")||tokenTipo3.equals("!")||tokenTipo3.equals("&&")){
                  Operadores+=tokenTipo3+": Logico \n";
                    listado.add("Logico");
                listado.add(tokenTipo3);
                    }
                  
             //   System.out.println("Operador: "+tokenTipo3);
            }
            
            String tokenTipo4 = matcher.group(4);
            if(tokenTipo4 != null){
                 Numeros+=tokenTipo4+"\n";
                 listado.add("Numeros");
                listado.add(tokenTipo4);
                //System.out.println("Numeros: "+tokenTipo4);
            }
            
            String tokenTipo5 = matcher.group(5);
            if(tokenTipo5 != null){
                 parentesis+=tokenTipo5+"\n";
                 listado.add("Agrupacion");
                listado.add(tokenTipo5);
                //System.out.println("Operador: "+tokenTipo5);
            }
            
            String tokenTipo6 = matcher.group(6);
            if(tokenTipo6 != null){
                 llaves+=tokenTipo6+"\n";
                 listado.add("Agrupacion");
                listado.add(tokenTipo6);
                //System.out.println("LLaves: "+tokenTipo6);
            }
            
            String tokenTipo7 = matcher.group(7);
            if(tokenTipo7 != null){
                corchetes +=tokenTipo7+"\n";
                listado.add("Agrupacion");
                listado.add(tokenTipo7);
                //System.out.println("Punto y coma: "+tokenTipo7);
            }
            
            String tokenTipo8 = matcher.group(8);
            if(tokenTipo8 != null){
                 puntoycoma+=tokenTipo8+"\n";
                 listado.add("Puntuador");
                listado.add(tokenTipo8);
                //System.out.println("Corchete: "+tokenTipo8);
            } 
            String tokenTipo9 = matcher.group(9);
            if(tokenTipo9 != null){
                
                    listado.add("Secuencia de escape");
                  if(tokenTipo9.equals("\\t")){
                  SecuenciaEspcape+=tokenTipo9+": Tabulador \n";
                listado.add(tokenTipo9);
                  }
            if(tokenTipo9.equals("\\s")){
                  SecuenciaEspcape+=tokenTipo9+": Espacio en blano \n";
             //listado.add("Secuencia de escape");
                listado.add(tokenTipo9);}
            if(tokenTipo9.equals("\\n")){
                  SecuenciaEspcape+=tokenTipo9+": Nueva linea \n";
            // listado.add("Secuencia de escape");
                listado.add(tokenTipo9);}
            if(tokenTipo9.equals("\\")){
                  SecuenciaEspcape+=tokenTipo9+": Barra inversa \n";
             //listado.add("Secuencia de escape");
                listado.add(tokenTipo9);}
            if(tokenTipo9.equals("\"")){
                  SecuenciaEspcape+=tokenTipo9+": Comillas dobles \n";
            // listado.add("Secuencia de escape");
                listado.add(tokenTipo9);}      
                  
             //   System.out.println("Operador: "+tokenTipo3);
            }
        }
       /* System.out.println("***Palabras Reservadas****");
        System.out.println(palabrasReser);
        System.out.println("****Variables:****");
        System.out.println(variables);
        System.out.println("****Operadores:****");
        System.out.println(Operadores);
        System.out.println("****Numeros:****");
        System.out.println(Numeros);
        System.out.println("****Parentesis:****");
        System.out.println(parentesis);
        System.out.println("****llaves:****");
        System.out.println(llaves);
        System.out.println("****Punto y Coma:****");
        System.out.println(puntoycoma);
        System.out.println("****Corchetes:****");
        System.out.println(corchetes);
        System.out.println("****Secuencias de Escape:****");
        System.out.println(SecuenciaEspcape);*/
}
}//fin clase compiladores