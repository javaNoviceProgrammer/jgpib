package jgpib.gui.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import javafx.beans.value.ObservableValue;

public class Agilent34401APropertySheet implements PropertySheet.Item {
	
    public static Map<String, Object> customDataMap = new LinkedHashMap<>();
    static {
        customDataMap.put("GPIB.Bus", "0"); // Creates a TextField in property sheet
        customDataMap.put("GPIB.Address", "22"); // Creates a TextField in property sheet
        
        customDataMap.put("Voltage.Max Value (V)", "10"); // Creates a TextField in property sheet
        customDataMap.put("Voltage.Resolution (V)", "0.0001"); // Creates a TextField in property sheet
    }

    private String key;
    private String category, name;

    public Agilent34401APropertySheet(String key) 
    {
        this.key = key;
        String[] skey = key.split("\\.", 2);
        category = skey[0];
        name = skey[1];
    }

    @Override
    public Class<?> getType() {
        return customDataMap.get(key).getClass();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Object getValue() {
        return customDataMap.get(key);
    }

    @Override
    public void setValue(Object value) {
        customDataMap.put(key, value);
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.empty();
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.empty();
    }

}