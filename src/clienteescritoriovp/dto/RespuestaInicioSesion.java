package clienteescritoriovp.dto;

import clienteescritoriovp.pojo.Profesor;


public class RespuestaInicioSesion {
    
    private boolean error;
    private String mensaje;
    private Profesor profesor;

    public RespuestaInicioSesion() {
    }

    public RespuestaInicioSesion(boolean error, String mensaje, Profesor profesor) {
        this.error = error;
        this.mensaje = mensaje;
        this.profesor = profesor;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
