package com.prueba.nosolosoft.pojos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto para añadir información de error en la respuesta.
 * @author leandro.latorre
 *
 */
@XmlRootElement
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }

}
