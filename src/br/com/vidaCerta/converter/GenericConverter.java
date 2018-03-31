package br.com.vidaCerta.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vidaCerta.interfaces.IDAOFinder;
import br.com.vidaCerta.interfaces.IModelFinder;

@FacesConverter("genericConverter")
public class GenericConverter implements Converter {
	
	@Override
    public Object getAsObject(FacesContext contet, UIComponent component, String value) {
        if(value.equals(null) || value.equals("null") || value.equals(""))
            return null;
        try {
        	Map<String,Object> attrs = component.getAttributes();
  
        	String nomeClasse = "br.com.vidaCerta.model.DAO." + attrs.get("data-classe").toString();
        	
        	Class<?> clasz = Class.forName(nomeClasse);
        	IDAOFinder instancia = (IDAOFinder) clasz.newInstance();
        	
        	Object resultado = instancia.getById(Integer.valueOf(value));
            return resultado;
        } catch ( Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext contet, UIComponent component, Object value) {
        if(value==null || value.equals(""))
            return null;

        try {
        	return String.valueOf( ((IModelFinder)value).getId() );
        }catch(Exception e) {}
        
        return null;
    }
 
}

