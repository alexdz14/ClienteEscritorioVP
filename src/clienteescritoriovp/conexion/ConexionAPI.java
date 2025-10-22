package clienteescritoriovp.conexion;

import clienteescritoriovp.pojo.RespuestaHTTP;


public class ConexionAPI {

    public static RespuestaHTTP peticionGET(String URL){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
        URL urlWS = new URL(URL);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlWS.openConnection();
        int codigo = conexionHTTP.getResponseCode();
        if(codigo == HttpURLConnection.HTTP_OK){
        respuesta.setContenido(
        Utilidades.streamToString(conexionHTTP.getInputStream()));
        }
        respuesta.setCodigo(codigo);
        } catch (MalformedURLException e) {
        respuesta.setCodigo(Constantes.ERROR_MALFORMED_URL);
        respuesta.setContenido(e.getMessage());
        } catch (IOException ex){
        respuesta.setCodigo(Constantes.ERROR_PETICION);
        respuesta.setContenido(ex.getMessage());
        }
        return respuesta;
        }

    public static RespuestaHTTP peticionBody(String URL, String metodoHTTP,
        String parametros, String contentType){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
        URL urlWS = new URL(URL);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlWS.openConnection();
        conexionHTTP.setRequestMethod(metodoHTTP);
        conexionHTTP.setRequestProperty("Content-Type", contentType);
        conexionHTTP.setDoOutput(true);
        OutputStream os = conexionHTTP.getOutputStream();
        os.write(parametros.getBytes());
        os.flush();
        os.close();
        int codigo = conexionHTTP.getResponseCode();
        if(codigo == HttpURLConnection.HTTP_OK){
        respuesta.setContenido(
        Utilidades.streamToString(conexionHTTP.getInputStream()));
        }
        respuesta.setCodigo(codigo);
        } catch (MalformedURLException e) {
        respuesta.setCodigo(Constantes.ERROR_MALFORMED_URL);
        respuesta.setContenido(e.getMessage());
        } catch (IOException ex){
        respuesta.setCodigo(Constantes.ERROR_PETICION);
        respuesta.setContenido(ex.getMessage());
        }
        return respuesta;
        }
}
