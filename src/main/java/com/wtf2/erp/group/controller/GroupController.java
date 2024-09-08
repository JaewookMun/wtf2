package com.wtf2.erp.group.controller;

import com.wtf2.erp.common.dto.DataTableRequest;
import com.wtf2.erp.common.dto.JsonResponse;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.dto.GroupInfo;
import com.wtf2.erp.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/search")
    public JsonResponse<GroupInfo> search(@RequestParam(name = "groupCode", required = false) String groupCode,
                                          @ModelAttribute @Valid DataTableRequest dataTableRequest) {
        return JsonResponse
                .succeed()
                .buildWith(groupService.search(groupCode));
    }

//    @PostMapping(value = "/registration")
//    public JsonResponse<Long> registerGroup(@RequestParam(name = "groupName") String name) {
//        log.info("groupName: {}", name);
//        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//        return JsonResponse
//                .succeed()
//                .buildWith(groupService.register(name, userDetails.getUsername()));
//    }

    @GetMapping(value = "/{id}")
    public Group findOne(@PathVariable("id") Long id) {
        return groupService.getGroupById(id);
    }
}
