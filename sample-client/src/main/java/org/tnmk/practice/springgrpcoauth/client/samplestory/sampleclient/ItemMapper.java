package org.tnmk.practice.springgrpcoauth.client.samplestory.sampleclient;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.tnmk.practice.springgrpcoauth.sampleresourceserver.proto.ItemProto;

/**
 * The destination object could be constructed from many different sources.
 * So we name the mapper/builder/resolver following the target object, not the source objects.
 */
@Component
public class ItemMapper {

    public Item toItem(ItemProto itemProto) {
        Item item = new Item();
        BeanUtils.copyProperties(itemProto, item);
        return item;
    }

}
