package com.peaksoft.controller;

import com.peaksoft.dto.request.GroupRequest;
import com.peaksoft.dto.response.GroupResponse;
import com.peaksoft.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/getAllGroups")
    @PreAuthorize("isAuthenticated()")
    public List<GroupResponse> getAllGroup() {
        return groupService.getAllGroups();
    }


    @GetMapping("/getCourseById/{id}")
    @PreAuthorize("isAuthenticated()")
    public List<GroupResponse> getCourseById(@PathVariable Long id) {
        return groupService.getAllGroupsByCourseId(id);
    }

    @PostMapping("/{companyId}/saveGroup")
    @PreAuthorize("hasAuthority('Admin')")
    public GroupResponse saveGroup(@PathVariable Long companyId, @RequestBody GroupRequest groupRequest) throws IOException {
        return groupService.saveGroup(companyId, groupRequest);
    }

    @PutMapping("/updateGroup/{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/deleteGroupById/{courseId}/{groupId}")
    @PreAuthorize("hasAuthority('Admin')")
    public GroupResponse deleteGroupById(@PathVariable Long courseId , @PathVariable Long groupId) {
        return groupService.deleteGroup(courseId,groupId);
    }

    @PostMapping("/assignGroup/{courseId}/{groupId}")
    @PreAuthorize("hasAuthority('Admin')")
    public GroupResponse assignGroupCourse(@PathVariable Long groupId, @PathVariable Long courseId) throws IOException {
        return groupService.assignGroup(courseId,groupId);
    }
}
