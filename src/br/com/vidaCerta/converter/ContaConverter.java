package br.com.vidaCerta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vidaCerta.model.DAO.ContaDAO;
import br.com.vidaCerta.model.entity.Conta;

@FacesConverter("contaConverter")
public class ContaConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext contet, UIComponent component, String value) {
        if(value.equals(null) || value.equals("null") || value.equals(""))
            return null;
        try{
        	ContaDAO contaDAO = new ContaDAO();
        	Conta resultado = contaDAO.buscarContaById(Integer.valueOf(value));
            return resultado;
        }catch (Exception e) {
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext contet, UIComponent component, Object value) {
        if(value==null || value.equals(""))
            return null;
             return String.valueOf(((Conta)value).getIdConta());
    }

}

