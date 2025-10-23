package clienteescritoriovp.dominio;

import clienteescritoriovp.conexion.ConexionAPI;
import clienteescritoriovp.dto.RespuestaInicioSesion;
import clienteescritoriovp.pojo.RespuestaHTTP;
import clienteescritoriovp.utilidad.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

public class InicioSesionImp {
    
    public static RespuestaInicioSesion verificarCredenciales(String noPersonal, String password){
        RespuestaInicioSesion respuesta = new RespuestaInicioSesion();
        String URL = Constantes.URL_WS + "autenticacion/administracion";
        String parametros = "noPersonal="+noPersonal+"&password="+password;
        RespuestaHTTP respuestaAPI = 
                ConexionAPI.peticionBody(URL, Constantes.METODO_POST, parametros, Constantes.CONTENT_FORM);
        if(respuestaAPI.getCodigo() == HttpURLConnection.HTTP_OK){
            //Crear Objeto
            try{
                Gson gson = new Gson();
                respuesta = gson.fromJson(respuestaAPI.getContenido(), RespuestaInicioSesion.class);
            }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            //ERROR en peticion
            respuesta.setError(true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_MALFORMED_URL:
                        respuesta.setMensaje("Lo sentimos hubo un error al intentar conectar con el servicio.");
                    break;
                case Constantes.ERROR_PETICION:
                        respuesta.setMensaje("Lo sentimos hubo un error al leer la información.");
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                        respuesta.setMensaje("Lo sentimos la información no tiene el formato correcto.");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hubo un error con la respuesta del servicio.");

            }
        }
        return respuesta;
    }
    
}

