/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jose J
 */
@FacesValidator("valTel")
public class ValidadorTelefono implements Validator<String>{

    @Override
    public void validate(FacesContext fc, UIComponent uic, String valor) throws ValidatorException {
      if(valor != null && !valor.startsWith("0")){
          FacesMessage mensajeErr = new FacesMessage("Teléfono incorrecto");
          mensajeErr.setSeverity(FacesMessage.SEVERITY_ERROR);
          throw new ValidatorException(mensajeErr);
      }
    }
    
}
