package clienteescritoriovp.utilidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Utilidades {
    
    public static String streamToString(InputStream input) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String inputLine;
        StringBuffer respuestaEntrada = new StringBuffer();
        while( (inputLine = in.readLine()) != null){
         respuestaEntrada.append(inputLine);
        }
        in.close();
        return respuestaEntrada.toString();
       }


}
