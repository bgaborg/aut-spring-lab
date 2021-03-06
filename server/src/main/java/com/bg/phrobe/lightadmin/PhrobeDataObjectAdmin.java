package com.bg.phrobe.lightadmin;

import com.bg.phrobe.entities.Phone;
import com.bg.phrobe.entities.PhrobeDataObject;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

/**
 * Created by bg on 2014.10.12..
 */
public class PhrobeDataObjectAdmin extends AdministrationConfiguration<PhrobeDataObject> {
    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("apiKey")
                .singularName("PData")
                .pluralName("PDatas")
                .build();
    }
}
