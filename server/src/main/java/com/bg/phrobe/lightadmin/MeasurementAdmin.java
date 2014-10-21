package com.bg.phrobe.lightadmin;

import com.bg.phrobe.entities.Measurement;
import com.bg.phrobe.entities.Phone;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

/**
 * Created by bg on 2014.10.12..
 */
public class MeasurementAdmin extends AdministrationConfiguration<Measurement> {
    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("date")
                .singularName("Measurement")
                .pluralName("Measurements")
                .build();
    }
}
