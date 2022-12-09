package com.peaksoft.service;

import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.dto.response.GroupResponse;

import java.io.IOException;
import java.util.List;

public interface GroupService {

    List<GroupResponse> getAllGroups();

    List<GroupResponse> getAllGroupsByCourseId(Long courseId);

    List<GroupResponse> getAllGroup(Long companyId);

    GroupResponse getGroupById(Long id);

    GroupResponse saveGroup(Long companyId, GroupRequest groupRequest) throws IOException;

    GroupResponse updateGroup(Long id, GroupRequest groupRequest);

    GroupResponse deleteGroup(Long courseId, Long groupId);

    GroupResponse assignGroup(Long courseId, Long groupId) throws IOException;
}
