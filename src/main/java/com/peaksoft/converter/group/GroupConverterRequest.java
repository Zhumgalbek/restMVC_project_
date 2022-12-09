package com.peaksoft.converter.group;

import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupConverterRequest {

    public Group create(GroupRequest groupRequest) {
        if (groupRequest == null) return null;
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImage(groupRequest.getImage());
        return group;
    }

    public void update(Group group, GroupRequest groupRequest) {
        if (groupRequest.getGroupName() != null){
            group.setGroupName(groupRequest.getGroupName());
        }if (groupRequest.getImage() != null){
            group.setImage(groupRequest.getImage());
        }



    }
}
