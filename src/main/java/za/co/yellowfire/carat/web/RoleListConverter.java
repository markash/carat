package za.co.yellowfire.carat.web;

import org.omnifaces.converter.ListConverter;
import za.co.yellowfire.carat.db.Role;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("carat.RolesListConverter")
public class RoleListConverter extends ListConverter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new Role(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Role) {
            return ((Role) value).getName();
        }
        return value != null ? value.toString() : "null";
    }
}
