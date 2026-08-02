package com.flowpay.backend.service;

import com.flowpay.backend.dto.CreateExpenseGroupRequest;
import com.flowpay.backend.dto.ExpenseGroupResponse;
import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.AddGroupMemberRequest;
import com.flowpay.backend.entity.GroupMember;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.GroupMemberRepository;
import com.flowpay.backend.repository.UserRepository;
import com.flowpay.backend.dto.GroupMemberResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseGroupService {

    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    public ExpenseGroupService(
            ExpenseGroupRepository expenseGroupRepository,
            UserRepository userRepository,
            GroupMemberRepository groupMemberRepository) {

        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public ExpenseGroupResponse createGroup(CreateExpenseGroupRequest request) {

        ExpenseGroup group = new ExpenseGroup();

        group.setName(request.getName());
        group.setDescription(request.getDescription());

        ExpenseGroup savedGroup =
                expenseGroupRepository.save(group);

        return new ExpenseGroupResponse(
                savedGroup.getId(),
                savedGroup.getName(),
                savedGroup.getDescription(),
                savedGroup.getCreatedAt()
        );
    }

    public List<ExpenseGroupResponse> getAllGroups() {


        return expenseGroupRepository.findAll()
                .stream()
                .map(group -> new ExpenseGroupResponse(
                        group.getId(),
                        group.getName(),
                        group.getDescription(),
                        group.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
    public String addMember(Long groupId,
                            AddGroupMemberRequest request) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean alreadyMember =
                groupMemberRepository.existsByExpenseGroupAndUser(
                        group,
                        user
                );

        if (alreadyMember) {
            throw new RuntimeException("User is already a member of this group");
        }

        GroupMember member = new GroupMember();

        member.setExpenseGroup(group);
        member.setUser(user);

        groupMemberRepository.save(member);

        return "Member added successfully";
    }
    public List<GroupMemberResponse> getGroupMembers(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        return groupMemberRepository.findByExpenseGroup(group)
                .stream()
                .map(member -> new GroupMemberResponse(
                        member.getUser().getId(),
                        member.getUser().getName(),
                        member.getUser().getEmail()
                ))
                .toList();
    }
    @Transactional
    public String removeMember(Long groupId, Long userId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean alreadyMember =
                groupMemberRepository.existsByExpenseGroupAndUser(
                        group,
                        user
                );

        if (!alreadyMember) {
            throw new RuntimeException("User is not a member of this group");
        }

        groupMemberRepository.deleteByExpenseGroupAndUser(
                group,
                user
        );

        return "Member removed successfully";
    }
}