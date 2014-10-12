package com.bg.phrobe.lightadmin;

import com.bg.phrobe.entities.User;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

/**
 * Created by bg on 2014.10.12..
 */
public class UserAdmin extends AdministrationConfiguration<User> {
    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("username")
                .singularName("User")
                .pluralName("Users")
                .build();
    }
}
