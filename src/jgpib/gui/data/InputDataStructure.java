package jgpib.gui.data;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class InputDataStructure implements PropertySheet.Item {
	
	public enum SomeEnum {
		ALPHA,
		BETA,
		GAMMA
	}
	
    public static Map<String, Object> customDataMap = new LinkedHashMap<>();
    static {
        customDataMap.put("basic.My Text", "Some text"); // Creates a TextField in property sheet
        customDataMap.put("basic.My Date", LocalDate.of(2016, Month.JANUARY, 1)); // Creates a DatePicker
        customDataMap.put("misc.My Enum", SomeEnum.ALPHA); // Creates a ChoiceBox
        customDataMap.put("misc.My Boolean", false); // Creates a CheckBox
        customDataMap.put("misc.My Number", 500); // Creates a NumericField
        customDataMap.put("misc.My Color", Color.ALICEBLUE); // Creates a ColorPicker
    }

    private String key;
    private String category, name;

    public InputDataStructure(String key) 
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
        // doesn't really fit into the map
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
        // for an item of type number, specify the type of editor to use
//        if (Number.class.isAssignableFrom(getType())) return Optional.of();

        // ... return other editors for other types

        return Optional.empty();
    }
}
