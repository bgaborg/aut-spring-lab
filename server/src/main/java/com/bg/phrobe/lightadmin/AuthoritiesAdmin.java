package com.bg.phrobe.lightadmin;

import com.bg.phrobe.entities.Authorities;
import com.bg.phrobe.entities.Users;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

/**
 * Created by bg on 2014.10.12..
 */
public class AuthoritiesAdmin extends AdministrationConfiguration<Authorities> {
    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("authority")
                .singularName("Authority")
                .pluralName("Authorities")
                .build();
    }
}
