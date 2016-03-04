package org.openmrs.module.aijar.api.deploy.bundle;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.aijar.metadata.core.PatientIdentifierTypes;
import org.openmrs.module.aijar.metadata.core.PersonAttributeTypes;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

/**
 * Installs the most common metadata
 * <p/>
 * Created by ssmusoke on 06/01/2016.
 */
@Component
public class CommonMetadataBundle extends AbstractMetadataBundle {

    /**
     * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
     */
    public void install() throws Exception {
        // install the patient identifier types
        log.info("Installing PatientIdentifierTypes");
        // add a check if the HIV care number already exists do not install it
        PatientIdentifierType hivCareNumber = MetadataUtils.possible(PatientIdentifierType.class, PatientIdentifierTypes
                .HIV_CARE_NUMBER.uuid());
        if (hivCareNumber == null) {
            install(PatientIdentifierTypes.HIV_CARE_NUMBER);
        }

        // add a check not to overwrite the district TB number if it exists
        PatientIdentifierType tbNumber = MetadataUtils.possible(PatientIdentifierType.class, PatientIdentifierTypes
                .DISTRICT_TB_NUMBER.uuid());
        if (tbNumber == null) {
            install(PatientIdentifierTypes.DISTRICT_TB_NUMBER);
        }
        install(PatientIdentifierTypes.OLD_OPENMRS_IDENTIFICATION_NUMBER);
        install(PatientIdentifierTypes.OPENMRS_ID);
        install(PatientIdentifierTypes.OPENMRS_IDENTIFICATION_NUMBER);
        install(PatientIdentifierTypes.EXPOSED_INFANT_NUMBER);
        install(PatientIdentifierTypes.ANC_NUMBER);
        install(PatientIdentifierTypes.IPD_NUMBER);
        install(PatientIdentifierTypes.HCT_NUMBER);
        log.info("Patient IdentifierTypes installed");

        // install person attribute types
        log.info("Installing PatientAttributeTypes");
        install(PersonAttributeTypes.MARITAL_STATUS);
        install(PersonAttributeTypes.HEALTH_CENTER);
        install(PersonAttributeTypes.HEALTH_FACILITY_DISTRICT);
        log.info("Person AttributeTypes installed");
    }
}
