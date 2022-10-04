package stepdefinitions;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.TableEntryTransformer;
import io.cucumber.datatable.DataTableType;
import java.util.Locale;

import common.Base_Classes;

public class DataTableConfigurer extends Base_Classes implements TypeRegistryConfigurer {

    public DataTableConfigurer(){
        super(false);
    }
    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {

        registry.defineDataTableType(new DataTableType(FileDetails.class, (TableEntryTransformer<FileDetails>)
                entry -> new FileDetails(entry.get("file_name"),entry.get("file_type"),entry.get("sheet_name"))));
    }
}
