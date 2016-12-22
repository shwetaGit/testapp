package com.app.server.businessservice.organization.contactmanagement;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CoreContactsBusinessServiceImpl implements CoreContactsBusinessService {

    @Autowired
    private CoreContactsRepository coreContactsRepository;

    /**
     * Update the <CoreContacts> object
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Override
    public void update(CoreContacts entity) throws Exception {
        if (entity.isHardDelete()) {
            coreContactsRepository.delete(entity.getContactId());
        } else {
            coreContactsRepository.deleteAddress(entity.getDeletedAddressList());
            coreContactsRepository.update(entity);
        }
    }

    /**
     * Update the list of <CoreContacts> object
     * @Params List of CoreContacts Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<CoreContacts> entity) throws Exception {
        for (CoreContacts corecontacts : entity) {
            if (corecontacts.isHardDelete()) {
                coreContactsRepository.delete(corecontacts.getContactId());
            } else {
                coreContactsRepository.deleteAddress(corecontacts.getDeletedAddressList());
                coreContactsRepository.update(corecontacts);
            }
        }
    }
}
